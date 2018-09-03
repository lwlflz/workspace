package com.build.cloud.modules.mat.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderService;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderlistService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockInService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: MatPurchaseOrderController</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 下午2:12:55
 */
@RestController
@RequestMapping(value="/mat/order")
public class MatPurchaseOrderController extends AbstractController{
	@Autowired
	private IMatPurchaseOrderService matPurchaseOrderService;
	
	@Autowired
	private IMatPurchaseOrderlistService matPurchaseOrderlistService;
	
	@Autowired
	private IMatPurchasePlanlistService matPurchasePlanlistService;
	
	@Autowired
	private IMatPurchaseStockInService matPurchaseStockInService;
	@Autowired
	private ActivityService activityService;
	/**
	 * 采购订单列表
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/list")
//	@RequiresPermissions("mat:order:list")
	public Result list(@RequestParam Map<String,Object> params) {
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils page = matPurchaseOrderService.selectPage(params);
		return Result.ok().put("result",page);
	}
	/**
	 * 保存采购订单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
//	@RequiresPermissions("mat:order:save")
	public Result save(@RequestBody MatPurchaseOrderEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			List<MatPurchaseOrderlistEntity> list = entity.getList();
			for (MatPurchaseOrderlistEntity matPurchaseOrderlistEntity : list) {
				ValidatorUtils.validateEntity(matPurchaseOrderlistEntity, AddGroup.class);
				BigDecimal orderNumber = matPurchaseOrderlistEntity.getOrderNumber();
				if(orderNumber.compareTo(new BigDecimal(0))<0) {
					throw new BusinessException("订单数不能为小于0");
				}
				BigDecimal usedNumber = matPurchaseOrderlistEntity.getUsedNumber();
				if (usedNumber == null) {
					usedNumber = new BigDecimal(0);
				}
				BigDecimal planNumber = matPurchaseOrderlistEntity.getPlanNumber();
				BigDecimal nowNumber = planNumber.subtract(usedNumber);
				if(nowNumber.compareTo(orderNumber) < 0) {
					throw new BusinessException("订单数不可大于可下单量");
				}
			}
//			String orderType = entity.getOrderType();
//			if("2".equals(orderType)) {
//				matPurchaseOrderService.saveUpdatePlanList(list, 1, entity.getId(), orderType);
//			} else if("1".equals(orderType)) {
//				matPurchaseOrderService.saveUpdatePlanSumList(list, 1, entity.getId(), orderType);
//			}
			
			matPurchaseOrderService.insertOrder(entity);
			return Result.ok("保存成功").putObject("id", entity.getId());
		}catch(Exception e) {
			logger.error("保存采购订单异常", e);
			return Result.error("保存失败"+e.getMessage());
		}
		
	}
	/**
	 * 更新采购订单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
//	@RequiresPermissions("mat:order:update")
	public Result update(@RequestBody MatPurchaseOrderEntity entitys) {
		try {
			ValidatorUtils.validateEntity(entitys, UpdateGroup.class);
			
			List<MatPurchaseOrderlistEntity> list = entitys.getList();
			for (MatPurchaseOrderlistEntity matPurchaseOrderlistEntity : list) {
				ValidatorUtils.validateEntity(matPurchaseOrderlistEntity, AddGroup.class);
				BigDecimal orderNumber = matPurchaseOrderlistEntity.getOrderNumber();
				if(orderNumber.compareTo(new BigDecimal(0))<0) {
					throw new BusinessException("订单数不能为小于0");
				}
				BigDecimal usedNumber = matPurchaseOrderlistEntity.getUsedNumber();
				if (usedNumber == null) {
					usedNumber = new BigDecimal(0);
				}
				BigDecimal planNumber = matPurchaseOrderlistEntity.getPlanNumber();
				BigDecimal nowNumber = planNumber.subtract(usedNumber);
				if(nowNumber.compareTo(orderNumber) < 0) {
					throw new BusinessException("订单数不可大于可下单量");
				}
			}
//			String orderType = entitys.getOrderType();
//			if("2".equals(orderType)) {
//				matPurchaseOrderService.saveUpdatePlanList(list, 2, entitys.getId(), orderType);
//			} else if("1".equals(orderType)) {
//				matPurchaseOrderService.saveUpdatePlanSumList(list, 2, entitys.getId(), orderType);
//			}
			matPurchaseOrderService.updateOrder(entitys);
			return Result.ok("更新成功").putObject("id", entitys.getId());
		}catch(Exception e) {
			logger.error("更新采购订单异常", e);
			return Result.error("更新失败"+e.getMessage());
		}
		
	}
	/**
	 * 获取采购订单信息
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/info")
//	@RequiresPermissions("mat:order:info")
	public Result info(String id) {
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		MatPurchaseOrderEntity entity = matPurchaseOrderService.selectInfoById(id);
		
		if(entity!=null&&!StringUtils.isEmpty(entity.getActivityinsId())
				&&!Objects.equal(entity.getReturnStatus(), "1")){
			//设置是否允许弃审
			String endChecker = activityService.getEndChecker(entity.getActivityinsId());
			if(Objects.equal(ShiroUtils.getUserId(), endChecker)
					&&Objects.equal(entity.getCheckStatus(), "2")){
				entity.setEndCheckerFlag(true);
			}
			//设置当前审核人id
			List<String> checkerList = activityService.findNowChecker(entity.getActivityinsId());
			if(checkerList!=null&&checkerList.size()>0){
				entity.setNowCheckerList(checkerList);
			}
			//会签时,工作流当前任务列表,当前用户等于任务办理人时,取此任务的任务id为执行任务id
			List<Task> taskList = activityService.getNowTaskList(entity.getActivityinsId());
			for (Task task : taskList) {
				if(Objects.equal(task.getAssignee(), ShiroUtils.getUserId())){
					entity.setTaskId(task.getId());
				}
			}
		}
		if(entity != null) {
			List<MatPurchaseOrderlistEntity> list = matPurchaseOrderlistService.queryList(entity.getId());
			entity.setList(list);
		}
		return Result.ok().putEntity(entity);
	}
	/**
	 * 删除采购订单
	 * @param str
	 * @return
	 */
	@PostMapping("/v1/delete")
//	@RequiresPermissions("mat:order:delete")
	public Result delete(@RequestBody String str) {
		String id = getDelId(str);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
//		MatPurchaseOrderEntity orderEntity = matPurchaseOrderService.selectById(id);
//		删除恢复数据
//		if(orderEntity != null) {
//			String orderType = orderEntity.getOrderType();
//			matPurchaseOrderService.rejectUpdatePlanList(id, orderType);
//		}
		matPurchaseOrderService.deleteById(id);
		matPurchaseOrderlistService.deleteOrderId(id);
		return Result.ok("删除成功");
	}
//	@GetMapping("/v1/orderlist")
//	public Result orderlist(Map<String,Object> params) {
//		PageUtils page = matPurchaseOrderlistService.queryPage(params);
//		return Result.ok().put("result",page);
//	}
	/**
	 * 保存采购订单详情
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/savelist")
//	@RequiresPermissions("mat:orderlist:save")
	public Result saveList(@RequestBody MatPurchaseOrderlistEntity entity) {
		ValidatorUtils.validateEntity(entity, AddGroup.class);
		matPurchaseOrderlistService.insert(entity);
		return Result.ok();
	}
	/**
	 * 更新采购订单详情
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/updatelist")
//	@RequiresPermissions("mat:orderlist:update")
	public Result updateList(@RequestBody MatPurchaseOrderlistEntity entity) {
		ValidatorUtils.validateEntity(entity, UpdateGroup.class);
		matPurchaseOrderlistService.updateAllColumnById(entity);
		return Result.ok();
	}
	/**
	 * 获取采购订单详情信息
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/infolist")
//	@RequiresPermissions("mat:orderlist:info")
	public Result infoList(String id) {
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		EntityWrapper<MatPurchaseOrderlistEntity> wrapper = new EntityWrapper<MatPurchaseOrderlistEntity>();
		wrapper.eq("order_id", id);
		List<MatPurchaseOrderlistEntity> list = matPurchaseOrderlistService.selectList(wrapper);
		return Result.ok().putEntity(list);
	}
	/**
	 * 删除采购订单详情
	 * @param str
	 * @return
	 */
	@PostMapping("/v1/deletelist")
//	@RequiresPermissions("mat:orderlist:delete")
	public Result deleteList(@RequestBody String str) {
		String id = getDelId(str);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		matPurchaseOrderlistService.deleteById(id);
		return Result.ok();
	}
	 /**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatPurchaseOrderEntity entity) {
   		try {
   			entity = matPurchaseOrderService.selectById(entity.getId());
			String orderType = entity.getOrderType();
			EntityWrapper<MatPurchaseOrderlistEntity> wrapper = new EntityWrapper<MatPurchaseOrderlistEntity>();
			wrapper.eq("order_id", entity.getId());
			List<MatPurchaseOrderlistEntity> list = matPurchaseOrderlistService.selectList(wrapper);
			if("2".equals(orderType)) {
				matPurchaseOrderService.submitUpdatePlanList(list);
			} else if("1".equals(orderType)) {
				matPurchaseOrderService.submitUpdatePlanSumList(list);
			}
   			matPurchaseOrderService.submit(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("提交审核异常", e);
   			return Result.error("提交失败："+e.getMessage());
   		}
   	}
    
    /**
     * 审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actCheck")
   	public Result check(@RequestBody MatPurchaseOrderEntity entity) {
   		try {
   			matPurchaseOrderService.check(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("审核异常", e);
   			return Result.error("审核异常："+e.getMessage());
   		}
   	}
    
    /**
     * 驳回
     * @param info
     * @return
     */
    @PostMapping("/v1/actReject")
   	public Result reject(@RequestBody MatPurchaseOrderEntity entity) {
   		try {
   			matPurchaseOrderService.reject(entity);
   			entity = matPurchaseOrderService.selectById(entity.getId());
			String orderType = entity.getOrderType();
			matPurchaseOrderService.rejectList(entity.getId(), orderType);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("驳回异常", e);
   			return Result.error("驳回异常："+e.getMessage());
   		}
   	}
    
    /**
     * 弃审
     * @param info
     * @return
     */
    @PostMapping("/v1/actEndReturn")
   	public Result end(@RequestBody MatPurchaseOrderEntity entity) {
   		try {
   			String id = entity.getId();
   			EntityWrapper<MatPurchaseStockInEntity> wrapper = new EntityWrapper<MatPurchaseStockInEntity>();
   			wrapper.eq("order_id", id);
   			int count = matPurchaseStockInService.selectCount(wrapper);
   			if (count > 0) {
   				return Result.error("已被下游单据引用不可弃审");
			}
   			matPurchaseOrderService.endReturn(entity);
   			entity = matPurchaseOrderService.selectById(id);
   			String orderType = entity.getOrderType();
   			matPurchaseOrderService.rejectList(entity.getId(), orderType);
   			return Result.ok("取消审批成功");
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
    
    /**
     * 采购订单参照
     * @param params
     * @return
     */
    @GetMapping("/v1/orderList")
    public Result getOrderList(@RequestParam Map<String, Object> params) {
    	params.put("companyId", getCurrentCompanyId());
    	return Result.ok().put("result", matPurchaseOrderService.getOrderList(params));
    }
    
}    
