package com.build.cloud.modules.punch.service.impl;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.punch.bean.PunchBean;
import com.build.cloud.modules.punch.dao.PunchDao;
import com.build.cloud.modules.punch.entity.ProAttendEntity;
import com.build.cloud.modules.punch.entity.PunchEntity;
import com.build.cloud.modules.punch.entity.StatExceptionEntity;
import com.build.cloud.modules.punch.service.IDevDeviceService;
import com.build.cloud.modules.punch.service.IProAttendService;
import com.build.cloud.modules.punch.service.IPunchService;
import com.build.cloud.modules.punch.service.IStatExceptionService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class PunchServiceImpl extends ServiceImpl<PunchDao, PunchEntity> implements IPunchService {
	private static final Logger LOG = LoggerFactory.getLogger(PunchServiceImpl.class);
	private static final String EARLIEST_TIME_STR = "00:00";
	private static final String LAST_TIME_STR = "23:59";
	private static final Date EARLIEST_TIME = DateUtil.parse(EARLIEST_TIME_STR, "HH:mm");
	private static final Date LAST_TIME = DateUtil.parse(LAST_TIME_STR, "HH:mm");
	// 异常类型（1:无进场时间；2:无退场时间）
	private static final String NO_ENTRYTIME = "1";
	private static final String NO_EXITTIME = "2";
	@Autowired
	private IStatExceptionService statExceptionService;
	@Autowired
	private IProAttendService proAttendService;
	@Autowired
	private IDevDeviceService devDeviceService;
	// 记录第二天的最早进场时间
	private Map<String, Object> map_enter = Maps.newConcurrentMap();
	@Override
	public List<PunchEntity> queryStatCard() {
		return baseMapper.queryStatCard();
	}
	// redis存储的为后续要修改调整的考勤
	@Override
	public List<PunchBean> queryPunch(Map<String, Object> params) {
		List<String> projectIds = devDeviceService.selectAllProject();
		List<PunchBean> list = null;
		List<PunchBean> punchs = Lists.newArrayList();
		Map<String, Object> exitTimeMap = null;
		Date statDay = new Date() ;
		for (String projectId : projectIds) {
			params.put("projectId", projectId);
			// 获取最大时间
			Map<String, Object> dMap = proAttendService.queryMaxDate();
			String ad = MapUtil.getStr(dMap, "attendDate");
			if (StrUtil.isNotEmpty(ad)) {
				params.put("timeNode", DateUtil.parse(ad));
				statDay = DateUtil.parse(ad);
			}
			list = baseMapper.queryPunch(params);
			for (PunchBean pe : list) {
				LOG.info("punchBean:" + pe.toString());
				String[] entryTimes = StrUtil.split(pe.getEntryTime(), ","); // 进场时间
				String[] exitTimes = StrUtil.split(pe.getExitTime(), ","); // 退场时间
				if(pe.getAttendDate().compareTo(DateUtils.addDateDays(statDay, -1)) == 0){//考勤为两天考勤的前一天时，取跨天考勤之后的数据
					//查询当天跨天考勤数据
					Map<String, Object> param = Maps.newHashMap();
					param.put("attendDate", DateUtils.addDateDays(statDay, -1));
					param.put("status", 1);
					param.put("workerId", pe.getWorkerId());
					param.put("projectId", pe.getProjectId());
					exitTimeMap = proAttendService.queryCrossDay(param);
					if(MapUtils.isNotEmpty(exitTimeMap)){//处理跨天考勤之前的无效数据
						String cross = MapUtil.getStr(exitTimeMap, "exitTime");
						Date crossTime = DateUtil.parse(cross, "HH:mm:ss");
						for (String exiTime : exitTimes) {
							Date exitTime = DateUtil.parse(exiTime, "HH:mm:ss");
							if(crossTime.compareTo(exitTime) == 1){
								exitTimes = ArrayUtil.removeEle(exitTimes, exiTime);
							}
						}
						for (String entTime : entryTimes) {
							Date exitTime = DateUtil.parse(entTime, "HH:mm:ss");
							if(crossTime.compareTo(exitTime) == 1){
								entryTimes = ArrayUtil.removeEle(entryTimes, entTime);
							}
						}
						exitTimes = ArrayUtil.removeEle(exitTimes, cross);
					}
				}
				PunchBean pBean = null;
				PunchBean punBean = null;
				Map<String, Object> countParams = Maps.newHashMap();
				countParams.put("projectId", projectId);
				countParams.put("workerId", pe.getWorkerId());
				countParams.put("attendDate", DateUtils.toSqlDate(DateUtils.addDateDays(pe.getAttendDate(), 1)));
				Map<String, Object> enterMap = queryEntryTime(countParams);//查询第二天最早进场考勤
				countParams.put("attendDate", DateUtils.toSqlDate(DateUtils.addDateDays(pe.getAttendDate(), 1)));
				boolean flag = false;
				if(MapUtils.isNotEmpty(enterMap)){
					map_enter.put(DateUtils.format(DateUtils.addDateDays(pe.getAttendDate(), 1)), enterMap.get("entryTime"));
					countParams.put("enTime", enterMap.get("entryTime"));
				}
				String eTimeList = queryExitTime(countParams);//查询第二天最早出场考勤
				String[] nextExitTimes = StrUtil.split(eTimeList, ","); // 第二天的出场时间
				// 处理跨天考勤
				if(ArrayUtil.isNotEmpty(nextExitTimes) && ArrayUtil.isNotEmpty(entryTimes)){
					if (ArrayUtil.isNotEmpty(exitTimes)) {
						flag = true;
						for (String exitime : exitTimes) {
							if(exitime.compareTo(entryTimes[0]) > 0){//去除当天无效出场纪录
								ArrayUtil.removeEle(entryTimes, exitime);
							}
						}
					}else{//当天无出场考勤
						flag = true;
					}
				}else if(ArrayUtil.isEmpty(nextExitTimes)){//查询到后一天的退场时间为空，判断为未跨天（只打了进去的卡但是未进去，清除无效进场的时间）
					if (ArrayUtil.isNotEmpty(entryTimes) && ArrayUtil.isNotEmpty(exitTimes)) {
						for (String str : entryTimes) {
							Date exitTime = DateUtil.parse(exitTimes[0], "HH:mm:ss");
							Date entryTime = DateUtil.parse(str, "HH:mm:ss");
							if (exitTime.compareTo(entryTime) == -1) {//最晚出场时间<进场时间----进场时间无效
								LOG.info("排除未跨天的异常进场考勤时间：", str);
								entryTimes = ArrayUtil.removeEle(entryTimes, str);
							}
						}
					}
				}
				if(flag){
					LOG.info("处理跨天当天早上的考勤数据--------------------");
					pBean = new PunchBean();
					BeanUtil.copyProperties(pe, pBean, "entryTime", "exitTime");
					pBean.setId(IdWorker.getId());
					pBean.setExitTime(nextExitTimes[0]);
					pBean.setEntryTime(EARLIEST_TIME_STR);
					pBean.setRemark("处理跨天数据，默认进场时间为00:00，数据仅供参考！");
					pBean.setAttendTime(DateUtils.getDatePoor(DateUtil.parse(nextExitTimes[0], "HH:mm"), EARLIEST_TIME));
					pBean.setAttendDate(DateUtil.parse(DateUtils.format(DateUtils.addDateDays(pe.getAttendDate(), 1)).toString()+" "+nextExitTimes[0],"yyyy-MM-dd HH:mm:ss"));
					pBean.setAberrant(false);
					pBean.setStatus("1");//跨天考勤
					pBean.setExType("");
					punchs.add(pBean);	
					LOG.info("处理当天晚上的考勤数据--------------------");
					punBean = new PunchBean();
					BeanUtil.copyProperties(pe, punBean, "entryTime", "exitTime");
					punBean.setId(IdWorker.getId());
					punBean.setExitTime(LAST_TIME_STR);
					punBean.setEntryTime(entryTimes[0]);
					punBean.setAttendDate(DateUtil.parse(DateUtils.format(pe.getAttendDate()).toString()+" "+LAST_TIME_STR,"yyyy-MM-dd HH:mm"));
					punBean.setRemark("未匹配到大于当前进场时间的退场时间，默认去当天23:59，数据仅供参考！");
					punBean.setAttendTime(
							DateUtils.getDatePoor(LAST_TIME, DateUtil.parse(entryTimes[0], "HH:mm:ss")));
					punBean.setAberrant(true);
					punBean.setStatus("2");
					punBean.setExType(NO_EXITTIME);
					punchs.add(punBean);
					entryTimes = ArrayUtil.removeEle(entryTimes, entryTimes[0]);
				}
				Date lastEntryTime = null;
				Date lastExitTime = null;
				e: for (String exiTime : exitTimes) {
					for (String etTime : entryTimes) {
						pBean = new PunchBean();
						BeanUtil.copyProperties(pe, pBean, "entryTime", "exitTime");
						Date exitTime = DateUtil.parse(exiTime, "HH:mm:ss");
						Date entryTime = DateUtil.parse(etTime, "HH:mm:ss");
						// 时间比较前者大于后者为1，小于后者为-1，等于为0
						LOG.info("判断===[{}]", exitTime.compareTo(entryTime));
						if (lastEntryTime!=null && lastExitTime!=null && lastExitTime.compareTo(exitTime) == 1 && lastEntryTime.compareTo(exitTime) == -1) {
							exitTimes = ArrayUtil.removeEle(exitTimes, exiTime);//删除正常考勤中的无效出场数据
							continue e;
						}
						if (exitTime.compareTo(entryTime) == 1 || exitTime.compareTo(entryTime) == 0 ) { //退场大于进场，直接计算工时
							LOG.info("处理正常时间业务--------------------");
							pBean.setId(IdWorker.getId());
							pBean.setEntryTime(etTime);
							pBean.setExitTime(exiTime);
							pBean.setAttendTime(DateUtils.getDatePoor(exitTime, entryTime));
							pBean.setAttendDate(DateUtil.parse(DateUtils.format(pe.getAttendDate()).toString()+" "+exiTime,"yyyy-MM-dd HH:mm:ss"));
							pBean.setAberrant(false);
							pBean.setStatus("0");//正常考勤
							pBean.setRemark("正常考勤数据");
							lastEntryTime = entryTime;
							lastExitTime = exitTime;
							// 删除已处理的时间
							exitTimes = ArrayUtil.removeEle(exitTimes, exiTime);
							entryTimes = ArrayUtil.removeEle(entryTimes, etTime);
							LOG.info("移除已处理的进场时间：{}，移除已处理的退场时间：{}", etTime, exiTime);
							punchs.add(pBean);
							continue e;
						}else if(exitTime.compareTo(entryTime) == -1) { //退场小于进场(无效考勤)
							LOG.info("移除无效的进场时间：{}", etTime);
							entryTimes = ArrayUtil.removeEle(entryTimes, etTime);
						}
					}
				}
				map_enter.remove(DateUtils.format(DateUtils.addDateDays(pe.getAttendDate(), 1)));
				//删除个人两天内所有考勤记录
				Wrapper<ProAttendEntity> wrapper = new EntityWrapper<ProAttendEntity>();
				String newDay = null;
				wrapper.eq("worker_id", pe.getWorkerId());
				wrapper.eq("project_id", pe.getProjectId());
				if(MapUtils.isNotEmpty(exitTimeMap)){
					String cross = MapUtil.getStr(exitTimeMap, "exitTime");
					newDay = DateUtils.format(DateUtils.addDateDays(statDay, -1)).toString()+" " +cross;
					wrapper.gt("attend_date", DateUtil.parse(newDay,"yyyy-MM-dd HH:mm:ss"));
					if(statDay.compareTo(pe.getAttendDate()) == 0){
						exitTimeMap.remove("exitTime");
					}
				}else{
					wrapper.gt("attend_date", DateUtils.format(DateUtils.addDateDays(statDay, -1)));
				}
				proAttendService.delete(wrapper);
			}
		}
		
		ThreadUtil.execAsync(new Runnable() {
			@Override
			public void run() {
				try {
					if (CollectionUtil.isNotEmpty(punchs)) {
						List<ProAttendEntity> entityList = Lists.newArrayListWithCapacity(punchs.size());
						List<ProAttendEntity> ProAttendEntityList = Lists.newArrayListWithCapacity(punchs.size());
						for (PunchBean pb : punchs) {
							ProAttendEntity pEntity = new ProAttendEntity();
							BeanUtil.copyProperties(pb, pEntity);
							pEntity.setCreateDate(DateUtil.date());
							entityList.add(pEntity);
						}
						for (ProAttendEntity pEntity : entityList) {//清除考勤工时为0的考勤记录
							if(pEntity.getAttendTime() > 0.00){
								ProAttendEntityList.add(pEntity);
							}
						}
						proAttendService.insertOrUpdateBatch(ProAttendEntityList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return punchs;
	}
	
	@Transactional
	private void update(StatExceptionEntity exEntity, ProAttendEntity proAttEntity) {
		statExceptionService.updateById(exEntity);
		proAttendService.insert(proAttEntity);
	}
	private StatExceptionEntity initExEntity(Long scId, Date exDate, String exType, String workerId) {
		return initExEntity(scId, exDate, exType, workerId, "");
	}
	private StatExceptionEntity initExEntity(Long scId, Date exDate, String exType, String workerId, String remark) {
		StatExceptionEntity exEntity = new StatExceptionEntity();
		exEntity.setScId(scId);
		exEntity.setExDate(exDate);
		exEntity.setExType(exType);
		exEntity.setWorkerId(workerId);
		if (StrUtil.isNotEmpty(remark)) {
			exEntity.setRemark(remark);
		} else {
			if (exType.equals(NO_ENTRYTIME)) {
				exEntity.setRemark("无进场时间，进场时间异常");
			} else if (exType.equals(NO_EXITTIME)) {
				exEntity.setRemark("无退场时间，退场时间异常");
			}
		}
		exEntity.setCreateTime(DateUtil.date());
		return exEntity;
	}
	private class StatExceptionRun implements Runnable {
		private StatExceptionEntity exEntity;
		public StatExceptionRun(StatExceptionEntity exEntity) {
			this.exEntity = exEntity;
		}
		@Override
		public void run() {
			statExceptionService.insert(exEntity);
		}
	}
	@Override
	public Map<String, Object> queryEntryTime(Map<String, Object> params) {
		return baseMapper.queryEntryTime(params);
	}
	private String queryExitTime(Map<String, Object> params) {
		return baseMapper.queryExitTimes(params);
	}
}
