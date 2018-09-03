package com.build.cloud.modules.ls.controller;

import java.util.HashMap;
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
import com.beust.jcommander.internal.Maps;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.id.IdGenerator;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.ls.entity.LsContractLaborEntity;
import com.build.cloud.modules.ls.entity.LsContractLaborPaymentEntity;
import com.build.cloud.modules.ls.service.ILsContractLaborPaymentService;
import com.build.cloud.modules.ls.service.ILsContractLaborService;
import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanService;
import com.build.cloud.modules.mat.service.IMatPurchaseStockOutService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/ls/contractlabor")
public class LsContractLaborController extends AbstractController{

	@Autowired
	private ILsContractLaborService lsContractLaborService;
	
	@Autowired
	private ILsContractLaborPaymentService lsContractLaborPaymentService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private IBsProjectService projectService;
	
	@Autowired
	private IMatPurchasePlanService purchasePlanService;
	
	@Autowired
	private IMatPurchaseStockOutService purchaseStockOutService;
	
	@Autowired
	private ICommonService commonService;
	/**
	 * 劳务分包合同--列表分页
	 */
	@GetMapping("/v1/pageList")
	public Result pageList(@RequestParam Map<String, Object> params){
		params.put("companyId", getCurrentCompanyId());
		PageUtils page = lsContractLaborService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 劳务分包合同--列表分页(不区分收入支出)
	 */
	@GetMapping("/v1/pageAll")
	public Result pageAll(@RequestParam Map<String, Object> params){
		params.put("companyId", getCurrentCompanyId());
		PageUtils page = lsContractLaborService.queryPageAll(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 劳务分包合同--数据列表（无分页）
	 */
	@GetMapping("/v1/list")
	public Result list(@RequestParam Map<String, Object> params){
		String conType = (String)params.get("conType");
		String projectId = (String)params.get("projectId");
		String companyId = getCurrentCompanyId();
		Map<String, Object> map = Maps.newHashMap();
		map.put("conType", conType);
		map.put("projectId", projectId);
		map.put("companyId", companyId);
		map.put("returnStatus", 0);
		map.put("checkStatus", 2);
		List<Map<String, Object>> list = lsContractLaborService.getConLaborList(params);
		return Result.ok().putList(list);
	}
	
	/**
	 * 劳务分包合同 --详情
	 */
	@GetMapping("/v1/info")
	public Result info(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		LsContractLaborEntity entity = lsContractLaborService.getContractLaborById(id);
		if(entity == null){
			return Result.error("未查询到数据信息");
		}
		
		Map<String, String> map = commonService.getCompanyProType(getCurrentCompanyId(), entity.getProjectId());
		
		entity.setCompanyRoleType(map.get(Constant.COMPANY_ROLE_TYPE));
		
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
	 * 劳务分包合同--保存
	 */
	@PostMapping("/v1/save")
	@Transactional
	public Result save(@RequestBody LsContractLaborEntity entity){
		ValidatorUtils.validateEntity(entity, AddGroup.class);
		checkData(entity);
		List<LsContractLaborPaymentEntity> contractLaborPaymentList = entity.getConPaymentList();
		if(!CollectionUtil.isEmpty(contractLaborPaymentList)) {
			for (LsContractLaborPaymentEntity lsContractLaborPaymentEntity : contractLaborPaymentList) {
				ValidatorUtils.validateEntity(lsContractLaborPaymentEntity, AddGroup.class);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("con_code", entity.getConCode());
		map.put("con_name", entity.getConName());
		List<LsContractLaborEntity> lsContractLaborList = lsContractLaborService.selectByMap(map);
		if(!CollectionUtil.isEmpty(lsContractLaborList)){
			return Result.error("记录已经存在请检查后重试");
		}
		if(entity != null &&  StrUtil.isBlank(entity.getConType())) {
			return Result.error("请选择合同类型");
		}
		//此处项目编码由前端直接传过来不需要再查库
		String conCode = idGenerator.getNewMax(
				Constant.modules.LS_CONTRACT_LABOR.getValue(), 3,
				Constant.LS_CONCODE + entity.getProjectCode(), entity.getProjectId(),
				entity.getConType());
		entity.setConCode(conCode);
		
//		BsProjectEntity projectEntity = projectService.selectById(entity.getProjectId());
//		String prefix = Constant.LS_CONCODE + projectEntity.getProjectCode();
//		String conCode = idGenerator.getNewMax("LS_CONTRACT_LABOR", 3, prefix, entity.getProjectId(), entity.getConType());
//		entity.setConCode(conCode);
		
		lsContractLaborService.insert(entity);
		if(entity != null && !CollectionUtil.isEmpty(contractLaborPaymentList)) {
			for (LsContractLaborPaymentEntity lsContractLaborPaymentEntity : contractLaborPaymentList) {
				lsContractLaborPaymentEntity.setConId(entity.getId());
			}
			lsContractLaborPaymentService.insertBatch(contractLaborPaymentList);
		}
		
		return Result.ok().putObject("id", entity.getId());
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		/*************************检查是否与劳务分包付款数据关联****************************/
		
		LsContractLaborEntity entity = lsContractLaborService.selectById(id);
		if(entity != null && StrUtil.isBlank(entity.getCheckStatus()) && !"0".equals(entity.getCheckStatus())) {
			return Result.error("合同正在进行审批流程，不能进行删除");
		}
		List<MatPurchasePlanEntity> purchasePlanList = purchasePlanService.selectList(new EntityWrapper<MatPurchasePlanEntity>().eq("work_con_id", id));
		if(!CollectionUtil.isEmpty(purchasePlanList)) {
			return Result.error("该条数据已与月度采购计划进行关联，不能进行删除，请检查");
		}
		List<MatPurchaseStockOutEntity> purchaseStockOutList = purchaseStockOutService.selectList(new EntityWrapper<MatPurchaseStockOutEntity>().eq("work_ct_id", id));
		if(!CollectionUtil.isEmpty(purchaseStockOutList)) {
			return Result.error("该条数据已与出库单进行关联，不能进行删除，请检查");
		}
		/*************************************END***************************************/
		lsContractLaborService.deleteById(id);
		lsContractLaborPaymentService.delete(new EntityWrapper<LsContractLaborPaymentEntity>().eq("con_id", id));
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@PostMapping("/v1/update")
	@Transactional
	public Result update(@RequestBody LsContractLaborEntity entity) {
		if(entity == null) {
			return Result.error("请选择数据进行操作");
		}
		checkData(entity);
		EntityWrapper<LsContractLaborEntity> wrapper = new EntityWrapper<LsContractLaborEntity>();
		wrapper.eq("con_name", entity.getConName());
		wrapper.eq("con_code", entity.getConCode());
		wrapper.notIn("id", entity.getId());
		List<LsContractLaborEntity> list = lsContractLaborService.selectList(wrapper);
		if(!CollectionUtil.isEmpty(list)) {
			return Result.error("已有重复数据请检查");
		}
		lsContractLaborService.updateContractLabor(entity);
		
		return Result.ok();
	}
	
	/***********************************************劳务分包合同付款协议*********************************************/
	/**
	 * 劳务分包合同付款协议--列表分页
	 */
	@GetMapping("/v1/paymentPageList")
	public Result paymentPageList(@RequestParam Map<String, Object> params){
		String conId = (String)params.get("conId");
		if(StrUtil.isBlank(conId)) {
			return Result.error("劳务分包合同id不能为空");
		}
		PageUtils page = lsContractLaborPaymentService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 劳务分包合同付款协议--数据列表（无分页）
	 */
	@GetMapping("/v1/paymentList")
	public Result paymentList(@RequestParam Map<String, Object> params){
		String conId = (String)params.get("conId");
		if(StrUtil.isBlank(conId)) {
			return Result.error("劳务分包合同id不能为空");
		}
		List<LsContractLaborPaymentEntity> list = lsContractLaborPaymentService.selectList(new EntityWrapper<LsContractLaborPaymentEntity>().eq("con_id", conId));
		return Result.ok().putList(list);
	}
	
	/**
	 * 劳务分包合同付款协议 --详情
	 */
	@GetMapping("/v1/paymentInfo")
	public Result paymentInfo(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		LsContractLaborPaymentEntity entity = lsContractLaborPaymentService.selectById(id);
		return Result.ok().putEntity(entity);
	}
	
	/**
	 * 劳务分包合同付款协议--保存
	 */
	@PostMapping("/v1/paymentSave")
	@Transactional
	public Result paymentSave(@RequestBody LsContractLaborPaymentEntity entity){
		if(entity != null && StrUtil.isBlank(entity.getConId())) {
			return Result.error("合同ID不能为空");
		}
		lsContractLaborPaymentService.insert(entity);
		return Result.ok().putObject("id", entity.getId());
	}
	
	/**
	 * 劳务分包合同付款协议 --删除
	 */
	@PostMapping("/v1/paymentDelete")
	public Result paymentDelete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		lsContractLaborPaymentService.deleteById(id);
		return Result.ok();
	}
	/**
	 * 劳务分包合同付款协议--修改
	 */
	@PostMapping("/v1/paymentUpdate")
	@Transactional
	public Result update(@RequestBody LsContractLaborPaymentEntity entity) {
		if(entity == null) {
			return Result.error("请选择数据进行操作");
		}
		lsContractLaborPaymentService.updateAllColumnById(entity);
		return Result.ok();
	}
	
	/**
     * 提交审核
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	public Result submit(@RequestBody LsContractLaborEntity entity) {
   		try {
   			lsContractLaborService.submit(entity);
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
   	public Result check(@RequestBody LsContractLaborEntity entity) {
   		try {
   			lsContractLaborService.check(entity);
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
   	public Result reject(@RequestBody LsContractLaborEntity entity) {
   		try {
   			lsContractLaborService.reject(entity);
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
   	public Result end(@RequestBody LsContractLaborEntity entity) {
   		try {
   			lsContractLaborService.endReturn(entity);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
    
    private void checkData(LsContractLaborEntity entity) {
    	if(entity != null) {
    		if(!StrUtil.isBlank(entity.getPartybLeaderIdcard()) && entity.getPartybLeaderIdcard().length() > 18) {
        		throw new BusinessException("身份证长度不能超过18位"); 
        	}
        	if(!StrUtil.isBlank(entity.getPartybLeaderPhone()) && entity.getPartybLeaderPhone().length() > 11) {
        		throw new BusinessException("电话号码长度不能超过11位"); 
        	}
    	}
    	
    }
}
