package com.build.cloud.modules.mat.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>Title: MatPurchasePlanEntity</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午2:30:06
 */
@TableName("mat_purchase_plan")
public class MatPurchasePlanEntity extends DataEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 项目id
	 */
	private String projectId;
	/**
	 * 项目名称
	 */
	@TableField("project_name")
	@NotBlank(message = "项目名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String projectName;
	/**
	 * 计划月份
	 */
	@TableField("plan_month")
	private String planMonth;
	/**
	 * 劳务合同id
	 */
	@TableField("work_con_id")
	@NotBlank(message = "劳务合同id不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String workConId;
	/**
	 * 劳务合同名称
	 */
	@TableField("work_con_name")
	@NotBlank(message = "劳务合同名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String workConName;
	/**
	 * 采购方式(1甲供 2自购)
	 */
	@TableField("purchase_type")
	@NotBlank(message = "采购方式不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String purchaseType;
	/**
	 * 总包单位id
	 */
	@TableField("main_con_id")
	@NotBlank(message = "总包单位id不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mainConId;
	/**
	 * 总包单位名称
	 */
	@TableField(exist=false)
	private String mainConName;
	/**
	 * 单据号
	 */
	@TableField("bill_code")
	@NotBlank(message = "单据号不能为空", groups = {UpdateGroup.class})
	private String billCode;
	/**
	 * 工作流实例id
	 */
	@TableField("activityins_id")
	private String activityinsId;
	/**
	 * 弃审状态(0-未弃审，1-已弃审)
	 */
	@TableField("return_status")
	private String returnStatus;
	/**
	 * 状态(0-自由态;1-审核中;2-审核通过)
	 */
	@TableField("check_status")
	private String checkStatus;
	/**
	 * 工作流完成时间
	 */
	@TableField("check_fin_time")
	private Date checkFinTime;
	@TableField(exist=false)
	private List<MatPurchasePlanlistEntity>  list;
	/**
	 * 劳务公司id
	 */
	@TableField("company_id")
	private String companyId;
	/**
	 * 劳务公司名称
	 */
	@TableField(exist = false)
	private String companyName;
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
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkConId() {
		return workConId;
	}
	public void setWorkConId(String workConId) {
		this.workConId = workConId;
	}
	public String getWorkConName() {
		return workConName;
	}
	public void setWorkConName(String workConName) {
		this.workConName = workConName;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getMainConId() {
		return mainConId;
	}
	public void setMainConId(String mainConId) {
		this.mainConId = mainConId;
	}
	public String getMainConName() {
		return mainConName;
	}
	public void setMainConName(String mainConName) {
		this.mainConName = mainConName;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
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
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Date getCheckFinTime() {
		return checkFinTime;
	}
	public void setCheckFinTime(Date checkFinTime) {
		this.checkFinTime = checkFinTime;
	}
	public List<MatPurchasePlanlistEntity> getList() {
		return list;
	}
	public void setList(List<MatPurchasePlanlistEntity> list) {
		this.list = list;
	}
	
}
