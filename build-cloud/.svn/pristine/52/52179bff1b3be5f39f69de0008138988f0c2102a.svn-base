package com.build.cloud.modules.mat.service.impl;

import java.math.BigDecimal;
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
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.mat.dao.MatPurchaseStockInDao;
import com.build.cloud.modules.mat.entity.MatPurchaseStockEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockInService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockInlistService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * <p>
 * 入库表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@Service
public class MatPurchaseStockInServiceImpl extends ServiceImpl<MatPurchaseStockInDao, MatPurchaseStockInEntity> implements IMatPurchaseStockInService {
	
	@Autowired IMatPurchaseStockInlistService matPurchaseStockInlistService;
	
	@Autowired
	private IMatPurchaseStockService matPurchaseStockService;
	
	@Autowired
	private IMatPurchaseStockInService matPurchaseStockInService;
	
	@Autowired
	private ActivityService activityService; 

	@Override
	public PageUtils queryPageList(Map<String, Object> params) {
		Query<MatPurchaseStockInEntity> query = new Query<MatPurchaseStockInEntity>(params);
		Page<MatPurchaseStockInEntity> page = query.getPage();
		page.setRecords(baseMapper.queryPageList(page, params));
		return new PageUtils(page);
	}

	@Override
	public MatPurchaseStockInEntity info(String id) {
		MatPurchaseStockInEntity entity = baseMapper.selectByKey(id);
		List<MatPurchaseStockInlistEntity> list = matPurchaseStockInlistService.selectListInfo(id);
		entity.setList(list);
		return entity;
	}

	@Override
	@Transactional
	public MatPurchaseStockInEntity save(MatPurchaseStockInEntity entity) {
		baseMapper.insert(entity);
		List<MatPurchaseStockInlistEntity> slEntities = entity.getList();
		for(MatPurchaseStockInlistEntity ilEntity : slEntities) {
			ilEntity.setWarehousId(entity.getId());
		}
		matPurchaseStockInlistService.insertBatch(slEntities);
		return entity;
	}

