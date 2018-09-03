package com.build.cloud.modules.mat.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beust.jcommander.internal.Maps;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.constant.Constant.modules;
import com.build.cloud.common.id.IdGenerator;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.mat.dao.MatPurchasePlanSumDao;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumlistService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

import cn.hutool.core.util.StrUtil;
@Service("matPurchasePlanSumService")
public class MatPurchasePlanSumServiceImpl extends ServiceImpl<MatPurchasePlanSumDao, MatPurchasePlanSumEntity> implements IMatPurchasePlanSumService{

	@Autowired
	private IMatPurchasePlanSumlistService purchasePlanSumlistService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private IMatPurchasePlanlistService matPurchasePlanlistService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private ICommonService commonService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		List<Map<String, Object>> records = baseMapper.getPurchasePlanSumPage(page, params);
		page.setRecords(records);
		return new PageUtils(page);
	}
	
	@Override
	public void savePurchasePlanSum(MatPurchasePlanSumEntity entity) {
		String orderCode = idGenerator.getNewMax(modules.PURCHASE_PLAN_SUM.getValue(), 3, DateUtils.format(new Date(), "yyyyMMdd"), entity.getProjectId(), null);
		entity.setBillCode(orderCode);
		baseMapper.insert(entity);
	}

	@Override
	public void updatePurchasePlanSum(MatPurchasePlanSumEntity entity) {
		//先将原先数据还原
		restorePlan(entity.getId());
		baseMapper.updateById(entity);
		//根据计划id删除下面的清单
		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
		wrapper.eq("plan_id", entity.getId());
		purchasePlanSumlistService.delete(wrapper);
		
		if(entity != null && entity.getPlanSumListCollection().size() > 0) {
			List<MatPurchasePlanSumListEntity> list = entity.getPlanSumListCollection();
			for (MatPurchasePlanSumListEntity planSumListEntity : list) {
				planSumListEntity.setPlanId(entity.getId());
			}
			matPurchasePlanlistService.updatePlanlist(list);
			purchasePlanSumlistService.insertBatch(list);
		}
		
	}

	@Override
	public MatPurchasePlanSumEntity getPurchasePlanSumById(String id) {
		return baseMapper.getPurchasePlanSumById(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchasePlanSumEntity entity) {

		MatPurchasePlanSumEntity info = baseMapper.selectById(entity.getId());
		
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
				MatPurchasePlanSumEntity upInfo = new MatPurchasePlanSumEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_PLAN_SUM.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PU_PLAN_SUM.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				MatPurchasePlanSumEntity upInfo = new MatPurchasePlanSumEntity();
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
	public void check(MatPurchasePlanSumEntity entity) {
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
			MatPurchasePlanSumEntity upInfo = new MatPurchasePlanSumEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");//审核通过
//			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
        }
		
	}


	@Override
	public void reject(MatPurchasePlanSumEntity entity) {
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
			MatPurchasePlanSumEntity upInfo = new MatPurchasePlanSumEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}


	@Override
	public void endReturn(MatPurchasePlanSumEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if(ins == null){//表示流程结束
			//修改单据状态为自由态，已弃审
			MatPurchasePlanSumEntity upInfo = new MatPurchasePlanSumEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			baseMapper.updateById(upInfo);
		}
		
	}

	@Override
	public List<Map<String, Object>> getPurchasePlanSumList(Map<String, Object> params) {
		List<Map<String, Object>> records = baseMapper.getPurchasePlanSumPage(params);
		return records;
	}

	@Override
	public PageUtils getPlanSumList(Map<String, Object> params) {
		Map<String, String> map = commonService.getCompanyProType(params.get("companyId").toString(), params.get("projectId").toString());
		if(StringUtil.equals(map.get(Constant.COMPANY_ROLE_TYPE), Constant.CompanyProRole.BENERALCONTRACTOR.getType()) 
				|| StringUtil.equals(map.get(Constant.COMPANY_ROLE_TYPE), Constant.CompanyProRole.CONSTRUCTION.getType())
				|| StringUtil.equals(map.get(Constant.COMPANY_ROLE_TYPE), Constant.CompanyProRole.LABOR.getType())){
			params.put(Constant.PRO_ID, map.get(Constant.PRO_ID));
			params.put(Constant.COMPANY_ROLE_TYPE, map.get(Constant.COMPANY_ROLE_TYPE));
			params.put(Constant.CompanyProRole.CONSTRUCTION.getName(), map.get(Constant.CompanyProRole.CONSTRUCTION.getName()));
			Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
			Page<Map<String, Object>> page = query.getPage();
			page.setRecords(baseMapper.getPlanSumList(page, params));
			return new PageUtils(page);
		}
		return new PageUtils(new Page<Map<String, Object>>());
		
	}
    
	public static void main(String[] args) {
		String a ="1,2,3,4,5,6,7,8,9";
		String[] b = a.split(",");
		System.out.println(b);
	}

	@Override
	public void restorePlan(String id) {
		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
		wrapper.eq("plan_id", id);
		List<MatPurchasePlanSumListEntity> selectList = purchasePlanSumlistService.selectList(wrapper);
	
		for(MatPurchasePlanSumListEntity entity: selectList) {
			String planlistId = entity.getPlanlistId();
			if(StrUtil.isBlank(planlistId)) {
				return;
			}
			//List<String> idList = Arrays.asList(planlistId.split(","));
			MatPurchasePlanlistEntity planEntity = matPurchasePlanlistService.selectById(planlistId);
			planEntity.setUsedNumber(new BigDecimal(0));
			planEntity.setUsedState("0");
			matPurchasePlanlistService.updateById(planEntity);
		}
		
	}
}
