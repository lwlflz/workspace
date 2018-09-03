package com.build.cloud.modules.psn.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.psn.entity.PsnControlRulesEntity;
import com.build.cloud.modules.psn.service.IPsnControlRulesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 规则设置 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2018-08-09
 */
@RestController
@RequestMapping("/psn/rules")
public class PsnControlRulesController extends AbstractController{
	@Autowired
	private IPsnControlRulesService controllRulesService;
	@GetMapping("/v1/info")
	public Result info() {
		EntityWrapper<PsnControlRulesEntity> wrapper = new EntityWrapper<PsnControlRulesEntity>();
		wrapper.eq("company_id", getCurrentCompanyId());
		List<PsnControlRulesEntity> list = controllRulesService.selectList(wrapper);
		if(list!=null && list.size()>0) {
			return Result.ok().putEntity(list.get(0));
		}
		PsnControlRulesEntity psnControlRulesEntity = new PsnControlRulesEntity();
		psnControlRulesEntity.setCompanyId(getCurrentCompanyId());
		controllRulesService.insert(psnControlRulesEntity);
		PsnControlRulesEntity entity = controllRulesService.selectById(psnControlRulesEntity.getId());
		return Result.ok().putEntity(entity);
	}
	@PostMapping("/v1/update")
	public Result update(@RequestBody PsnControlRulesEntity entity) {
		try {
			ValidatorUtils.validateEntity(entity, UpdateGroup.class);
			controllRulesService.updateById(entity);
			return Result.ok("保存成功");
		}catch (Exception e) {
			logger.error("保存规则设置异常",e);
			return Result.error("保存失败"+e.getMessage());
		}

	}
	
}

