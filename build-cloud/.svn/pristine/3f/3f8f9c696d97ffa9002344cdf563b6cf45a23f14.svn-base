package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>Title: MatPurchaseOrder 采购订单</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:37:09
 */
@TableName("mat_purchase_order")
public class MatPurchaseOrderEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	 /**
     * 项目id
     */
    @TableField("project_id")
    @NotBlank(message = "项目id不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String projectId;
    /**
     * 项目名称
     */
    @TableField("project_name")
    @NotBlank(message = "项目名称不能为空", groups = {AddGroup.class,UpdateGroup.class})
	private String projectName;
    /**
     * 计划月份
     */
    @TableField("plan_month")
	private String planMonth;
    /**
     * 采购合同id
     */
    @TableField("con_id")
    @NotBlank(message = "采购合同id不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String conId;
    /**
     * 采购合同名称
     */
    @TableField("con_name")
    @NotBlank(message = "采购合同名称不能为空", groups = {AddGroup.class})
	private String conName;
    /**
     * 供应商单位id
     */
    @TableField("supplier")
    @NotBlank(message = "供应商id不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String supplier;
    /**
     * 供应商单位名称
     */
    @TableField(exist = false)
	private String supplierName;
    /**
     * 订单号
     */
    @TableField("order_code")
	private String orderCode;
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
     * 工作流完成时间
     */
    @TableField("check_fin_time")
	private Date checkFinTime;
    /**
     * 状态(0-自由态;1-审核中;2-审核通过)
     */
    @TableField("check_status")
	private String checkStatus;
    @TableField(exist = false)
    private List<MatPurchaseOrderlistEntity> list;
    /**
     * 采购方式
     */
    @NotBlank(message = "采购类型不能为空", groups = {AddGroup.class})
    private String orderType;
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
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}
	public String getConId() {
		return conId;
	}
	public void setConId(String conId) {
		this.conId = conId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	public List<MatPurchaseOrderlistEntity> getList() {
		return list;
	}
	public void setList(List<MatPurchaseOrderlistEntity> list) {
		this.list = list;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	 
	 
}
