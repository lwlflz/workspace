package com.build.cloud.modules.punch.entity;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
public class PunchEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Long ngId; // 考勤ID
	private String ngUserId;
	private Date tsCard;
	private Date attendDate;
	private String stCardType;
	private String szUserName;
	private String szDevName;
	private String szDevPlace;
	private String isIn;
	private String projectId;
	private String workType;
	private String workId;
	private String szEmployId; // 员工ID EMPLID
	private String ngDevId;// 设备ID
	public Long getNgId() {
		return ngId;
	}
	public void setNgId(Long ngId) {
		this.ngId = ngId;
	}
	public String getSzEmployId() {
		return szEmployId;
	}
	public void setSzEmployId(String szEmployId) {
		this.szEmployId = szEmployId;
	}
	public String getNgDevId() {
		return ngDevId;
	}
	public void setNgDevId(String ngDevId) {
		this.ngDevId = ngDevId;
	}
	public String getNgUserId() {
		return ngUserId;
	}
	public void setNgUserId(String ngUserId) {
		this.ngUserId = ngUserId;
	}
	public Date getTsCard() {
		return tsCard;
	}
	public void setTsCard(Date tsCard) {
		this.tsCard = tsCard;
	}
	public String getStCardType() {
		return stCardType;
	}
	public void setStCardType(String stCardType) {
		this.stCardType = stCardType;
	}
	public String getSzUserName() {
		return szUserName;
	}
	public void setSzUserName(String szUserName) {
		this.szUserName = szUserName;
	}
	public String getSzDevName() {
		return szDevName;
	}
	public void setSzDevName(String szDevName) {
		this.szDevName = szDevName;
	}
	public String getSzDevPlace() {
		return szDevPlace;
	}
	public void setSzDevPlace(String szDevPlace) {
		this.szDevPlace = szDevPlace;
	}
	public String getIsIn() {
		return isIn;
	}
	public void setIsIn(String isIn) {
		this.isIn = isIn;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
