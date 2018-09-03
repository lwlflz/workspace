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
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsBussinessEntity;
import com.build.cloud.modules.bs.service.IBsBussinessService;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockOutService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

/**
 * <p>
 * 出库表 前端控制器
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@RestController
@RequestMapping("/mat/pso")
public class MatPurchaseStockOutController extends AbstractController {

	@Autowired
	private IMatPurchaseStockOutService matPurchaseStockOutService;

	@Autowired
	private IBsBussinessService bsBussinessService;

	@Autowired
	private ActivityService activityService;

	/**
	 * 分页查询出库信息
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/list")
	// @RequiresPermissions("bs:measureunit:list")
	public Result queryPage(@RequestParam Map<String, Object> params) {
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		return Result.ok().put("result", matPurchaseStockOutService.queryPageList(params));
	}

	/**
	 * 查询出库信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/info")
	// @RequiresPermissions("bs:measureunit:list")
	public Result info(@RequestParam String id) {
		MatPurchaseStockOutEntity entity = matPurchaseStockOutService.info(id);
		// 获取分包单位名称
		if (entity != null) {
			BsBussinessEntity bsBussiness = bsBussinessService.selectById(entity.getSubpackageId());
			if (bsBussiness != null) {
				if (bsBussiness.getBussinessName() != null && !"".equals(bsBussiness.getBussinessName())) {
					entity.setSubpackageName(bsBussiness.getBussinessName());
				}
			}
		}
		if (entity != null && !StringUtils.isEmpty(entity.getActivityinsId())
				&& !Objects.equal(entity.getReturnStatus(), "1")) {
			// 设置是否允许弃审
			String endChecker = activityService.getEndChecker(entity.getActivityinsId());
			if (Objects.equal(ShiroUtils.getUserId(), endChecker) && Objects.equal(entity.getCheckStatus(), "2")) {
				entity.setEndCheckerFlag(true);
			}
			// 设置当前审核人id
			List<String> checkerList = activityService.findNowChecker(entity.getActivityinsId());
			if (checkerList != null && checkerList.size() > 0) {
				entity.setNowCheckerList(checkerList);
			}
			// 会签时,工作流当前任务列表,当前用户等于任务办理人时,取此任务的任务id为执行任务id
			List<Task> taskList = activityService.getNowTaskList(entity.getActivityinsId());
			for (Task task : taskList) {
				if (Objects.equal(task.getAssignee(), ShiroUtils.getUserId())) {
					entity.setTaskId(task.getId());
				}
			}
		}
		return Result.ok().put("result", entity);
	}

	/**
	 * 保存出库信息
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/save")
	// @RequiresPermissions("bs:measureunit:list")
	public Result save(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, AddGroup.class);
			for (MatPurchaseStockOutlistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, AddGroup.class);
			}
			return Result.ok("保存成功").put("result", matPurchaseStockOutService.save(entity)).putObject("id", entity.getId());
		} catch (Exception e) {
			logger.error("保存出库信息失败", e);
			return Result.error("保存出库信息失败");
		}
	}

	/**
	 * 修改出库信息
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("/v1/update")
	// @RequiresPermissions("bs:measureunit:list")
	public Result update(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			for (MatPurchaseStockOutlistEntity lEntity : entity.getList()) {
				ValidatorUtils.validateEntity(lEntity, UpdateGroup.class);
			}
			matPurchaseStockOutService.update(entity);
			return Result.ok("修改成功").putObject("id", entity.getId());
		} catch (Exception e) {
			logger.error("修改出库信息失败", e);
			return Result.error("修改出库信息失败");
		}
	}

	/**
	 * 删除出库信息
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/v1/delete")
	// @RequiresPermissions("bs:measureunit:list")
	public Result delete(@RequestBody String id) {
		try {
			String key = getDelId(id);
			matPurchaseStockOutService.deleteById(key);
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败", e);
			return Result.error("删除失败");
		}
	}

	/**
	 * 获取库存
	 * 
	 * @param params mtrKindCode：材料分类编码 mtrCode：材料编码 specs：规格projectId：项目ID
	 * @return
	 */
	@GetMapping("/v1/inventory")
	// @RequiresPermissions("bs:measureunit:list")
	public Result getInventory(@RequestParam Map<String, Object> params) {
		try {
			Integer inventory = matPurchaseStockOutService.getInventory(params);
			if (inventory == null || "".equals(inventory)) {
				inventory = 0;
			}
			return Result.ok().putObject("inventory", inventory);
		} catch (Exception e) {
			logger.error("获取库存失败", e);
			return Result.error("获取库存失败");
		}
	}

	/**
	 * 提交审核
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actSubmit")
	public Result submit(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			matPurchaseStockOutService.submit(entity);
			return Result.ok();
		} catch (Exception e) {
			logger.error("提交审核异常", e);
			return Result.error("提交审核异常：" + e.getMessage());
		}
	}

	/**
	 * 审核
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actCheck")
	public Result check(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			// 校验 库存数据是否正确
			String verify = matPurchaseStockOutService.verifyStock(entity);
			if ("".equals(verify)) {
				matPurchaseStockOutService.check(entity);
			} else {
				return Result.error("审核失败：" + verify);
			}
			return Result.ok();
		} catch (Exception e) {
			logger.error("审核异常", e);
			return Result.error("审核异常：" + e.getMessage());
		}
	}

	/**
	 * 驳回
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actReject")
	public Result reject(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			matPurchaseStockOutService.reject(entity);
			return Result.ok();
		} catch (Exception e) {
			logger.error("驳回异常", e);
			return Result.error("驳回异常：" + e.getMessage());
		}
	}

	/**
	 * 弃审
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actEndReturn")
	public Result end(@RequestBody MatPurchaseStockOutEntity entity) {
		try {
			matPurchaseStockOutService.endReturn(entity);
			return Result.ok();
		} catch (Exception e) {
			logger.error("取消审批异常", e);
			return Result.error("取消审批异常：" + e.getMessage());
		}
	}

	/**
	 * 获取材料 及其 库存
	 * 
	 * @param projectId
	 * @return
	 */
	@GetMapping("/v1/materials")
	public Result materialsList(@RequestParam Map<String, Object> params) {
		try {
			return Result.ok().put("result", matPurchaseStockOutService.findMaterialsList(params));
		} catch (Exception e) {
			logger.error("获取出库材料列表异常", e);
			return Result.error("获取出库材料列表异常" + e.getMessage());
		}
	}
	
	/**
	 * 劳务班组合同参照
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/conList")
	public Result getConList(@RequestParam Map<String, Object> params) {
		params.put("companyId", getCurrentCompanyId());
		return Result.ok().put("result", matPurchaseStockOutService.getConList(params));
	}
	
	@GetMapping("/v1/proLabCompanyList")
	public Result proLabCompanyList(@RequestParam Map<String, Object> params){
//		Map<String,String> map = commonService.getCompanyProType(user.getCompanyId(), MapUtil.getStr(params, "projectId"));
//		String type = map.get(Constant.COMPANY_ROLE_TYPE);
		PageUtils pu = bsBussinessService.partbJoin(params);
		return Result.ok().put(pu);
	}
}
