package com.build.cloud.modules.person.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.person.dao.PsnReportMapper;
import com.build.cloud.modules.person.dto.WorkTeamInfo;
import com.build.cloud.modules.person.service.IPsnReportService;
import com.sunsine.common.util.MathUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;

/**
 * <p>
 *   劳务班组报表
 * </p>
 * @author qindq
 * @since 2018-05-27
 */
@Service
public class PsnReportServiceImpl extends ServiceImpl<PsnReportMapper, WorkTeamInfo> implements IPsnReportService {
	
	/**
     * 劳务班组总首页：1、根据项目查询班组执行情况
     * @param params
     * @return
     */
	@Override
	public PageUtils getWorkTeamInfoByPro(Map<String, Object> params) {
		Query<WorkTeamInfo> query = new Query<WorkTeamInfo>(params);
		Page<WorkTeamInfo> page = query.getPage();
		
		//1、根据项目查询记录，分页；
		String companyId = MapUtil.getStr(params,"companyId");
		String projectId = MapUtil.getStr(params,"projectId");
		WorkTeamInfo wt = new WorkTeamInfo();
		wt.setCompanyId(companyId);
		wt.setProjectId(projectId);
		List<WorkTeamInfo> thisList = baseMapper.getWorkTeamInfoByPro(page, wt);
		
		if(thisList==null||thisList.size()==0){
			return new PageUtils(page);
		}
		
		List<String> teamIdList = new ArrayList<String>();
		for (WorkTeamInfo workTeamInfo : thisList) {
			teamIdList.add(workTeamInfo.getTeamId());
		}

		Map<String,String> buildNameMap = getBuildNameByPro(projectId);
		
		//2、对查询出的四条记录，补全剩下字段的值；--查询出来基于任务单的条数，所以行数会大于4行
		List<WorkTeamInfo> resList = baseMapper.getWorkTeamInfoByTList(teamIdList);
		Map<String,WorkTeamInfo> lastMap = new HashMap<String,WorkTeamInfo>();
		for (WorkTeamInfo wtinfo : resList) {
			String workStatus = wtinfo.getWorkStatus();

			String key = wtinfo.getProjectId() + "&" + wtinfo.getTeamId();
			if(lastMap.keySet().contains(key)){
				WorkTeamInfo oldWT = lastMap.get(key);
				if("2".equals(workStatus)||"3".equals(workStatus)||"4".equals(workStatus)){
					oldWT.setWorkorderNum(addInteger(oldWT.getWorkorderNum(),1));
				}else if("5".equals(workStatus)){
					oldWT.setFinishproNum(MathUtil.add(oldWT.getFinishproNum(), wtinfo.getFinishproNum()));
					oldWT.setFinishValue(MathUtil.add(oldWT.getFinishValue(), MathUtil.multiply(wtinfo.getPrice(), wtinfo.getBuildArea())));
				}
				
				oldWT.setBuildArea(MathUtil.add(oldWT.getBuildArea(), wtinfo.getBuildArea()));
				
				oldWT.setPrice(MathUtil.add(oldWT.getPrice(), wtinfo.getPrice()));
				
				oldWT.setParentId(oldWT.getParentId()+","+wtinfo.getParentId());
				
			}else{
				WorkTeamInfo newWT = new WorkTeamInfo();
				BeanUtil.copyProperties(wtinfo, newWT);
				if("2".equals(workStatus)||"3".equals(workStatus)||"4".equals(workStatus)){
					newWT.setWorkorderNum(1);
				}else if("5".equals(workStatus)){
					newWT.setFinishValue(MathUtil.multiply(wtinfo.getPrice(), wtinfo.getBuildArea()));
				}
				
				lastMap.put(key, newWT);
			}
		}
		
		//3、补全Map的数据，并转为List
		page.setRecords(mapChange2List(lastMap, buildNameMap, projectId,thisList));
		return new PageUtils(page);
	}
	
	private Integer addInteger(Integer a,Integer b){
		if(a==null){
			return b;
		}
		
		return a+b;
	}
	
