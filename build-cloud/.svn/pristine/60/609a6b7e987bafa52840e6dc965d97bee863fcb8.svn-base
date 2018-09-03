package com.build.cloud.modules.sta.entity;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value = "sta_employment")
public class StaEmploymentEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.INPUT)
	private String id;
	private String projectId;
	private String teamId;
	private int staYear;
	private int staMonth;
	private int staDay;
	private String workHours;
	private Date recordTime;
	@TableField(exist = false)
	private int statsDay; // 当月天
	@TableField(exist = false)
	private String totalWorkHours; // 合计总工日
	@TableField(exist = false)
	private String workDay; // 总工日
	@TableField(exist = false)
	private String totalDaily; // 每天合计
	@TableField(exist = false)
	private String teamName;
	@TableField(exist = false)
	private String teamTypeName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public int getStaYear() {
		return staYear;
	}
	public void setStaYear(int staYear) {
		this.staYear = staYear;
	}
	public int getStaMonth() {
		return staMonth;
	}
	public void setStaMonth(int staMonth) {
		this.staMonth = staMonth;
	}
	public int getStaDay() {
		return staDay;
	}
	public void setStaDay(int staDay) {
		this.staDay = staDay;
	}
	public String getWorkHours() {
		return workHours;
	}
	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}
	public String getTotalWorkHours() {
		return totalWorkHours;
	}
	public void setTotalWorkHours(String totalWorkHours) {
		this.totalWorkHours = totalWorkHours;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamTypeName() {
		return teamTypeName;
	}
	public void setTeamTypeName(String teamTypeName) {
		this.teamTypeName = teamTypeName;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getTotalDaily() {
		return totalDaily;
	}
	public void setTotalDaily(String totalDaily) {
		this.totalDaily = totalDaily;
	}
	public int getStatsDay() {
		return statsDay;
	}
	public void setStatsDay(int statsDay) {
		this.statsDay = statsDay;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
