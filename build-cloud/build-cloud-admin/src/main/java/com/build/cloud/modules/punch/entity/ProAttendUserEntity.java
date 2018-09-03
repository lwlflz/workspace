package com.build.cloud.modules.punch.entity;
import java.io.Serializable;
import java.sql.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @ClassName: ProAttendUserEntity
 * @Description: liutao
 * @author: liutao
 * @date: 2018年5月12日 上午11:10:49
 */
@TableName("pro_attend_user")
public class ProAttendUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId
	private Long ngId;
	
	private String szEmployId;
	/**
	 * 员工真实姓名 NAME
	 */
	private String szName;
	/**
	 * 卡号
	 */
	private String szCard;
	/**
	 * 身份证号码
	 */
	private String szCardId;
	private Date tsCreate;
	
	public Long getNgId() {
		return ngId;
	}
	public void setNgId(Long ngId) {
		this.ngId = ngId;
	}
	/**
	 * 设置：
	 */
	public void setSzEmployId(String szEmployId) {
		this.szEmployId = szEmployId;
	}
	/**
	 * 获取：
	 */
	public String getSzEmployId() {
		return szEmployId;
	}
	/**
	 * 设置：员工真实姓名 NAME
	 */
	public void setSzName(String szName) {
		this.szName = szName;
	}
	/**
	 * 获取：员工真实姓名 NAME
	 */
	public String getSzName() {
		return szName;
	}
	/**
	 * 设置：卡号
	 */
	public void setSzCard(String szCard) {
		this.szCard = szCard;
	}
	/**
	 * 获取：卡号
	 */
	public String getSzCard() {
		return szCard;
	}
	/**
	 * 设置：身份证号码
	 */
	public void setSzCardId(String szCardId) {
		this.szCardId = szCardId;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getSzCardId() {
		return szCardId;
	}
	public Date getTsCreate() {
		return tsCreate;
	}
	public void setTsCreate(Date tsCreate) {
		this.tsCreate = tsCreate;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
