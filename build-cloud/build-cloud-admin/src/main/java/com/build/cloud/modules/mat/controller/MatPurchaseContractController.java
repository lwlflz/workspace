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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.id.IdGenerator;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatPurchaseConlistEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseContractEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseConlistService;
import com.build.cloud.modules.mat.service.IMatPurchaseContractService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * <p>
 * 物资采购合同文本表 前端控制器
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
@RestController
@RequestMapping("/mat/pcon")
public class MatPurchaseContractController extends AbstractController{
	
	@Autowired
	private IMatPurchaseContractService matPurchaseContractService;
	
	@Autowired
	private IMatPurchaseConlistService matPurchaseConlistService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	/**
	 * 分页查询物资采购合同文本
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/typeList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result queryPageList(@RequestParam Map<String, Object> params){
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils pageUtils = matPurchaseContractService.queryPageList(params);
		return Result.ok().put("result", pageUtils);
	}
	
	/**
	 * 保存物资采购合同文本
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
//	@RequiresPermissions("bs:measureunit:list")
	public Result save(@RequestBody MatPurchaseContractEntity entity){
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			String conCode = idGenerator.getNewMax(
					Constant.modules.PURCHASE_CONTRACT.getValue(),
					2,
					Constant.conCodeMap.get(entity.getConType())
							+ entity.getProjectCode(), entity.getProjectId(),
					entity.getConType());
			entity.setConCode(conCode);
			entity = matPurchaseContractService.save(entity);
			return Result.ok().put("result", entity);
		} catch (Exception e) {
			logger.error("保存物资采购合同文本异常", e);
			return Result.error("保存物资采购合同文本失败");
		}
	}
	
	/**
	 * 保存物资采购合同清单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/saveList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result saveList(@RequestBody List<MatPurchaseConlistEntity> entitys){
		try {
			for(MatPurchaseConlistEntity entity : entitys) {
				ValidatorUtils.validateEntity(entity, AddGroup.class);
			}
			matPurchaseConlistService.update(entitys);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存物资采购合同清单异常", e);
			return Result.error("保存物资采购合同清单失败");
		}
	}
	
	/**
	 * 修改物资采购合同文本
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
//	@RequiresPermissions("bs:measureunit:list")
	public Result update(@RequestBody MatPurchaseContractEntity entity){
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			matPurchaseContractService.update(entity);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改物资采购合同文本异常", e);
			return Result.error("修改失败");
		}
	}
	
	/**
	 * 修改物资采购合同清单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/updateList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result updateList(@RequestBody List<MatPurchaseConlistEntity> entitys){
		try {
			for(MatPurchaseConlistEntity entity : entitys) {
				ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			}
			matPurchaseConlistService.update(entitys);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改物资采购合同清单异常", e);
			return Result.error("修改失败");
		}
	}
	
	/**
	 * 查询物资采购合同文本信息
	 * @return
	 */
	@GetMapping("/v1/info")
//	@RequiresPermissions("bs:measureunit:list")
	public Result info(@RequestParam String id){
		MatPurchaseContractEntity entity = matPurchaseContractService.info(id);
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
	 * 查询物资采购合同清单信息
	 * @return
	 */
	@GetMapping("/v1/infoList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result infoList(@RequestParam String id){
		EntityWrapper<MatPurchaseConlistEntity> wrapper = new EntityWrapper<MatPurchaseConlistEntity>();
		wrapper.eq("con_id", id);
		return Result.ok().put("result", matPurchaseConlistService.selectList(wrapper));
	}
	
	/**
	 * 删除物资采购合同文本信息
	 * @return
	 */
	@PostMapping("/v1/delete")
//	@RequiresPermissions("bs:measureunit:list")
	public Result delete(@RequestBody String id){
		try {
			String key = getDelId(id);
			matPurchaseContractService.delete(key);
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除物资采购合同文本信息失败", e);
			return Result.error("删除失败");
		}
	}
	
	/**
	 * 删除物资采购合同清单信息
	 * @return
	 */
	@PostMapping("/v1/deleteList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result deleteList(@RequestBody String id){
		try {
			String key = getDelId(id);
			matPurchaseConlistService.deleteAsId(key);
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除物资采购合同清单信息失败", e);
			return Result.error("删除失败");
		}
	}
	
	/**
	 * 已采购清单
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/happenedPuList")
//	@RequiresPermissions("bs:measureunit:list")
	public Result happenedPuList(@RequestParam String id) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("conId", id);
		return Result.ok().put("result", matPurchaseContractService.happenedPuList(params));
	}
	
    /**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatPurchaseContractEntity entity) {
   		try {
   			matPurchaseContractService.submit(entity);
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
   	public Result check(@RequestBody MatPurchaseContractEntity entity) {
   		try {
   			matPurchaseContractService.check(entity);
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
   	public Result reject(@RequestBody MatPurchaseContractEntity entity) {
   		try {
   			matPurchaseContractService.reject(entity);
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
   	public Result end(@RequestBody MatPurchaseContractEntity entity) {
   		try {
   			matPurchaseContractService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
    
    /**
     * 物资采购合同首页数据查询
     * @param params
     * @return
     */
    @GetMapping("/v1/indexList")
    public Result selectIndexList(@RequestParam Map<String, Object> params){
    	String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils pageUtils = matPurchaseContractService.selectIndexPage(params);
		return Result.ok().put(pageUtils);
    }
	
}

