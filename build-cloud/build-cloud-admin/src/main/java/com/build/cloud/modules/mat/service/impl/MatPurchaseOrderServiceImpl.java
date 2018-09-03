package com.build.cloud.modules.mat.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.id.IdGenerator;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.mat.dao.MatPurchaseOrderDao;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderService;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderlistService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumlistService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Title: MatPurchaseOrderServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author WangJun
 * @date 2018年7月7日 上午10:56:11
 */
@Service
public class MatPurchaseOrderServiceImpl extends ServiceImpl<MatPurchaseOrderDao, MatPurchaseOrderEntity>
		implements IMatPurchaseOrderService {
	@Autowired
	private IMatPurchaseOrderlistService matPurchaseOrderlistService;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private IMatPurchasePlanlistService matPurchasePlanlistService;

	@Autowired
	private IMatPurchasePlanSumlistService matPurchasePlanSumlistService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		EntityWrapper<MatPurchaseOrderEntity> wrapper = new EntityWrapper<MatPurchaseOrderEntity>();
		String planName = MapUtil.getStr(params, "planName");
		// Date dateMoth = DateUtils.stringToDate(planMonth,
		// DateUtils.DATE_MOTH_PATTERN);
		wrapper.eq(StrUtil.isNotBlank(planName), "plan_name", planName);
		Page<MatPurchaseOrderEntity> page = this.selectPage(new Query<MatPurchaseOrderEntity>(params).getPage(),
				wrapper);
		return new PageUtils(page);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertOrder(MatPurchaseOrderEntity entity) {
		List<MatPurchaseOrderlistEntity> list = entity.getList();
		//生成订单单据号
		String orderCode = idGenerator.getNewMax(modules.PURCHASE_ORDER.getValue(), 3,
				DateUtils.format(new Date(), "yyyyMMdd"), entity.getProjectId(), null);
		entity.setOrderCode(orderCode);
		this.insert(entity);
		if (list != null && list.size() != 0) {
			for (MatPurchaseOrderlistEntity order : list) {
				order.setOrderId(entity.getId());
				matPurchaseOrderlistService.insert(order);
			}
		}

	}

	@Override
	public void updateOrder(MatPurchaseOrderEntity entity) {
		List<MatPurchaseOrderlistEntity> list = entity.getList();
		baseMapper.updateById(entity);
		String orderId = entity.getId();
		Map<String, Object> columnMap = Maps.newHashMap();
		columnMap.put("order_id", orderId);
		matPurchaseOrderlistService.deleteByMap(columnMap);
		matPurchaseOrderlistService.insertBatch(list);
	}

	@Override
	public MatPurchaseOrderEntity selectInfoById(String id) {
		MatPurchaseOrderEntity info = baseMapper.selectInfoById(id);
		return info;
	}

	@Override
	public PageUtils selectPage(Map<String, Object> params) {
		Query<MatPurchaseOrderEntity> query = new Query<MatPurchaseOrderEntity>(params);
		Page<MatPurchaseOrderEntity> page = query.getPage();
		page.setRecords(baseMapper.selectPage(page, params));
		return new PageUtils(page);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void submit(MatPurchaseOrderEntity entity) {

		MatPurchaseOrderEntity info = baseMapper.selectById(entity.getId());

		// 如果流程存在且未完成
		if (!StringUtils.isEmpty(info.getActivityinsId()) && Objects.equal(info.getCheckStatus(), "0")
				&& Objects.equal(info.getReturnStatus(), "0")) {
			// 完成节点
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setUserId(ShiroUtils.getUserId());
			Map<String, Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			activityService.completeTaskByInsId(info.getActivityinsId(), tmp);

			if (!StringUtils.isEmpty(info.getActivityinsId())) {
				MatPurchaseOrderEntity upInfo = new MatPurchaseOrderEntity();
				upInfo.setId(entity.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");// 审核中
				upInfo.setReturnStatus("0");// 未弃审
				baseMapper.updateById(upInfo);
			}
		} else if ((StringUtils.isEmpty(info.getActivityinsId()) || Objects.equal(info.getReturnStatus(), "1"))
				&& Objects.equal(info.getCheckStatus(), "0")) {// 如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PU_ORDER.value);// 流程定义key
			tmp.setUserId(ShiroUtils.getUserId());

			Map<String, Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);

			Map<String, Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, info.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value,
					ActivityConstant.BillType.ACT_BILL_PU_ORDER.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, info.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);

			if (!StringUtils.isEmpty(activityInsId)) {
				MatPurchaseOrderEntity upInfo = new MatPurchaseOrderEntity();
				upInfo.setId(info.getId());
				upInfo.setActivityinsId(activityInsId);
				upInfo.setCheckStatus("1");// 审核中
				upInfo.setReturnStatus("0");// 未弃审
				baseMapper.updateById(upInfo);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void check(MatPurchaseOrderEntity entity) {
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());

		Map<String, Object> localMap = Maps.newHashMap();
		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_PASS.value);
		taskPojo.setLocalMap(localMap);
		activityService.completeTaskByTaskIdAndMap(taskPojo);

		ProcessInstance pi = activityService.queryProcessInstance(entity.getActivityinsId());
		// 流程结束
		if (pi == null) {
			// 修改单据状态为审核通过
			MatPurchaseOrderEntity upInfo = new MatPurchaseOrderEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("2");// 审核通过
			// upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
		}

	}

	@Override
	public void reject(MatPurchaseOrderEntity entity) {
		// 工作流驳回
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(entity.getTaskId());
		taskPojo.setComment(entity.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());

		// Map<String,Object> localMap = Maps.newHashMap();
		// localMap.put("oper",
		// ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
		// taskPojo.setLocalMap(localMap);

		boolean isReturnSubmit = activityService.backProcess(taskPojo);
		if (isReturnSubmit) {
			// 修改单据状态为自由态
			MatPurchaseOrderEntity upInfo = new MatPurchaseOrderEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");// 自由态
			baseMapper.updateById(upInfo);
		}
	}

	@Override
	public void endReturn(MatPurchaseOrderEntity entity) {
		ProcessInstance ins = activityService.findProcessInstance(entity.getActivityinsId());
		if (ins == null) {// 表示流程结束
			// 修改单据状态为自由态，已弃审
			MatPurchaseOrderEntity upInfo = new MatPurchaseOrderEntity();
			upInfo.setId(entity.getId());
			upInfo.setCheckStatus("0");// 自由态
			upInfo.setReturnStatus("1");// 已弃审
			baseMapper.updateById(upInfo);
		}

	}

	@Override
	public PageUtils getOrderList(Map<String, Object> params) {
		Map<String, String> map = commonService.getCompanyProType(params.get("companyId").toString(),
				params.get("projectId").toString());
		// 如果是总包单位
		if (StringUtil.equals(map.get(Constant.COMPANY_ROLE_TYPE),
				Constant.CompanyProRole.BENERALCONTRACTOR.getType())) {
			params.put(Constant.COMPANY_ROLE_TYPE, map.get(Constant.COMPANY_ROLE_TYPE));
			params.put(Constant.CompanyProRole.CONSTRUCTION.getName(),
					map.get(Constant.CompanyProRole.CONSTRUCTION.getName()));
			params.put(Constant.PRO_ID, map.get(Constant.PRO_ID));
		}
		Query<MatPurchaseOrderEntity> query = new Query<MatPurchaseOrderEntity>(params);
		Page<MatPurchaseOrderEntity> page = query.getPage();
		page.setRecords(baseMapper.getOrderList(page, params));
		return new PageUtils(page);
	}

	@Override
	@Transactional
	public void submitUpdatePlanList(List<MatPurchaseOrderlistEntity> list) {
		for (MatPurchaseOrderlistEntity orderlist : list) {
//			BigDecimal userNumber = orderlist.getUsedNumber();
//			BigDecimal planNumber = orderlist.getPlanNumber();
			//可下单数量
//			BigDecimal subtract = new BigDecimal("0");
//			if(flag ==1) {	
//				if(userNumber!=null) {
//					subtract = planNumber.subtract(userNumber);
//				}else {
//					subtract = planNumber;
//				}
//				if(subtract.compareTo(orderNumber) < 0) {
//					throw new BusinessException("订单材料数量有误");
//				}
//			}
			BigDecimal orderNumber = orderlist.getOrderNumber();
			// 获取该材料对应计划详情id
			String planlistId = orderlist.getPlanlistId();
			// 根据材料对应计划详情id查询并排序
			MatPurchasePlanlistEntity planlistEntity = matPurchasePlanlistService.selectById(planlistId);
			// 根据下单数量 修改对应计划里的使用数量
//			HashMap<String, Object> map = Maps.newHashMap();
//			map.put("id", planlistId); 
//			map.put("orderType", "1");
			BigDecimal usedNumber = planlistEntity.getUsedNumber();
			if(usedNumber == null) {
				usedNumber = new BigDecimal("0");
			}
			if(orderNumber.compareTo(planlistEntity.getPlanNumber().subtract(usedNumber)) <= 0){
				planlistEntity.setUsedNumber(usedNumber.add(orderNumber));
			} else {
				throw new BusinessException("当前可下订单量不够，请检查数量后重新提交");
			}
			planlistEntity.setUsedState("1");
			matPurchasePlanlistService.updateById(planlistEntity);	
		}
	}
	@Override
	@Transactional
	public void submitUpdatePlanSumList(List<MatPurchaseOrderlistEntity> list) {
		for (MatPurchaseOrderlistEntity orderlist : list) {
			BigDecimal orderNumber = orderlist.getOrderNumber();
			// 获取该材料对应计划详情id
			String planlistId = orderlist.getPlanlistId();
			// 根据材料对应计划详情id查询并排序
			MatPurchasePlanSumListEntity planlistEntity = matPurchasePlanSumlistService.selectById(planlistId);
			// 根据下单数量 修改对应计划里的使用数量
//			HashMap<String, Object> map = Maps.newHashMap();
//			map.put("id", planlistId); 
//			map.put("orderType", "1");
			BigDecimal usedNumber = planlistEntity.getUsedNumber();
			if(usedNumber == null) {
				usedNumber = new BigDecimal("0");
			}
			if(orderNumber.compareTo(planlistEntity.getPlanNumber().subtract(usedNumber)) <= 0){
				planlistEntity.setUsedNumber(usedNumber.add(orderNumber));
			} else {
				throw new BusinessException("当前可下订单量不够，请检查数量后重新提交");
			}
			planlistEntity.setUsedState("1");
			matPurchasePlanSumlistService.updateById(planlistEntity);
		}
	
	}
//	private void selectSumUsedNumber(String orderType,List<MatPurchaseOrderlistEntity> list) {
//		for (MatPurchaseOrderlistEntity matPurchaseOrderlistEntity : list) {
//			
//			String planlistId = matPurchaseOrderlistEntity.getPlanlistId();
//			System.out.println(planlistId);
//			List<String> idList = Arrays.asList(planlistId.split(","));
//			HashMap<String, Object> param = Maps.newHashMap();
//			param.put("list", idList);
//			param.put("orderType", orderType);
//			//获取一个清单的已下单数量
//			BigDecimal orderNumber = new BigDecimal(0);
//			BigDecimal selectSumUsedNumber = matPurchaseOrderlistService.selectSumUsedNumber(param);
//			if(matPurchaseOrderlistEntity.getId()!=null) {
//				MatPurchaseOrderlistEntity entity = matPurchaseOrderlistService.selectById(matPurchaseOrderlistEntity.getId());
//				orderNumber = entity.getOrderNumber();
//			} else {
//				orderNumber = matPurchaseOrderlistEntity.getOrderNumber();
//			}
//			
//			//其他订单已下单数
//			BigDecimal otherUsedNumber = new BigDecimal(0);
//			if(selectSumUsedNumber.compareTo(new BigDecimal(0)) > 0) {
//				otherUsedNumber = selectSumUsedNumber.subtract(orderNumber);
//			}
//			
//			//当前可下单数
//			BigDecimal nowNumber = matPurchaseOrderlistEntity.getPlanNumber().subtract(otherUsedNumber);
//			if(matPurchaseOrderlistEntity.getOrderNumber().compareTo(nowNumber) >0) {
//				throw new BusinessException("当前可下单量不足,剩余可下单量为"+nowNumber);
//			}
//			
//		}
//	}
	@Override
	public void rejectList(String id, String orderType) {
		EntityWrapper<MatPurchaseOrderlistEntity> wrapper = new EntityWrapper<MatPurchaseOrderlistEntity>();
		wrapper.eq("order_id", id);
		List<MatPurchaseOrderlistEntity> list = matPurchaseOrderlistService.selectList(wrapper);
		if (StringUtil.equals(orderType, "2")) {
			rejectPlanList(list);
		} else {
			rejectPlanSumList(list);
		}
	}
	/**
	 * 还原计划清单数据
	 * @param list 订单清单
	 */
	private void rejectPlanList(List<MatPurchaseOrderlistEntity> list) {
		for (MatPurchaseOrderlistEntity listEntity : list) {
			
			String planId = listEntity.getPlanlistId();
//			List<String> idList = Arrays.asList(planId.split(","));
			MatPurchasePlanlistEntity planEntity = matPurchasePlanlistService.selectById(planId);
			BigDecimal orderNumber = listEntity.getOrderNumber();
			List<MatPurchasePlanlistEntity> updatelist = new ArrayList<MatPurchasePlanlistEntity>();
				BigDecimal usedNumber = planEntity.getUsedNumber();
				if(usedNumber == null) {
					usedNumber = new BigDecimal("0");
				}
				if (orderNumber.compareTo(usedNumber) <= 0){
					planEntity.setUsedNumber(usedNumber.subtract(orderNumber));
					orderNumber = new BigDecimal(0);
				} else {
					planEntity.setUsedNumber(new BigDecimal(0));
					orderNumber = orderNumber.subtract(usedNumber);
				}
				updatelist.add(planEntity);
			matPurchasePlanlistService.updateById(planEntity);
		}
	}
	/**
	 * 还原汇总清单数据
	 * @param list 订单清单
	 */
	private void rejectPlanSumList(List<MatPurchaseOrderlistEntity> list) {
		for (MatPurchaseOrderlistEntity listEntity : list) {
			String planId = listEntity.getPlanlistId();
			List<String> idList = Arrays.asList(planId.split(","));
			List<MatPurchasePlanSumListEntity> planList = matPurchasePlanSumlistService.selectByIds(idList);
			BigDecimal orderNumber = listEntity.getOrderNumber();
			List<MatPurchasePlanSumListEntity> updatelist = new ArrayList<MatPurchasePlanSumListEntity>();
			for (MatPurchasePlanSumListEntity planlistEntity : planList) {
				BigDecimal usedNumber = planlistEntity.getUsedNumber();
				if(usedNumber == null) {
					usedNumber = new BigDecimal("0");
				}
				if (orderNumber.compareTo(usedNumber) <= 0){
					planlistEntity.setUsedNumber(usedNumber.subtract(orderNumber));
					orderNumber = new BigDecimal(0);
				} else {
					planlistEntity.setUsedNumber(new BigDecimal(0));
					orderNumber = orderNumber.subtract(usedNumber);
				}
				updatelist.add(planlistEntity);
				if (orderNumber.compareTo(new BigDecimal(0)) == 0) {
					break;
				}
			}
			matPurchasePlanSumlistService.updateBatchById(updatelist);
		}
	}
}
