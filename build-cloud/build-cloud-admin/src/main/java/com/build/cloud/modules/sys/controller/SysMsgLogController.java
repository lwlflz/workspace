package com.build.cloud.modules.sys.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.service.ISysMsgLogService;

/**
 * @ClassName: SysMsgLogController
 * @Description: 消息日志查看
 * @author WangJun
 * @date 2018年4月3日下午1:47:54
 */
@RestController
@RequestMapping("/sys/msg")
public class SysMsgLogController {
	@Autowired
	private ISysMsgLogService sysMsgLogService;
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:msg:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysMsgLogService.queryPage(params);
		return Result.ok().put("result", page);
	}
}
