package com.build.cloud.modules.mat.controller;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumlistService;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;
import com.build.cloud.modules.sys.service.ISysCompanyProjectService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/mat/purchaseplansum")
public class MatPurchasePlanSumController extends AbstractController{
	
	@Autowired
	private IMatPurchasePlanSumService purchasePlanSumService;
	
	@Autowired
	private IMatPurchasePlanSumlistService purchasePlanSumlistService;
	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	@Autowired
	private ISysCompanyProjectService sysCompanyProjectService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private IMatPurchasePlanlistService purchasePlanlistService;
	/**
	 * 月度采购计划汇总--列表分页
	 */
	@GetMapping("/v1/pageList")
	public Result pageList(@RequestParam Map<String, Object> params){
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils page = purchasePlanSumService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 月度采购计划汇总--数据列表（无分页）
	 */
	@GetMapping("/v1/list")
	public Result list(@RequestParam Map<String,Object> params){
		params.put("companyId", getCurrentCompanyId());
		List<Map<String,Object>> list = purchasePlanSumService.getPurchasePlanSumList(params);
		return Result.ok().putList(list);
	}
	
	/**
	 * 月度采购计划汇总 --详情
	 */
	@GetMapping("/v1/info")
	public Result info(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		MatPurchasePlanSumEntity entity = purchasePlanSumService.getPurchasePlanSumById(id);
		if(entity != null) {
			EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
			wrapper.eq("plan_id", entity.getId());
			List<MatPurchasePlanSumListEntity> list = purchasePlanSumlistService.selectList(wrapper);
			entity.setPlanSumListCollection(list);
		}
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
		return Result.ok().putEntity(entity);
	}
	
	/**
	 * 月度采购计划汇总--保存
	 */
	@PostMapping("/v1/save")
	@Transactional
	public Result save(@RequestBody MatPurchasePlanSumEntity entity){
		ValidatorUtils.validateEntity(entity, AddGroup.class);
		List<MatPurchasePlanSumListEntity> list = entity.getPlanSumListCollection();
		for (MatPurchasePlanSumListEntity purchasePlanSumListEntity : list) {
			ValidatorUtils.validateEntity(purchasePlanSumListEntity, AddGroup.class);
		}
		
		purchasePlanSumService.savePurchasePlanSum(entity);
//		if(entity != null && !CollectionUtil.isEmpty(entity.getPlanSumListCollection())) {
//			for (MatPurchasePlanSumListEntity matPurchasePlanSumListEntity : list) {
//				matPurchasePlanSumListEntity.setPlanId(entity.getId());
//			}
//			purchasePlanSumlistService.insertBatch(list);
//		}
		return Result.ok("保存成功").putObject("id", entity.getId());
	}
	
	/**
	 * 月度采购计划汇总--删除
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
//		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
//		wrapper.eq("plan_id", id);
//		int count = purchasePlanSumlistService.selectCount(wrapper);
//		if(count > 0) {
//			return Result.error("该记录已关联数据，请先删除关联数据");
//		}
		purchasePlanSumService.restorePlan(id);
		purchasePlanSumService.deleteById(id);
		return Result.ok("删除成功");
	}
	/**
	 * 月度采购计划汇总--修改
	 */
	@PostMapping("/v1/update")
	@Transactional
	public Result update(@RequestBody MatPurchasePlanSumEntity entity) {
		if(entity == null) {
			return Result.error("请选择数据进行操作");
		}
		ValidatorUtils.validateEntity(entity, AddGroup.class);
		List<MatPurchasePlanSumListEntity> planSumList = entity.getPlanSumListCollection();
		for (MatPurchasePlanSumListEntity purchasePlanSumListEntity : planSumList) {
			ValidatorUtils.validateEntity(purchasePlanSumListEntity, AddGroup.class);
		}
//		EntityWrapper<MatPurchasePlanSumEntity> wrapper = new EntityWrapper<MatPurchasePlanSumEntity>();
//		wrapper.eq("bill_code", entity.getBillCode());
//		wrapper.notIn("id", entity.getId());
//		List<MatPurchasePlanSumEntity> list = purchasePlanSumService.selectList(wrapper);
//		if(!CollectionUtil.isEmpty(list)) {
//			return Result.error("已有重复记录，请检查参数");
//		}
		purchasePlanSumService.updatePurchasePlanSum(entity);
		return Result.ok("修改成功");
	}
	
	/***************************************月度采购计划汇总清单**********************************************/

	
	/**
	 * 月度采购计划汇总清单--列表分页
	 */
	@GetMapping("/v1/listingPageList")
	public Result listingPageList(@RequestParam Map<String, Object> params){
		String planId = (String)params.get("planId");
		if(StrUtil.isBlank(planId)) {
			return Result.error("采购计划汇总ID不能为空");
		}
		PageUtils page = purchasePlanSumlistService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 月度采购计划汇总清单--数据列表（无分页）
	 */
	@GetMapping("/v1/listingList")
	public Result listingList(@RequestParam Map<String, Object> params){
		String planId = (String)params.get("planId");
		if(StrUtil.isBlank(planId)) {
			return Result.error("采购计划汇总ID不能为空");
		}
		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
		wrapper.eq("plan_id", (String)params.get("planId"));
		List<MatPurchasePlanSumListEntity> list = purchasePlanSumlistService.selectList(wrapper);
		return Result.ok().putList(list);
	}
	
	/**
	 * 月度采购计划汇总清单 --详情
	 */
	@GetMapping("/v1/listingInfo")
	public Result listingInfo(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		MatPurchasePlanSumListEntity entity = purchasePlanSumlistService.selectById(id);
		return Result.ok().putEntity(entity);
	}
	/**
	 * 
	 */
	@GetMapping("v1/infolist")
	public Result infolist(String planId) {
		if(StrUtil.isBlank(planId)) {
			return Result.error();
		}
		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
		wrapper.where("(used_number <> plan_number or used_number is null) and plan_id = {0}", planId);
		List<MatPurchasePlanSumListEntity> list = purchasePlanSumlistService.selectList(wrapper);
		return Result.ok().putEntity(list);
	}
	/**
	 * 月度采购计划汇总清单--保存
	 */
	@PostMapping("/v1/listingSave")
	@Transactional
	public Result listingSave(@RequestBody List<MatPurchasePlanSumListEntity> list){
		for (MatPurchasePlanSumListEntity matPurchasePlanSumListEntity : list) {
			ValidatorUtils.validateEntity(matPurchasePlanSumListEntity);
		}
		if(CollectionUtil.isEmpty(list)) {
			return Result.error("参数不能为空");
		}
		purchasePlanlistService.updatePlanlist(list);
		purchasePlanSumlistService.insertBatch(list);
		return Result.ok();
	}
	
	/**
	 * 月度采购计划汇总清单--删除
	 */
	@PostMapping("/v1/listingDelete")
	public Result listingDelete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		purchasePlanSumlistService.deleteById(id);
		return Result.ok();
	}
//	/**
//	 * 月度采购计划汇总清单--修改
//	 */
//	@PostMapping("/v1/update")
//	@Transactional
//	public Result update(@RequestBody MatPurchasePlanSumListEntity entity) {
//		if(entity == null) {
//			return Result.error("请选择数据进行操作");
//		}
//		purchasePlanSumlistService.updateAllColumnById(entity);
//		return Result.ok();
//	}
	
	/**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody MatPurchasePlanSumEntity entity) {
   		try {
   			purchasePlanSumService.submit(entity);
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
   	public Result check(@RequestBody MatPurchasePlanSumEntity entity) {
   		try {
   			purchasePlanSumService.check(entity);
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
   	public Result reject(@RequestBody MatPurchasePlanSumEntity entity) {
   		try {
   			purchasePlanSumService.reject(entity);
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
   	public Result end(@RequestBody MatPurchasePlanSumEntity entity) {
   		try {
   			EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
   			wrapper.eq("plan_id", entity.getId());
   			wrapper.eq("used_state", "1");
   			int count = purchasePlanSumlistService.selectCount(wrapper);
   			if(count > 0) {
   				return Result.error("已被下游单据引用不可弃审");
   			}
   			purchasePlanSumService.restorePlan(entity.getId());
   			purchasePlanSumService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
	
    /**
     * 参照接口
     * @param params
     * @return
     */
    @GetMapping("/v1/planSumList")
    public Result getPlanSumList(@RequestParam Map<String, Object> params) {
    	params.put("companyId", getCurrentCompanyId());
		return Result.ok().put("result", purchasePlanSumService.getPlanSumList(params));
    }
    
}
