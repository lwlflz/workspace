package com.build.cloud.modules.mat.controller;


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

import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.id.IdGenerator;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockInService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

/**
 * <p>
 * 入库表 前端控制器
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@RestController
@RequestMapping("/mat/psi")
public class MatPurchaseStockInController extends AbstractController{
	
	@Autowired
	private IMatPurchaseStockInService matPurchaseStockInService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 分页查询入库信息
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/list")
//	@RequiresPermissions("bs:measureunit:list")
	public Result queryPage(@RequestParam Map<String, Object> params) {
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		return Result.ok().put("result", matPurchaseStockInService.queryPageList(params));
	}
	
	/**
	 * 查询入库信息
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/info")
//	@RequiresPermissions("bs:measureunit:list")
	public Result info(@RequestParam String id){
		MatPurchaseStockInEntity entity = matPurchaseStockInService.info(id);
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
		return Result.ok().put("result", entity);
	}
	
	/**
	 * 保存入库信息
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
//	@RequiresPermissions("bs:measureunit:list")
	public Result save(@RequestBody MatPurchaseStockInEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			for (MatPurchaseStockInlistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, AddGroup.class);
			}
			entity.setBillCode(idGenerator.getNewMax(
					Constant.modules.PURCHASE_STOCK_IN.getValue(), 3,
					DateUtils.getDateStr(), entity.getProjectId(),null));
			return Result.ok("保存成功").put("result", matPurchaseStockInService.save(entity));
		} catch (Exception e) {
			logger.error("保存入库信息失败", e);
			return Result.error("保存入库信息失败");
		}
	}
	
	
	/**
	 * 修改入库信息
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
//	@RequiresPermissions("bs:measureunit:list")
	public Result update(@RequestBody MatPurchaseStockInEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			for (MatPurchaseStockInlistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, UpdateGroup.class);
			}
			matPurchaseStockInService.update(entity);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改入库信息失败", e);
			return Result.error("修改入库信息失败");
		}
	}
	
	/**
	 * 删除入库信息
	 * @param id
	 * @return
	 */
	@PostMapping("/v1/delete")
//	@RequiresPermissions("bs:measureunit:list")
	public Result delete(@RequestBody String id) {
		try {
			String key = getDelId(id);
			matPurchaseStockInService.deleteById(key);
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败", e);
			return Result.error("删除失败");
		}
	}
	
    /**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatPurchaseStockInEntity entity) {
   		try {
   			matPurchaseStockInService.submit(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("提交审核异常", e);
   			return Result.error("提交审核异常："+e.getMessage());
   		}
   	}
    
    /**
     * 审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actCheck")
   	public Result check(@RequestBody MatPurchaseStockInEntity entity) {
   		try {
   			matPurchaseStockInService.check(entity);
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
   	public Result reject(@RequestBody MatPurchaseStockInEntity entity) {
   		try {
   			matPurchaseStockInService.reject(entity);
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
   	public Result end(@RequestBody MatPurchaseStockInEntity entity) {
   		try {
   			matPurchaseStockInService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}

}

