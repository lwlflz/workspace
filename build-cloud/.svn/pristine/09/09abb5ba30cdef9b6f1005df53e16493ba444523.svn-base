package com.build.cloud.common.activity.pojo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.activiti.engine.task.Comment;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActivityImplPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String taskId;
	
	private String activityId;//任务名称id
	
	private String name;//任务名称
	
	private String type;//任务类型
	
	private String assignee;//办理人
	
	private String assigneeName;
	
	private Date startTime;
	
	private Date endTime;
	
	private String duration;//历时天数
	
	private List<Comment> commentList;
	
	private String comment;
	
	private String parentAssigneeName;
	
	private String parentAssignee;
	
	private boolean finished = false;
	
	private String backStatus;
	
	private Integer index;

	private String operType;
	
	private String operTypeName;
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getParentAssignee() {
		return parentAssignee;
	}

	public void setParentAssignee(String parentAssignee) {
		this.parentAssignee = parentAssignee;
	}

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getParentAssigneeName() {
		return parentAssigneeName;
	}

	public void setParentAssigneeName(String parentAssigneeName) {
		this.parentAssigneeName = parentAssigneeName;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getOperTypeName() {
		return operTypeName;
	}

	public void setOperTypeName(String operTypeName) {
		this.operTypeName = operTypeName;
	}
	
}
