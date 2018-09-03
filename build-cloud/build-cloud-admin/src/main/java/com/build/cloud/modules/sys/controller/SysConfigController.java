package com.build.cloud.modules.sys.controller;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.entity.SysConfigEntity;
import com.build.cloud.modules.sys.service.ISysConfigService;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysConfigController
 * @Description: 系统配置信息
 * @author: liutao
 * @date: 2018年3月16日 下午1:56:48
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private ISysConfigService sysConfigService;
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysConfigService.queryPage(params);
		return Result.ok().put("page", page);
	}
	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public Result info(@PathVariable("id") String id) {
		SysConfigEntity config = sysConfigService.selectById(id);
		return Result.ok().put("config", config);
	}
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public Result save(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		sysConfigService.save(config);
		return Result.ok();
	}
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public Result update(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		sysConfigService.update(config);
		return Result.ok();
	}
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public Result delete(@RequestBody String id) {
		id = getJson(id, "id");
		if(StrUtil.isEmpty(id)){
			return Result.error("ID不能为空");
		}
		sysConfigService.deleteById(id);
		return Result.ok();
	}
}
