package com.build.cloud.modules.sys.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.bean.UserOnline;
import com.build.cloud.modules.sys.service.ISessionService;
@RestController
public class SessionController {
	@Autowired
	private ISessionService sessionService;
	@ResponseBody
	@RequestMapping("/session/list")
	public Result list() {
		List<UserOnline> list = sessionService.list();
		return Result.ok().putList(list);
	}
	// @RequiresPermissions("user:kickout")
	@RequestMapping("/session/forceLogout")
	public Result forceLogout(String id) {
		try {
			sessionService.forceLogout(id);
			return Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("踢出用户失败");
		}
	}
}