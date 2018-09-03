package com.build.cloud.modules.ls.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.collection.CollectionUtil;

import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.ls.entity.LsContractLaborEntity;
import com.build.cloud.modules.ls.entity.LsContractPlandetailEntity;
import com.build.cloud.modules.ls.service.ILsContractPlandetailService;

/**
 * <p>
 * 劳务分包合同拆分详情 前端控制器
 * </p>
 *
 * @author gongjy
 * @since 2018-08-27
 */
@RestController
@RequestMapping("/ls/conPlandetail")
public class LsContractPlandetailController extends AbstractController{
	
	private Logger logger = LoggerFactory.getLogger(LsContractPlandetailController.class);
	
	@Autowired
	private ILsContractPlandetailService lsContractPlandetailService;
	
	/**
	 * 劳务分包合同拆分参照
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/getPlanDetails")
	public Result getPlanDetails(@RequestParam Map<String, Object> params) {
		return Result.ok().put("result", lsContractPlandetailService.queryContractPlandetail(params));
	}
	
	/**
	 * 保存劳务分包合同拆分
	 * @param lst
	 * @return
	 */
	@PostMapping("/v1/save")
	public Result saveConPlan(@RequestBody LsContractLaborEntity entity){
		try {
			if (CollectionUtil.isEmpty(entity.getPlanDetailList())){
				return Result.error("保存劳务分包合同拆分失败:" + "请选择拆分项");
			}
			for(LsContractPlandetailEntity plandetailEntity : entity.getPlanDetailList()) {
				ValidatorUtils.validateEntity(plandetailEntity, AddGroup.class);
			}
			lsContractPlandetailService.saveConPlandetail(entity);
			return Result.ok("保存劳务分包合同拆分成功");
		} catch (Exception e) {
			logger.error("保存劳务分包合同拆分异常", e);
			return Result.error("保存劳务分包合同拆分失败:" + e.getMessage());
		}
	}
	
	/**
	 * 查询劳务分包合同拆分详情
	 * @return
	 */
	@GetMapping("/v1/info")
	public Result getInfo(@RequestParam Map<String, Object> params){
		List<Map<String, Object>> list = lsContractPlandetailService.queryConPlanInfo(params);
		
		return Result.ok().put("result", list);
	}
}

