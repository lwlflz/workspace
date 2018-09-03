package com.build.cloud.modules.punch.bean;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.build.cloud.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
public class PunchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String workerId;
	/**
	 * 工种
	 */
	private String typeCode;
	private String typeName;
	/**
	 * 考勤日期
	 */
	@JsonFormat(pattern = DateUtils.DATE_PATTERN, timezone = "GMT+8")
	private Date attendDate;
	/**
	 * 进场时间
	 */
	private String entryTime;
	/**
	 * 退场时间
	 */
	private String exitTime;
	/**
	 * 考勤工时
	 */
	private String attendTime = "0.0";
	private String userName;
	private String projectName;
	/**
	 * 说明
	 */
	private String remark = "";
	/**
	 * 异常数据
	 */
	private Boolean aberrant;
	/**
	 * 状态：是否有效工时 1-有效  2-无效
	 */
	private String status = "1";
	private String exType;
	private String projectId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getAberrant() {
		return aberrant;
	}
	public void setAberrant(Boolean aberrant) {
		this.aberrant = aberrant;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExType() {
		return exType;
	}
	public void setExType(String exType) {
		this.exType = exType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
