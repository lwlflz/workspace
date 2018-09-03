package com.build.cloud.modules.sys.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.service.ISysLogService;

import java.util.Map;
/**
 * @ClassName: SysLogController
 * @Description: 系统日志
 * @author: liutao
 * @date: 2018年3月16日 下午2:47:52
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private ISysLogService sysLogService;
	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:log:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysLogService.queryPage(params);
		return Result.ok().put("result", page);
	}
}