	private List<WorkTeamInfo> mapChange2List(Map<String,WorkTeamInfo> lastMap,Map<String,String> buildNameMap
			,String projectId,List<WorkTeamInfo> thisList){
		
//		Map<String,WorkTeamInfo> oldMap = hashList2Map(thisList);

		List<WorkTeamInfo> rtnList = new ArrayList<WorkTeamInfo>();
		for (WorkTeamInfo wtinfo : thisList) {
			String key = wtinfo.getProjectId() + "&" + wtinfo.getTeamId();
			
			WorkTeamInfo buchongWT = lastMap.get(key);
			if(buchongWT!=null){
				wtinfo.setWorkorderNum(buchongWT.getWorkorderNum());//进行中的任务单数
				wtinfo.setFinishproNum(buchongWT.getFinishproNum());//完成的工程量

				wtinfo.setBuildArea(buchongWT.getBuildArea());
				
				wtinfo.setFinishValue(buchongWT.getFinishValue());//这里的price是汇总数，已完成建筑面积*单价
				
				wtinfo.setPrice(MathUtil.divide(buchongWT.getPrice(), buchongWT.getBuildArea()));//单价，合同拆分中的合价（已完成）/已完成工程量 ，单位需要显示在单元格中，如元/t、混凝土为元/m3、元/m2。
				
				wtinfo.setParentId(buchongWT.getParentId());
			}

			String buildName = getBuildNameByPlan(wtinfo, buildNameMap);
			wtinfo.setBuildName(buildName);
			
			double workDays = getWorkRecordDaysByTeam(projectId, wtinfo.getTeamId());
			wtinfo.setWorkRecord(workDays);
			
			rtnList.add(wtinfo);
		}
		
		return rtnList;
	}
	
	private Map<String,WorkTeamInfo> hashList2Map(List<WorkTeamInfo> thisList){
		
		Map<String,WorkTeamInfo> oldMap  = new HashMap<String,WorkTeamInfo>();
		for (WorkTeamInfo wtinfo : thisList) {
			String key = wtinfo.getProjectId() + "&" + wtinfo.getTeamId();
			
			oldMap.put(key, wtinfo);
		}
	
		return oldMap;
	}
	
	private double getWorkRecordDaysByTeam(String projectId,String teamId){
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("projectId", projectId);
		userMap.put("teamId", teamId);
		
		List<WorkTeamInfo> workDays = baseMapper.getWorkRecordDaysByTeam(userMap);
		if(workDays==null||workDays.size()==0){
			return 0;
		}
		
		return MathUtil.add(workDays.get(0).getPrice(), workDays.get(0).getFinishValue());
	}
	
	private String getBuildNameByPlan(WorkTeamInfo wtinfo,Map<String,String> buildNameMap){
		
		Set<String> names = new HashSet<String>();
		if(wtinfo.getParentId()==null)
			return "";
		String[] arrays = wtinfo.getParentId().split(",");//检验批肯定有父级
		for (String key : arrays) {
			if(buildNameMap.keySet().contains(key)){
				names.add(buildNameMap.get(key));
			}
		}
		
		String lastString = "";
		Iterator it = names.iterator();
		while(it.hasNext()){
			lastString =lastString + ","+ it.next();
		}
		
		if(lastString.length()>0){
			return lastString.substring(1);
		}
		return lastString;
	}
	
	//根据项目名称获取：楼栋唯一标志+名称；
	private Map<String,String> getBuildNameByPro(String projectId){
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("projectId", projectId);
		
		Map<String,String> rtnMap = new HashMap<String,String>();
		List<WorkTeamInfo> resList = baseMapper.getBuildNameByPro(userMap);
		if(resList==null||resList.size()==0)
			return rtnMap;
		
		for (WorkTeamInfo thisList : resList) {
			rtnMap.put(thisList.getUniqueId(), thisList.getWbsName());
		}
		
		return rtnMap;
	}
	
	//------------------------
	/**
     * 劳务班组总首页：2、根据项目查询单项数据
     * @param params
     * @return
     */
	@Override
	public List<Map<String, Object>> getItemValueByPro(Map<String, Object> params) {
		
		List<Map<String,Object>> thisList = baseMapper.getItemValueByPro(params);
		
		
		return thisList;
	}
	
	
}

