package com.build.cloud.modules.productplan.dto;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
import com.build.cloud.modules.bs.entity.BsProjectEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
@TableName("pro_plan_info")
public class ProPlanInfo extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 生产计划名称
     */
	@TableField("pro_name")
	private String proName;
    /**
     * 项目id
     */
	@TableField("project_id")
	private String projectId;
    /**
     * 版本号
     */
	private Integer version;
    /**
     * 生效标记(0-未生效,1-已生效)
     */
	@TableField("eff_status")
	private String effStatus;
    /**
     * 工作流实例id
     */
	@TableField("activityins_id")
	private String activityinsId;
    /**
     * 工作流完成时间
     */
	@TableField("check_fin_time")
	private Date checkFinTime;
    /**
     * 状态(0-自由态;1-审核中;2-审核通过;3-审核不通过;4-驳回)
     */
	@TableField("check_status")
	private String checkStatus;
	/**
     * 弃审状态(0-未弃审(默认);1-已弃审)
     */
	@TableField("return_status")
	private String returnStatus;
	
	/**
     * activity taskId
     */
	@TableField(exist = false)
	private String taskId;
	
	/**
     * activity 审核批注
     */
	@TableField(exist = false)
	private String comment;//批注
	
	@TableField(exist = false)
	private List<ProPlanDetail> detailList;
	
	@TableField(exist = false)
	private List<BsProjectEntity> projectList;
	
	@TableField(exist = false)
	private List<Integer> versionList;
	
	@TableField(exist = false)
	private boolean endCheckerFlag = false; //是否最后审核人(弃审判断依据)
	
	@TableField(exist = false)
	private List<String> nowCheckerList;//当前审核人列表
	
	@TableField(exist = false)
	private String projectName;//项目名称
	
	@TableField(exist = false)
	private boolean allowOpered = true; //是否允许操作
	
	/**
	 * 公司项目中的角色
	 */
	@TableField(exist = false)
	private String companyRoleType;
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEffStatus() {
		return effStatus;
	}

	public void setEffStatus(String effStatus) {
		this.effStatus = effStatus;
	}

	public String getActivityinsId() {
		return activityinsId;
	}

	public void setActivityinsId(String activityinsId) {
		this.activityinsId = activityinsId;
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

	public List<ProPlanDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProPlanDetail> detailList) {
		this.detailList = detailList;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<BsProjectEntity> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<BsProjectEntity> projectList) {
		this.projectList = projectList;
	}

	public List<Integer> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<Integer> versionList) {
		this.versionList = versionList;
	}

	public boolean getEndCheckerFlag() {
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

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isAllowOpered() {
		return allowOpered;
	}

	public void setAllowOpered(boolean allowOpered) {
		this.allowOpered = allowOpered;
	}

	public String getCompanyRoleType() {
		return companyRoleType;
	}

	public void setCompanyRoleType(String companyRoleType) {
		this.companyRoleType = companyRoleType;
	}

}
