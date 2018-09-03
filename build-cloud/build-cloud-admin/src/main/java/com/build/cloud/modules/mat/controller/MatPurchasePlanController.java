package com.build.cloud.modules.mat.controller;

import java.util.HashMap;
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
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.sys.entity.SysCompanyEntity;
import com.build.cloud.modules.sys.service.ISysCompanyService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: MatPurchasePlanController</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午3:28:23
 */
@RestController
@RequestMapping(value="/mat/plan")
public class MatPurchasePlanController extends AbstractController{
	@Autowired
	private IMatPurchasePlanService matPurchasePlanService;
	@Autowired 
	private IMatPurchasePlanlistService matPurchasePlanlistService;
	@Autowired
	private IBsProjectService bsProjectService;
	@Autowired
	private ISysCompanyService sysCompanyService;
	
	@Autowired
	private ActivityService activityService;
	/**
	 * 月度采购计划列表
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/list")
	public Result list(@RequestParam Map<String,Object> params) {
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils page = matPurchasePlanService.selectPage(params);
		return Result.ok().put("result",page);
	}
//	/**
//	 * 参照月度采购计划列表
//	 */
//	@GetMapping("/v1/referlist")
//	public Result referList(@RequestParam Map<String,Object> params) {
//		String companyId = getCurrentCompanyId();
//		params.put("companyId", companyId);
//		PageUtils page = matPurchasePlanService.selectReferList(params);
//		return Result.ok().put("result",page);
//	}
	/**
	 * 保存月度采购计划
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
	public Result save(@RequestBody MatPurchasePlanEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			String companyId = getCurrentCompanyId();
			entity.setCompanyId(companyId);
			List<MatPurchasePlanlistEntity> list = entity.getList();
			
			for(MatPurchasePlanlistEntity plan:list) {
				ValidatorUtils.validateEntity(plan, AddGroup.class);
			}
			matPurchasePlanService.insertPurchasePlan(entity);
			
			return Result.ok("保存成功").putObject("id", entity.getId());
		}catch(Exception e) {
			logger.error("保存月度采购计划异常", e);
			if(e.getMessage().contains("uq")) {
				return Result.error("该项目当月采购计划已经存在");
			}
			return Result.error("保存失败"+e.getMessage());
		}
		
	}
	/**
	 * 更新月度采购计划
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
	public Result update(@RequestBody MatPurchasePlanEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			List<MatPurchasePlanlistEntity> list = entity.getList();
			for(MatPurchasePlanlistEntity plan:list) {
				ValidatorUtils.validateEntity(plan, AddGroup.class);
			}
			matPurchasePlanService.updatePurchasePlan(entity);
			return Result.ok("更新成功").putObject("id", entity.getId());
		}catch(Exception e) {
			logger.error("更新月度采购计划异常", e);
			return Result.error("更新失败");
		}
	
	}
	/**
	 * 获取月度采购计划信息
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/info")
	public Result info(@RequestParam String id) {
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		try {
			MatPurchasePlanEntity entity = matPurchasePlanService.selectById(id);
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
			//根据计划id查询项目
			BsProjectEntity projectEntity = bsProjectService.selectById(entity.getProjectId());
			//根据项目id查询总包单位
			SysCompanyEntity companyEntity = sysCompanyService.selectById(projectEntity.getGeneralcontractorId());
			entity.setMainConName(companyEntity.getCompanyName());
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("planId",entity.getId());
			List<MatPurchasePlanlistEntity> queryList = matPurchasePlanlistService.queryList(params);
			for(MatPurchasePlanlistEntity plan:queryList) {
				plan.setPurchaseType(entity.getPurchaseType());
			}
			entity.setList(queryList);
			return Result.ok().putEntity(entity);
		}catch(Exception e){
			logger.error("查询月度采购计划异常", e);
			return Result.error("查询失败");
		}
		
		
	}
	/**
	 * 删除月度采购计划
	 * @param str
	 * @return
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String str) {
		String id = getDelId(str);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		matPurchasePlanService.deleteById(id);
		return Result.ok();
	}
	@GetMapping("/v1/planlist")
	public Result planlist(@RequestParam Map<String,Object> params) {
		List<MatPurchasePlanlistEntity> list = matPurchasePlanlistService.queryList(params);
		return Result.ok().put("result",list);
	}
	/**
	 * 保存月度采购计划清单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/savelist")
	public Result saveList(@RequestBody List<MatPurchasePlanlistEntity> entitys) {
		for(MatPurchasePlanlistEntity entity :entitys) {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
		}
		matPurchasePlanlistService.insertBatch(entitys);
		return Result.ok();
	}
	/**
	 * 更新月度采购计划清单
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/updatelist")
	public Result updateList(@RequestBody MatPurchasePlanlistEntity entity) {
		ValidatorUtils.validateEntity(entity, UpdateGroup.class);
		matPurchasePlanlistService.updateAllColumnById(entity);
		return Result.ok();
	}
	/**
	 * 获取月度采购计划清单
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/infolist")
	public Result infoList(String planId) {
		if(StrUtil.isBlank(planId)){
			return Result.error("ID不能为空");
		}
		EntityWrapper<MatPurchasePlanlistEntity> wrapper = new EntityWrapper<MatPurchasePlanlistEntity>();
		wrapper.where("(used_number <> plan_number or used_number is null) and plan_id = {0}", planId);
		List<MatPurchasePlanlistEntity> list = matPurchasePlanlistService.selectList(wrapper);
		return Result.ok().putEntity(list);
	}
	/**
	 * 删除月度采购计划清单
	 * @param str
	 * @return
	 */
	@PostMapping("/v1/deletelist")
	public Result deleteList(@RequestBody String str) {
		String id = getDelId(str);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		matPurchasePlanlistService.deleteById(id);
		return Result.ok();
	}
	 /**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatPurchasePlanEntity entity) {
   		try {
   			matPurchasePlanService.submit(entity);
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
   	public Result check(@RequestBody MatPurchasePlanEntity entity) {
   		try {
   			matPurchasePlanService.check(entity);
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
   	public Result reject(@RequestBody MatPurchasePlanEntity entity) {
   		try {
   			matPurchasePlanService.reject(entity);
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
   	public Result end(@RequestBody MatPurchasePlanEntity entity) {
   		try {
   			String id = entity.getId();
   			EntityWrapper<MatPurchasePlanlistEntity> wrapper = new EntityWrapper<MatPurchasePlanlistEntity>();
   			wrapper.eq("plan_id", id);
   			wrapper.eq("used_state", 1);
   			int count = matPurchasePlanlistService.selectCount(wrapper);
   			if (count > 0) {
				return Result.error("已被下游单据引用不可弃审");
			}
   			matPurchasePlanService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
    
    /**
     * 采购计划汇总参照
     * @param params
     * @return
     */
    @GetMapping("/v1/getPlanList")
    public Result getPlanList(@RequestParam Map<String, Object> params){
    	params.put("companyId", getCurrentCompanyId());
    	return Result.ok().put("result", matPurchasePlanService.getPlanList(params));
    }
}
