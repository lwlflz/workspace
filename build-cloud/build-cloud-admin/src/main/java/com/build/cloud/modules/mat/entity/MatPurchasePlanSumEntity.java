package com.build.cloud.modules.mat.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("mat_purchase_plan_sum")
public class MatPurchasePlanSumEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "项目ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String projectId;//项目id
	@NotBlank(message = "项目名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String projectName;//项目名称
	@NotBlank(message = "计划月份", groups = {AddGroup.class, UpdateGroup.class})
	private String planMonth;//计划月份
	private String billCode;//单据号
	@NotBlank(message = "建设单位ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String partyaId;//建设单位id
	@TableField(exist = false)
	private String partyaName;//建设单位名称
	private String activityinsId;//工作流实例id
	private String returnStatus;//弃审状态(0-未弃审，1-已弃审)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date checkFinTime;//工作流完成时间
	private String checkStatus;//状态(0-自由态;1-审核中;2-审核通过)
	@NotBlank(message = "物料来源不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String source;//物料来源（1采购计划2自建）
	@TableField(exist = false)
	private List<MatPurchasePlanSumListEntity> planSumListCollection;//月度采购计划汇总详情集合
	
	/**
	 * activity taskId
	 */
	@TableField(exist = false)
	private String taskId;
	/**
	 * 是否最后审核人(弃审判断依据)
	 */
	@TableField(exist = false)
	private boolean endCheckerFlag = false;
	/**
	 * 当前审核人列表
	 */
	@TableField(exist = false)
	private List<String> nowCheckerList;
	/**
	 * activity 审核批注
	 */
	@TableField(exist = false)
	private String comment;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPlanMonth() {
		return planMonth;
	}
	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getPartyaId() {
		return partyaId;
	}
	public void setPartyaId(String partyaId) {
		this.partyaId = partyaId;
	}
	public String getPartyaName() {
		return partyaName;
	}
	public void setPartyaName(String partyaName) {
		this.partyaName = partyaName;
	}
	public String getActivityinsId() {
		return activityinsId;
	}
	public void setActivityinsId(String activityinsId) {
		this.activityinsId = activityinsId;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public Date getCheckFinTime() {
		return checkFinTime;
	}
	public void setCheckFinTime(Date checkFinTime) {
		this.checkFinTime = checkFinTime;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public List<MatPurchasePlanSumListEntity> getPlanSumListCollection() {
		return planSumListCollection;
	}
	public void setPlanSumListCollection(List<MatPurchasePlanSumListEntity> planSumListCollection) {
		this.planSumListCollection = planSumListCollection;
	}
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isEndCheckerFlag() {
		return endCheckerFlag;
	}

	public void setEndCheckerFlag(boolean endCheckerFlag) {
		this.endCheckerFlag = endCheckerFlag;
	}

	public List<String> getNowCheckerList() {
		return nowCheckerList;
	}

	public void setNowCheckerList(List<String> nowCheckerList) {
		this.nowCheckerList = nowCheckerList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
