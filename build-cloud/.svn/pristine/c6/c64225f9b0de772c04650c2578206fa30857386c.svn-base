package com.build.cloud.modules.punch.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: StatCardEntity   
 * @Description: 考勤记录表
 * @author: liutao
 * @date: 2018年5月21日 下午8:37:24
 */
@TableName("stat_card")
public class StatCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long ngId;
	/**
	 * 用户ID
	 */
	private Long ngUserId;
	/**
	 * 设备ID
	 */
	private Long ngDevId;
	/**
	 * 打卡时间
	 */
	private Date tsCard;
	/**
	 * 保留字段： 打卡类型  0:一般卡;  1:上班;  2:下班;  3:加班上班;  4:加班下班;  5:外出;  6:回来
	 */
	private Long stCardType;
	/**
	 * 
	 */
	private String szUserName;
	/**
	 * 
	 */
	private String szEmployId;
	/**
	 * 
	 */
	private String szDevName;
	/**
	 * 
	 */
	private String szDevPlace;

	/**
	 * 设置：
	 */
	public void setNgId(Long ngId) {
		this.ngId = ngId;
	}
	/**
	 * 获取：
	 */
	public Long getNgId() {
		return ngId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setNgUserId(Long ngUserId) {
		this.ngUserId = ngUserId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getNgUserId() {
		return ngUserId;
	}
	/**
	 * 设置：设备ID
	 */
	public void setNgDevId(Long ngDevId) {
		this.ngDevId = ngDevId;
	}
	/**
	 * 获取：设备ID
	 */
	public Long getNgDevId() {
		return ngDevId;
	}
	/**
	 * 设置：打卡时间
	 */
	public void setTsCard(Date tsCard) {
		this.tsCard = tsCard;
	}
	/**
	 * 获取：打卡时间
	 */
	public Date getTsCard() {
		return tsCard;
	}
	/**
	 * 设置：保留字段： 打卡类型  0:一般卡;  1:上班;  2:下班;  3:加班上班;  4:加班下班;  5:外出;  6:回来
	 */
	public void setStCardType(Long stCardType) {
		this.stCardType = stCardType;
	}
	/**
	 * 获取：保留字段： 打卡类型  0:一般卡;  1:上班;  2:下班;  3:加班上班;  4:加班下班;  5:外出;  6:回来
	 */
	public Long getStCardType() {
		return stCardType;
	}
	/**
	 * 设置：
	 */
	public void setSzUserName(String szUserName) {
		this.szUserName = szUserName;
	}
	/**
	 * 获取：
	 */
	public String getSzUserName() {
		return szUserName;
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
	 * 设置：
	 */
	public void setSzDevName(String szDevName) {
		this.szDevName = szDevName;
	}
	/**
	 * 获取：
	 */
	public String getSzDevName() {
		return szDevName;
	}
	/**
	 * 设置：
	 */
	public void setSzDevPlace(String szDevPlace) {
		this.szDevPlace = szDevPlace;
	}
	/**
	 * 获取：
	 */
	public String getSzDevPlace() {
		return szDevPlace;
	}
}
