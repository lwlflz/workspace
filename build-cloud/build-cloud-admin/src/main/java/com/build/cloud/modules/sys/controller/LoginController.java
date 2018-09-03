package com.build.cloud.modules.sys.controller;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.core.base.controller.Result;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.form.SysLoginForm;
import com.build.cloud.modules.sys.service.ISysCaptchaService;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.build.cloud.modules.sys.service.ISysUserTokenService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.sunsine.common.util.encrypt.MD5Util;

/**
 * @ClassName: SysLoginController
 * @Description: 登录相关
 * @author: liutao
 * @date: 2018年3月16日 下午2:48:04
 */
@RestController
public class LoginController extends AbstractController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysUserTokenService sysUserTokenService;
	@Autowired
	private ISysCaptchaService sysCaptchaService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid) {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);
		try (ServletOutputStream out = response.getOutputStream();) {
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 登录
	 */
	@PostMapping("/v1/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)
		throws IOException {
		// 用户信息
		SysUserEntity user = sysUserService.queryByLoginName(form.getLoginName());
		// 账号不存在、密码错误
		if (user == null || !user.getPassword().equals(ShiroUtils.sha256(form.getPassword(), user.getSalt()))) {
			return Result.error("账号或密码不正确");
		}
		
		// 账号停用处理
		EntityWrapper<SysEmployeeEntity> wrapper = new EntityWrapper<SysEmployeeEntity>();
		wrapper.eq("user_id", user.getId());
		SysEmployeeEntity employee = sysEmployeeService.selectOne(wrapper);
		if(employee != null && "1".equals(employee.getStatus())) {
			return Result.error("账号已经停用,请联系管理员");
		}
		
		// 账号锁定
		if (user.getStatus().equals("1")) {
			return Result.error("账号已被禁用,请联系管理员");
		} else if (user.getStatus().equals("2")) {
			return Result.error("账号已被冻结,请联系管理员");
		}
		// 生成token，并保存到数据库
		Result r = sysUserTokenService.createToken(user);
		return r;
	}
	/**
	 * 退出
	 */
	@PostMapping("/v1/logout")
	public Result logout() {
		sysUserTokenService.logout(getUserId());
		return Result.ok();
	}
	
	public static void main(String[] args) {
		System.out.println(ShiroUtils.sha256("e10adc3949ba59abbe56e057f20f883e", "xSFHWQuxxCBFaki34GSs"));
	}

}
