package com.build.cloud.modules.sys.controller;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.entity.SysMsgTemplateEntity;
import com.build.cloud.modules.sys.service.ISysMsgTemplateService;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysMsgLogController
 * @Description: 消息模板管理
 * @author WangJun
 * @date 2018年4月3日下午5:06:31
 */
@RestController
@RequestMapping("/sys/msgtemplate")
public class SysMsgTemplateController extends AbstractController {
	@Autowired
	private ISysMsgTemplateService sysMsgTemplateService;
	/*
	 * 列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:msgtemplate:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysMsgTemplateService.queryPage(params);
		return Result.ok().put("result", page);
	}
	/*
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("sys:msgtemplate:info")
	public Result info(String id) {
		SysMsgTemplateEntity msgtemplate = null;
		if (id != null) {
			msgtemplate = sysMsgTemplateService.selectById(id);
		}
		return Result.ok().putEntity(msgtemplate);
	}
	/*
	 * 保存
	 */
	@PostMapping("/v1/save")
	@RequiresPermissions("sys:msgtemplate:save")
	public Result save(@RequestBody SysMsgTemplateEntity sysMsgTemplate) {
		ValidatorUtils.validateEntity(sysMsgTemplate);
		sysMsgTemplateService.insert(sysMsgTemplate);
		return Result.ok();
	}
	/*
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("sys:msgtemplate:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if (StrUtil.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		sysMsgTemplateService.deleteById(id);
		return Result.ok();
	}
	/*
	 * 更新
	 */
	@PostMapping("/v1/update")
	@RequiresPermissions("sys:msgtemplate:update")
	public Result update(@RequestBody SysMsgTemplateEntity sysMsgTemplate) {
		ValidatorUtils.validateEntity(sysMsgTemplate);
		sysMsgTemplateService.updateById(sysMsgTemplate);
		return Result.ok();
	}
}
