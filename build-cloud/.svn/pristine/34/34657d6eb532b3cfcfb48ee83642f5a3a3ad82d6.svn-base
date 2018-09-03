package com.build.cloud.modules.productplan.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;


/**
 * 
* Title: ProWorkOrderEntity
* Description: 任务单表
* @author 鲁四围 
* @date 2018年4月24日
 */
@TableName("pro_work_order")
public class ProWorkOrderEntity extends DataEntity{

	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;
	
	@TableField(value="project_id")
	private String projectId;
	
	
	@TableField(value="proplan_unique")
	private String proplanUnique;
	
	@TableField(value="consplit_id")
	private String consplitId;
	
	@TableField(value="work_name")
	private String workName;
	
	@TableField(value="work_content")
	private String workContent;
	
	@TableField(value="work_remarks")
	private String workRemarks;
	
	@TableField(value="plan_bengin_date")
	private Date planBenginDate;
	
	@TableField(value="plan_end_date")
	private Date planEndDate;
	
	@TableField(value="duration")
	private BigDecimal duration;
	
	@TableField(value="initail_by")
	private String initailBy;
	
	@TableField(value="duty_by")
	private String dutyBy;
	
	@TableField(value="send_date")
	private Date sendDate;
	
	@TableField(value="act_begin_date")
	private Date actBeginDate;
	
	@TableField(value="act_end_date")
	private Date actEndDate;
	
	@TableField(value="work_status")
	private String workStatus;
	
	@TableField(value="acceptor_ids")
	private String acceptorIds;
	
	@TableField(exist = false)
	private String contractName;

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkRemarks() {
		return workRemarks;
	}

	public void setWorkRemarks(String workRemarks) {
		this.workRemarks = workRemarks;
	}

	public Date getPlanBenginDate() {
		return planBenginDate;
	}

	public void setPlanBenginDate(Date planBenginDate) {
		this.planBenginDate = planBenginDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getInitailBy() {
		return initailBy;
	}

	public void setInitailBy(String initailBy) {
		this.initailBy = initailBy;
	}

	public String getDutyBy() {
		return dutyBy;
	}

	public void setDutyBy(String dutyBy) {
		this.dutyBy = dutyBy;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getActBeginDate() {
		return actBeginDate;
	}

	public void setActBeginDate(Date actBeginDate) {
		this.actBeginDate = actBeginDate;
	}

	public Date getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getProplanUnique() {
		return proplanUnique;
	}

	public void setProplanUnique(String proplanUnique) {
		this.proplanUnique = proplanUnique;
	}

	public String getConsplitId() {
		return consplitId;
	}

	public void setConsplitId(String consplitId) {
		this.consplitId = consplitId;
	}

	public String getAcceptorIds() {
		return acceptorIds;
	}

	public void setAcceptorIds(String acceptorIds) {
		this.acceptorIds = acceptorIds;
	}
}
