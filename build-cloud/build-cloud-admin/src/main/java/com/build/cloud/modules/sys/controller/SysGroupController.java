/**
 * 
 */
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
import com.build.cloud.modules.sys.entity.SysGroupEntity;
import com.build.cloud.modules.sys.service.ISysGroupService;

import cn.hutool.core.util.StrUtil;

/**
 * @className 
 * @descripion 
 * @author WangJun
 * @date 2018年4月27日下午4:52:15
 */
@RestController
@RequestMapping("/sys/group")
public class SysGroupController extends AbstractController{
	@Autowired
	private ISysGroupService sysGroupServiceImpl;
	/*
	 * 列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:group:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysGroupServiceImpl.queryPage(params);
		return Result.ok().put("result", page);
	}
	/*
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("sys:group:info")
	public Result info(@RequestParam String id) {
		SysGroupEntity groupEntity = sysGroupServiceImpl.selectById(id);
		return Result.ok().putEntity(groupEntity);
	}
	/*
	 * 新增
	 */
	@PostMapping("/v1/save")
	@RequiresPermissions("sys:group:save")
	public Result save(@RequestBody SysGroupEntity sysGroup) {
		sysGroupServiceImpl.insert(sysGroup);
		return Result.ok();
	}
	/*
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("sys:group:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if (StrUtil.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		sysGroupServiceImpl.deleteById(id);
		return Result.ok();
	}
	/*
	 * 更新
	 */
	@PostMapping("/v1/update")
	@RequiresPermissions("sys:group:update")
	public Result update(@RequestBody SysGroupEntity sysGroup) {
		ValidatorUtils.validateEntity(sysGroup);
		sysGroupServiceImpl.updateById(sysGroup);
		return Result.ok();
	}
	
}
