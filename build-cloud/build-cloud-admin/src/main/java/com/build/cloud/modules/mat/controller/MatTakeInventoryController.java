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
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatTakeInventoryEntity;
import com.build.cloud.modules.mat.entity.MatTakeInventorylistEntity;
import com.build.cloud.modules.mat.service.IMatTakeInventoryService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

/**
 * <p>
 * 盘存表 前端控制器
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
@RestController
@RequestMapping("/mat/ti")
public class MatTakeInventoryController extends AbstractController{
	
	@Autowired
	private IMatTakeInventoryService matTakeInventoryService;
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 分页查询盘存表
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/list")
//	@RequiresPermissions("bs:measureunit:list")
	public Result getPage(@RequestParam Map<String, Object> params){
		params.put("companyId", getCurrentCompanyId());
		return Result.ok().put("result", matTakeInventoryService.pageList(params));
	}
	
	/**
	 * 查询出库详情
	 * @return
	 */
	@GetMapping("/v1/outNumberList")
	public Result outNumberList(@RequestParam Map<String, Object> params){
		return Result.ok().put("result", matTakeInventoryService.outNumberList(params));
	}
	
	/**
	 * 查询实际预算消耗量详情
	 * @return
	 */
	@GetMapping("/v1/takeNumberList")
	public Result takeNumberList(@RequestParam Map<String, Object> params){
		return Result.ok().put("result", matTakeInventoryService.takeNumberList(params));
	}
	
	/**
	 * 保存盘存
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
	public Result save(@RequestBody MatTakeInventoryEntity entity){
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			for(MatTakeInventorylistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, AddGroup.class);
			}
			matTakeInventoryService.save(entity);
			return Result.ok("保存成功").put("result", entity);
		} catch (Exception e) {
			logger.error("保存失败", e);
			if (e.getMessage().contains("uk")){
				return Result.error("该项目同一劳务班组该月已经做过盘存");
			}
			return Result.error("保存失败");
		}
	}
	
	/**
	 * 修改盘存
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
	public Result update(@RequestBody MatTakeInventoryEntity entity){
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			for(MatTakeInventorylistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, UpdateGroup.class);
			}
			matTakeInventoryService.update(entity);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败", e);
			return Result.error("修改失败");
		}
	}
	
	/**
	 * 查询盘存信息
	 * @param entity
	 * @return
	 */
	@GetMapping("/v1/info")
	public Result info(@RequestParam String id){
		MatTakeInventoryEntity entity = matTakeInventoryService.info(id);
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
	 * 删除盘存
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String id){
		try {
			String key = getDelId(id);
			matTakeInventoryService.delete(key);
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败", e);
			return Result.error("删除失败");
		}
	}
	
	/**
	 * 查询盘存新增数据
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/takeAddInfo")
	public Result takeAddInfo(@RequestParam Map<String, Object> params){
		return Result.ok().put("result", matTakeInventoryService.takeAddInfo(params));
	}
	
    /**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatTakeInventoryEntity entity) {
   		try {
   			matTakeInventoryService.submit(entity);
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
   	public Result check(@RequestBody MatTakeInventoryEntity entity) {
   		try {
   			matTakeInventoryService.check(entity);
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
   	public Result reject(@RequestBody MatTakeInventoryEntity entity) {
   		try {
   			matTakeInventoryService.reject(entity);
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
   	public Result end(@RequestBody MatTakeInventoryEntity entity) {
   		try {
   			matTakeInventoryService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
	/**
	 * 劳务班组参照
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/teamList")
	public Result getConList(@RequestParam Map<String, Object> params) {
		params.put("companyId", getCurrentCompanyId());
		return Result.ok().put("result", matTakeInventoryService.getTeamList(params));
	}
}

