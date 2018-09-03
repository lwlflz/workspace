package com.build.cloud.modules.sys.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
/**
 * 
 * @ClassName: SysCaptchaEntity   
 * @Description: 系统验证码
 * @author: liutao
 * @date: 2018年3月31日 下午3:46:08
 */
@TableName("sys_captcha")
public class SysCaptchaEntity {
	@TableId(type = IdType.INPUT)
	private String uuid;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
