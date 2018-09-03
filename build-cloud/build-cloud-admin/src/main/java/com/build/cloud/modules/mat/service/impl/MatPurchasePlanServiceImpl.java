package com.build.cloud.modules.mat.service.impl;


import java.util.ArrayList;
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
import com.build.cloud.modules.mat.dao.MatPurchasePlanDao;
import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: MatPurchasePlanServiceImpl</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午2:33:22
 */
@Service
public class MatPurchasePlanServiceImpl extends ServiceImpl<MatPurchasePlanDao, MatPurchasePlanEntity> implements IMatPurchasePlanService{

	@Autowired 
	private IMatPurchasePlanlistService matPurchasePlanlistService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ICommonService commonService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		EntityWrapper<MatPurchasePlanEntity> wrapper = new EntityWrapper<MatPurchasePlanEntity>();
		String purchaseType = MapUtil.getStr(params, "purchaseType");
		String planMonth =MapUtil.getStr(params, "planMonth");
//		Date dateMoth =  DateUtils.stringToDate(planMonth, DateUtils.DATE_MOTH_PATTERN);
		wrapper.eq(StrUtil.isNotBlank(purchaseType),"purchase_type", purchaseType);
		wrapper.eq(StrUtil.isNotBlank(planMonth),"plan_month", planMonth);
		Page<MatPurchasePlanEntity> page = 
				this.selectPage(new Query<MatPurchasePlanEntity>(params).getPage(), wrapper);
		return new PageUtils(page);
	}

	
	@Override
	@Transactional
	public void insertPurchasePlan(MatPurchasePlanEntity entity) {
		List<MatPurchasePlanlistEntity> list = entity.getList();
		String billCode = idGenerator.getNewMax(modules.PURCHASE_PLAN.getValue(), 3, DateUtils.format(new Date(), "yyyyMMdd"), entity.getProjectId(),null);
		entity.setBillCode(billCode);
		this.insert(entity);
		if(list!=null && list.size()!=0) {
			for(MatPurchasePlanlistEntity planlist:list) {
				planlist.setPlanId(entity.getId());
				matPurchasePlanlistService.insert(planlist);
			}
		}
		
	}
	@Override
	public void updatePurchasePlan(MatPurchasePlanEntity entity) {
		List<MatPurchasePlanlistEntity> list = entity.getList();
		baseMapper.updateById(entity);
		String planId = entity.getId();
		matPurchasePlanlistService.delteByPlanId(planId);
		matPurchasePlanlistService.insertBatch(list);	
	}


	@Override
	public PageUtils selectPage(Map<String, Object> params) {
		Query<MatPurchasePlanEntity> query = new Query<MatPurchasePlanEntity>(params);
		Page<MatPurchasePlanEntity> page = query.getPage();
		page.setRecords(baseMapper.selectPage(page, params));
		return new PageUtils(page);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchasePlanEntity entity) {

		MatPurchasePlanEntity info = baseMapper.selectById(entity.getId());
		
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
				MatPurchasePlanEntity upInfo = new MatPurchasePlanEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_PLAN.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PU_PLAN.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				MatPurchasePlanEntity upInfo = new MatPurchasePlanEntity();
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
	public void check(MatPurchasePlanEntity entity) {
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
			MatPurchasePlanEntity upInfo = new MatPurchasePlanEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");//审核通过
//			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
        }
		
	}


	@Override
	public void reject(MatPurchasePlanEntity entity) {
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
			MatPurchasePlanEntity upInfo = new MatPurchasePlanEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}


	@Override
	public void endReturn(MatPurchasePlanEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if(ins == null){//表示流程结束
			//修改单据状态为自由态，已弃审
			MatPurchasePlanEntity upInfo = new MatPurchasePlanEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			baseMapper.updateById(upInfo);
		}
		
	}


	@Override
	public void deleteById(String id) {
		baseMapper.deleteById(id);
		matPurchasePlanlistService.delteByPlanId(id);
	}

	@Override
	public PageUtils getPlanList(Map<String, Object> params) {
		Map<String, String> map = commonService.getCompanyProType(params.get("companyId").toString(), params.get("projectId").toString());
		if (StringUtil.equals(Constant.CompanyProRole.BENERALCONTRACTOR.getType(), map.get(Constant.COMPANY_ROLE_TYPE))) {
			params.put(Constant.PRO_ID, map.get(Constant.PRO_ID));
			Query<MatPurchasePlanEntity> query = new Query<MatPurchasePlanEntity>(params);
			Page<MatPurchasePlanEntity> page = query.getPage();
			page.setRecords(baseMapper.getPlanList(page, params));
			return new PageUtils(page);
		}
		return new PageUtils(new Page<MatPurchasePlanEntity>().setRecords(new ArrayList<MatPurchasePlanEntity>()));
	}


	@Override
	public PageUtils selectReferList(Map<String, Object> params) {
		Query<MatPurchasePlanEntity> query = new Query<MatPurchasePlanEntity>(params);
		Page<MatPurchasePlanEntity> page = query.getPage();
		page.setRecords(baseMapper.selectReferPage(page, params));
		return new PageUtils(page);
	}
}
