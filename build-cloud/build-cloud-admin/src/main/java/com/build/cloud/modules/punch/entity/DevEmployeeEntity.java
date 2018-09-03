package com.build.cloud.modules.punch.entity;
import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("dev_employee")
public class DevEmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long ngId;
	private Long ngDevId;
	private String szEmployId;
	private Date tsCreate;
	public Long getNgId() {
		return ngId;
	}
	public void setNgId(Long ngId) {
		this.ngId = ngId;
	}
	public Long getNgDevId() {
		return ngDevId;
	}
	public void setNgDevId(Long ngDevId) {
		this.ngDevId = ngDevId;
	}
	public String getSzEmployId() {
		return szEmployId;
	}
	public void setSzEmployId(String szEmployId) {
		this.szEmployId = szEmployId;
	}
	public Date getTsCreate() {
		return tsCreate;
	}
	public void setTsCreate(Date tsCreate) {
		this.tsCreate = tsCreate;
	}
}