	@Override
	@Transactional
	public void update(MatPurchaseStockInEntity entity) {
		baseMapper.updateById(entity);
		EntityWrapper<MatPurchaseStockInlistEntity> wrapper = new EntityWrapper<MatPurchaseStockInlistEntity>();
		wrapper.eq("warehous_id", entity.getId());
		matPurchaseStockInlistService.delete(wrapper);
		matPurchaseStockInlistService.insertBatch(entity.getList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchaseStockInEntity entity) {
		MatPurchaseStockInEntity info = baseMapper.selectById(entity.getId());
		
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
				MatPurchaseStockInEntity upInfo = new MatPurchaseStockInEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_STOCKIN.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PU_STOCKIN.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				MatPurchaseStockInEntity upInfo = new MatPurchaseStockInEntity();
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
	public void check(MatPurchaseStockInEntity entity) {
		
		List<MatPurchaseStockInlistEntity> list = matPurchaseStockInlistService.selectListInfo(entity.getId());
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", entity.getId());
		List<Map<String, Object>> obList = baseMapper.selectAlreadyList(params);
		String checkStr = "";
		for(MatPurchaseStockInlistEntity eInlistEntity : list){
			for(Map<String, Object> map : obList){
				if (map.get("sumWarehousNumber") == null) {
					continue;
				}
				BigDecimal sumWarehousNumber = new BigDecimal(map.get("sumWarehousNumber").toString());
				if (StringUtil.equals(eInlistEntity.getPolId(), map.get("polId").toString())) {
					if (eInlistEntity.getOrderNumber().compareTo(sumWarehousNumber.add(eInlistEntity.getWarehousNumber())) == -1){
						checkStr += eInlistEntity.getMtrName() + "已入库量为："
								+ map.get("sumWarehousNumber") + ",订单量为："
								+ eInlistEntity.getOrderNumber() + ",当前入库量为："
								+ eInlistEntity.getWarehousNumber() + ",已入库量与当前入库量总数已经大于订单量,";
						break;
					}
				}
			}
		}
		
		if (StringUtil.isNotBlank(checkStr)) {
			throw new BusinessException(checkStr + "不能通过审核");
		}
		
		
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
			MatPurchaseStockInEntity upInfo = new MatPurchaseStockInEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");//审核通过
//			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
			updateStock(entity.getId());
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void reject(MatPurchaseStockInEntity entity) {
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
			MatPurchaseStockInEntity upInfo = new MatPurchaseStockInEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void endReturn(MatPurchaseStockInEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if(ins == null){//表示流程结束
			//库存量还原
			reductionStock(entity.getId());
			//修改单据状态为自由态，已弃审
			MatPurchaseStockInEntity upInfo = new MatPurchaseStockInEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			baseMapper.updateById(upInfo);
		}
	}
	
	/**
	 * 审核通过更新库存
	 * @param id 入库主键
	 */
	public void updateStock(String id){
		MatPurchaseStockInEntity entity = matPurchaseStockInService.selectById(id);
		EntityWrapper<MatPurchaseStockInlistEntity> _wrapper = new EntityWrapper<MatPurchaseStockInlistEntity>();
		_wrapper.eq("warehous_id", id);
		List<MatPurchaseStockInlistEntity> slEntities = matPurchaseStockInlistService.selectList(_wrapper);
		//修改库存量，当审核通过的时候更新库存量
		for (MatPurchaseStockInlistEntity ilEntity : slEntities) {
			EntityWrapper<MatPurchaseStockEntity> wrapper = new EntityWrapper<MatPurchaseStockEntity>();
			wrapper.eq("project_id", entity.getProjectId());
			wrapper.eq("mtr_code", ilEntity.getMtrCode());
	
			MatPurchaseStockEntity stockEntity = matPurchaseStockService.selectOne(wrapper);
			if (stockEntity == null) {
				MatPurchaseStockEntity matStockEntity = new MatPurchaseStockEntity();
				matStockEntity.setMeasureCode(ilEntity.getMeasureCode());
				matStockEntity.setMeasureName(ilEntity.getMeasureName());
				matStockEntity.setMtrCode(ilEntity.getMtrCode()); 
				matStockEntity.setMtrName(ilEntity.getMtrName());
				matStockEntity.setMtrKindCode(ilEntity.getMtrKindCode());
				matStockEntity.setMtrKindName(ilEntity.getMtrKindName());
				matStockEntity.setProjectId(entity.getProjectId());
				matStockEntity.setProjectName(entity.getProjectName());
				matStockEntity.setSpecs(ilEntity.getSpecs());
				matStockEntity.setStockNumber(ilEntity.getWarehousNumber());
				matStockEntity.setTotalInNumber(ilEntity.getWarehousNumber());
				matPurchaseStockService.insert(matStockEntity);
			} else {
				stockEntity.setStockNumber(stockEntity.getStockNumber().add(ilEntity.getWarehousNumber()));
				stockEntity.setTotalInNumber(stockEntity.getTotalInNumber().add(ilEntity.getWarehousNumber()));
				matPurchaseStockService.updateById(stockEntity);
			}
		}
	}
	
	/**
	 * 弃审更新库存
	 * @param id 入库主键
	 */
	public void reductionStock(String id){
		MatPurchaseStockInEntity entity = matPurchaseStockInService.selectById(id);
		EntityWrapper<MatPurchaseStockInlistEntity> _wrapper = new EntityWrapper<MatPurchaseStockInlistEntity>();
		_wrapper.eq("warehous_id", id);
		List<MatPurchaseStockInlistEntity> slEntities = matPurchaseStockInlistService.selectList(_wrapper);
		//修改库存量，当驳回的时候更新库存量
		for (MatPurchaseStockInlistEntity ilEntity : slEntities) {
			EntityWrapper<MatPurchaseStockEntity> wrapper = new EntityWrapper<MatPurchaseStockEntity>();
			wrapper.eq("project_id", entity.getProjectId());
			wrapper.eq("mtr_code", ilEntity.getMtrCode());
			MatPurchaseStockEntity stockEntity = matPurchaseStockService.selectOne(wrapper);
			stockEntity.setStockNumber(stockEntity.getStockNumber().subtract(ilEntity.getWarehousNumber()));
			stockEntity.setTotalInNumber(stockEntity.getTotalInNumber().subtract(ilEntity.getWarehousNumber()));
			matPurchaseStockService.updateById(stockEntity);
		}
	}

}
