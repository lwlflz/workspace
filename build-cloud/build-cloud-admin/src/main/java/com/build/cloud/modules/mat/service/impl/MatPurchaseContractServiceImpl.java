package com.build.cloud.modules.mat.service.impl;

import java.util.Date;
import java.util.Map;

import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.mat.entity.MatPurchaseContractEntity;
import com.build.cloud.modules.mat.dao.MatPurchaseContractDao;
import com.build.cloud.modules.mat.service.IMatPurchaseContractService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 物资采购合同文本表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
@Service
public class MatPurchaseContractServiceImpl extends ServiceImpl<MatPurchaseContractDao, MatPurchaseContractEntity> implements IMatPurchaseContractService {
	
	@Autowired
	private ActivityService activityService;
	
	@Override
	public PageUtils queryPageList(Map<String, Object> params) {
		Query<MatPurchaseContractEntity> query = new Query<MatPurchaseContractEntity>(params);
		Page<MatPurchaseContractEntity> page = query.getPage();
		page.setRecords(baseMapper.selectPage(page, params));
		return new PageUtils(page);
	}

	@Override
	public MatPurchaseContractEntity save(MatPurchaseContractEntity entity) {
		baseMapper.insert(entity);
		return entity;
	}

	@Override
	public void update(MatPurchaseContractEntity entity) {
		baseMapper.updateById(entity);
	}

	@Override
	public MatPurchaseContractEntity info(String id) {
		MatPurchaseContractEntity entity = baseMapper.selectByKey(id);
		return entity;
	}

	@Override
	public void delete(String id) {
		baseMapper.deleteById(id);
	}

	@Override
	public PageUtils happenedPuList(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		page.setRecords(baseMapper.happenedPuList(page, params));
		return new PageUtils(page);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchaseContractEntity entity) {
		MatPurchaseContractEntity info = baseMapper.selectById(entity.getId());
		
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
				MatPurchaseContractEntity upInfo = new MatPurchaseContractEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_CON.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PU_CON.value + ":" + info.getConType());
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				MatPurchaseContractEntity upInfo = new MatPurchaseContractEntity();
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
	public void check(MatPurchaseContractEntity entity) {
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
			MatPurchaseContractEntity upInfo = new MatPurchaseContractEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");//审核通过
//			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void reject(MatPurchaseContractEntity entity) {
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
			MatPurchaseContractEntity upInfo = new MatPurchaseContractEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void endReturn(MatPurchaseContractEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if(ins == null){//表示流程结束
			//修改单据状态为自由态，已弃审
			MatPurchaseContractEntity upInfo = new MatPurchaseContractEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			baseMapper.updateById(upInfo);
		}
		
	}

	@Override
	public PageUtils selectIndexPage(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		page.setRecords(baseMapper.selectIndexPage(page, params));
		return new PageUtils(page);
	}
}
