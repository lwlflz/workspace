package com.build.cloud.modules.sys.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName: SysLogEntity
 * @Description: 系统日志
 * @author: liutao
 * @date: 2018年3月16日 下午5:31:27
 */
@TableName("sys_log")
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	// 用户名
	private String username;
	// 用户操作
	private String operation;
	// 请求方法
	private String method;
	// 请求参数
	private String params;
	// 执行时长(毫秒)
	private Long time;
	// IP地址
	private String ip;
	// 创建时间
	private Date createDate;
	/**
	 * 归属集团Code
	 */
	private String corpCode;
	/**
	 * 归属集团Name
	 */
	private String corpName;
	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户操作
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * 获取：用户操作
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：请求方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：请求参数
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 获取：请求参数
	 */
	public String getParams() {
		return params;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
}