package com.build.cloud.modules.mat.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.mat.entity.MatTakeInventoryEntity;
import com.build.cloud.modules.mat.entity.MatTakeInventorylistEntity;
import com.build.cloud.modules.bs.entity.BsTeamEntity;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.mat.dao.MatTakeInventoryDao;
import com.build.cloud.modules.mat.service.IMatTakeInventoryService;
import com.build.cloud.modules.mat.service.IMatTakeInventorylistService;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 盘存表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
@Service
public class MatTakeInventoryServiceImpl extends ServiceImpl<MatTakeInventoryDao, MatTakeInventoryEntity> implements IMatTakeInventoryService {
	
	@Autowired
	private IMatTakeInventorylistService matTakeInventorylistService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ICommonService commonService;

	
	@Override
	public PageUtils pageList(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		page.setRecords(baseMapper.pageList(page, params));
		return new PageUtils(page);
	}

	@Override
	public List<Map<String, Object>> outNumberList(Map<String, Object> params) {
		return baseMapper.outNumberList(params);
	}

	@Override
	public List<Map<String, Object>> takeNumberList(Map<String, Object> params) {
		List<Map<String, Object>> tList = baseMapper.takeNumberList(params);
		Set<String> set = new HashSet<String>();
		for(Map<String, Object> map : tList) {                          
			if (map.get("parentIds") != null ) {
				List<String> list = Splitter.on(",").trimResults().splitToList(map.get("parentIds").toString());
				set.addAll(list);
			}
		}
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("list", list);
		map.put("projectId", params.get("projectId"));
		List<Map<String, Object>> pList = baseMapper.takeNumberParent(map);
		pList.addAll(tList);
		return pList;
	}

	@Override
	@Transactional
	public MatTakeInventoryEntity save(MatTakeInventoryEntity entity) {
		baseMapper.insert(entity);
		for(MatTakeInventorylistEntity lEntity : entity.getList()) {
			lEntity.setTakeId(entity.getId());
		}
		matTakeInventorylistService.insertBatch(entity.getList());
		return entity;
	}

	@Override
	public void update(MatTakeInventoryEntity entity) {
		baseMapper.updateById(entity);
		EntityWrapper<MatTakeInventorylistEntity> wrapper = new EntityWrapper<MatTakeInventorylistEntity>();
		wrapper.eq("take_id", entity.getId());
		matTakeInventorylistService.delete(wrapper);
		matTakeInventorylistService.insertBatch(entity.getList());
	}
	
	@Override
	public MatTakeInventoryEntity info(String id) {
		MatTakeInventoryEntity entity = baseMapper.selectById(id);
		if(entity != null) {
			EntityWrapper<MatTakeInventorylistEntity> wrapper = new EntityWrapper<MatTakeInventorylistEntity>();
			wrapper.eq("take_id", entity.getId());
			List<MatTakeInventorylistEntity> list = matTakeInventorylistService.selectList(wrapper);
			entity.setList(list);
		}
		return entity;
	}

	@Override
	public Map<String, Object> takeAddInfo(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal bigDecimal = baseMapper.takeNumber(params);
		map.put("budgetAmount", bigDecimal == null ? 0 : bigDecimal);
		map.put("outStockList", baseMapper.outNumberListAdd(params));
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatTakeInventoryEntity entity) {
		MatTakeInventoryEntity info = baseMapper.selectById(entity.getId());
		
		//如果流程存在且未完成
		if(!StringUtils.isEmpty(info.getActivityinsId())
				&&Objects.equal(info.getCheckStatus(), "0")
				&&Objects.equal(info.getReturnStatus(), "0")){
			//完成节点
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setUserId(ShiroUtils.getUserId());
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
    		tmp.setLocalMap(localMap);
			activityService.completeTaskByInsId(info.getActivityinsId(),tmp);
			
			if(!StringUtils.isEmpty(info.getActivityinsId())){
				MatTakeInventoryEntity upInfo = new MatTakeInventoryEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_TAKE.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PU_TAKE.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				MatTakeInventoryEntity upInfo = new MatTakeInventoryEntity();
				upInfo.setId(info.getId());
				upInfo.setActivityinsId(activityInsId);
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void check(MatTakeInventoryEntity entity) {
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());
		
		Map<String,Object> localMap = Maps.newHashMap();
		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_PASS.value);
		taskPojo.setLocalMap(localMap);
		activityService.completeTaskByTaskIdAndMap(taskPojo);
		
		ProcessInstance pi = activityService.queryProcessInstance(entity.getActivityinsId());
		//流程结束
		if (pi == null) {  
			//修改单据状态为审核通过
			MatTakeInventoryEntity upInfo = new MatTakeInventoryEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");//审核通过
//			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void reject(MatTakeInventoryEntity entity) {
		//工作流驳回
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());
		
//				Map<String,Object> localMap = Maps.newHashMap();
//				localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
//				taskPojo.setLocalMap(localMap);
		
		boolean isReturnSubmit = activityService.backProcess(taskPojo);
		if(isReturnSubmit){
			//修改单据状态为自由态
			MatTakeInventoryEntity upInfo = new MatTakeInventoryEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void endReturn(MatTakeInventoryEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if(ins == null){//表示流程结束
			//修改单据状态为自由态，已弃审
			MatTakeInventoryEntity upInfo = new MatTakeInventoryEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	public PageUtils getTeamList(Map<String, Object> params) {
		Map<String, String> map = commonService.getCompanyProType(params.get("companyId").toString(),
				params.get("projectId").toString());
		params.put(Constant.PRO_ID, map.get(Constant.PRO_ID));
		Query<BsTeamEntity> query = new Query<BsTeamEntity>(params);
		Page<BsTeamEntity> page = query.getPage();
		page.setRecords(baseMapper.getTeamList(page, params));
		return new PageUtils(page);
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		HashMap<String, Object> newHashMap = Maps.newHashMap();
		newHashMap.put("take_id",key);
		matTakeInventorylistService.deleteByMap(newHashMap);
		baseMapper.delete(key);
	}

}
