package com.build.cloud.modules.bs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("bs_dynamic_log")
public class BsDynamicLogEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	
	@TableField("task_id")
	private String taskId;//任务单ID
	
	@TableField("dynamic_content")
	private String dynamicContent;//动态内容
	
	@TableField("recording_time")
	private Date recordingTime;//记录时间
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getDynamicContent() {
		return dynamicContent;
	}
	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}
	@JsonFormat(pattern = "MM-dd HH:mm")
	public Date getRecordingTime() {
		return recordingTime;
	}
	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
	}
}
