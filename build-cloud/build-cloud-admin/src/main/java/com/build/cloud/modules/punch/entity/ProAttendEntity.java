package com.build.cloud.modules.punch.entity;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @ClassName: ProAttendEntity
 * @Description: 考勤表
 * @author: liutao
 * @date: 2018年5月17日 上午10:42:48
 */
@TableName("pro_attend")
public class ProAttendEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 
	 */
	private String workerId;
	/**
	 * 工种
	 */
	private String typeCode;
	private String typeName;
	/**
	 * 项目编号
	 */
	private String projectId;
	private String projectName;
	/**
	 * 考勤日期
	 */
	@JsonFormat(pattern = DateUtils.DATE_PATTERN, timezone = DateUtils.TIMEZONE)
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
	private Double attendTime;
	private String userName;
	/**
	 * 状态：是否有效工时 1-有效 2-无效
	 */
	private String status = "1";
	/**
	 * 说明
	 */
	private String remark = "";
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = DateUtils.TIMEZONE)
	private Date createDate;
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
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public Double getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(Double attendTime) {
		this.attendTime = attendTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
