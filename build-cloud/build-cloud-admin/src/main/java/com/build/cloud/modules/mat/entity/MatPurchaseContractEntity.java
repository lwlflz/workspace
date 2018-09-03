package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;


/**
 * <p>
 * 物资采购合同文本表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
@TableName("mat_purchase_contract")
public class MatPurchaseContractEntity extends DataEntity {

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
    @NotBlank(message = "项目名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String projectName;
    /**
     * 合同编码
     */
    @TableField("con_code")
//    @NotBlank(message = "合同编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conCode;
    /**
     * 合同名称
     */
    @TableField("con_name")
    @NotBlank(message = "合同名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conName;
    /**
     * 合同类型
     */
    @TableField("con_type")
    @NotBlank(message = "合同类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conType;
    /**
     * 合同甲方
     */
    @TableField("partya_id")
    @NotBlank(message = "合同甲方不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String partyaId;
    /**
     * 合同甲方名称
     */
    @TableField(exist = false)
    private String partyaName;
    /**
     * 合同乙方
     */
    @TableField("partyb_id")
    @NotBlank(message = "合同乙方不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String partybId;
    /**
     * 合同乙方名称
     */
    @TableField(exist = false)
    private String partybName;
    /**
     * 配送地址
     */
    private String address;
    /**
     * 采购暂定总量
     */
    @TableField("temporary_total")
    @NotNull(message = "采购暂定总量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal temporaryTotal;
    /**
     * 合同金额（无税）
     */
    @TableField("con_money_ntax")
    @NotNull(message = "合同金额（无税）不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal conMoneyNtax;
    /**
     * 税率%
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;
    /**
     * 合同金额（含税）
     */
    @TableField("con_money_tax")
    @NotNull(message = "合同金额（含税）不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal conMoneyTax;
    /**
     * 合同签订日期
     */
    @TableField("sign_date")
    @NotNull(message = "合同签订日期不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Date signDate;
    /**
     * 延时付款利息
     */
    @TableField("delayed_interest")
    private BigDecimal delayedInterest;
    /**
     * 合同条款
     */
    @TableField("contract_terms")
    private String contractTerms;
    /**
     * 付款协议
     */
    @TableField("payment_agreement")
    private String paymentAgreement;
    /**
     * 供货方承担损毁比例范围(红砖/加气砼砌块采购类型合同)
     */
    @TableField("damage_ratio")
    private BigDecimal damageRatio;
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
	/**
	 * 项目编码
	 */
	@TableField(exist = false)
	private String projectCode;
	
    public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

    public String getConCode() {
        return conCode;
    }

    public void setConCode(String conCode) {
        this.conCode = conCode;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
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

    public String getPartybId() {
        return partybId;
    }

    public void setPartybId(String partybId) {
        this.partybId = partybId;
    }

    public String getPartybName() {
        return partybName;
    }

    public void setPartybName(String partybName) {
        this.partybName = partybName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTemporaryTotal() {
        return temporaryTotal;
    }

    public void setTemporaryTotal(BigDecimal temporaryTotal) {
        this.temporaryTotal = temporaryTotal;
    }

    public BigDecimal getConMoneyNtax() {
        return conMoneyNtax;
    }

    public void setConMoneyNtax(BigDecimal conMoneyNtax) {
        this.conMoneyNtax = conMoneyNtax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getConMoneyTax() {
        return conMoneyTax;
    }

    public void setConMoneyTax(BigDecimal conMoneyTax) {
        this.conMoneyTax = conMoneyTax;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public BigDecimal getDelayedInterest() {
        return delayedInterest;
    }

    public void setDelayedInterest(BigDecimal delayedInterest) {
        this.delayedInterest = delayedInterest;
    }

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }

    public String getPaymentAgreement() {
        return paymentAgreement;
    }

    public void setPaymentAgreement(String paymentAgreement) {
        this.paymentAgreement = paymentAgreement;
    }

    public BigDecimal getDamageRatio() {
        return damageRatio;
    }

    public void setDamageRatio(BigDecimal damageRatio) {
        this.damageRatio = damageRatio;
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


    @Override
    public String toString() {
        return "MatPurchaseContract{" +
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", conCode=" + conCode +
        ", conName=" + conName +
        ", conType=" + conType +
        ", partyaId=" + partyaId +
        ", partyaName=" + partyaName +
        ", partybId=" + partybId +
        ", partybName=" + partybName +
        ", address=" + address +
        ", temporaryTotal=" + temporaryTotal +
        ", conMoneyNtax=" + conMoneyNtax +
        ", taxRate=" + taxRate +
        ", conMoneyTax=" + conMoneyTax +
        ", signDate=" + signDate +
        ", delayedInterest=" + delayedInterest +
        ", contractTerms=" + contractTerms +
        ", paymentAgreement=" + paymentAgreement +
        ", damageRatio=" + damageRatio +
        ", activityinsId=" + activityinsId +
        ", returnStatus=" + returnStatus +
        ", checkFinTime=" + checkFinTime +
        ", checkStatus=" + checkStatus +
        "}";
    }
}
