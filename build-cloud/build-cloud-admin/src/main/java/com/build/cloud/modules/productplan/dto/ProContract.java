package com.build.cloud.modules.productplan.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
@TableName("pro_contract")
public class ProContract extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 已完成工程量
     */
	@TableField(exist = false)
	private double finishproNum;
	
	/**
     * 已完成产值
     */
	@TableField(exist = false)
	private double finishValue;
	
	/**
     * 实际用工
     */
	@TableField(exist = false)
	private double recordDays;
	
	/**
     * 实际平米用工
     */
	@TableField(exist = false)
	private double actrecordDays;
    
    /**
     * 项目id
     */
	@TableField("project_id")
	private String projectId;
    /**
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 合同编号
     */
	@TableField("con_code")
	private String conCode;
	/**
     * 合同类型(GJG-钢筋工,JZG-架子工,MG-木工,HNTG-混泥土工,QTG-砌体工,NQMH-内墙抹灰,WQZS-外墙装饰,SDAZ-水电安装)
     */
	@TableField("con_type")
	private String conType;
    /**
     * 合同名称
     */
	@TableField("con_name")
	private String conName;
    /**
     * 甲方
     */
	private String parta;
    /**
     * 乙方
     */
	private String partb;
    /**
     * 工程地址
     */
	@TableField("project_address")
	private String projectAddress;
    /**
     * 现场负责人身份证号
     */
	@TableField("duty_identity")
	private String dutyIdentity;
    /**
     * 现场负责人姓名
     */
	@TableField("duty_id")
	private String dutyId;
    /**
     * 工程类别
     */
	@TableField("project_type")
	private String projectType;
    /**
     * 合同签订日期
     */
	@TableField("sign_date")
	private Date signDate;
    /**
     * 合同金额(无税)
     */
	@TableField("con_money_ntax")
	private BigDecimal conMoneyNtax;
	/**
     * 合同金额(含税)
     */
	@TableField("con_money_tax")
	private BigDecimal conMoneyTax;
    /**
     * 合同价款类型
     */
	@TableField("price_type")
	private String priceType;
    /**
     * 工程开始日
     */
	@TableField("begin_date")
	private Date beginDate;
    /**
     * 工程结束日
     */
	@TableField("end_date")
	private Date endDate;
    /**
     * 建筑面积
     */
	@TableField("floor_area")
	private BigDecimal floorArea;
    /**
     * 保修金比例
     */
	@TableField("warranty_rate")
	private BigDecimal warrantyRate;
    /**
     * 承包方式
     */
	@TableField("contract_way")
	private String contractWay;
    /**
     * 工程质量
     */
	@TableField("project_quality")
	private String projectQuality;
    /**
     * 合同范围
     */
	@TableField("con_range")
	private String conRange;
	
	/**
     * 税率
     */
	@TableField("tax_rate")
	private BigDecimal taxRate;
	
	/**
     * 单价
     */
	@TableField("unit_price")
	private BigDecimal unitPrice;
	
	/**
     * 合同拆分标志(0-未拆分,1-已拆分)
     */
	@TableField("split_status")
	private String splitStatus;
	
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
     * 状态(0-自由态;1-审核中;2-审核通过;)
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
	
	@TableField(exist = false)
	private String dutyDname;
	
	@TableField(exist = false)
	private String partaName;
	
	@TableField(exist = false)
	private String partbName;
	
	@TableField(exist = false)
	private boolean endCheckerFlag = false; //是否最后审核人(弃审判断依据)
	
	@TableField(exist = false)
	private List<String> nowCheckerList;//当前审核人列表
	
	/**
     * activity 审核批注
     */
	@TableField(exist = false)
	private String comment;//批注
	
	@TableField(exist = false)
	private List<ProContractPayment> conPaymentList;
	
	@TableField(exist = false)
	private List<ProContractProjectlist> conProjectList;
	
	@TableField(exist = false)
	private List<ProContractPlandetail> detailList;
	
	@TableField(exist = false)
	private String companyId;

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

	public String getParta() {
		return parta;
	}

	public void setParta(String parta) {
		this.parta = parta;
	}

	public String getPartb() {
		return partb;
	}

	public void setPartb(String partb) {
		this.partb = partb;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getDutyIdentity() {
		return dutyIdentity;
	}

	public void setDutyIdentity(String dutyIdentity) {
		this.dutyIdentity = dutyIdentity;
	}

	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public BigDecimal getConMoneyNtax() {
		return conMoneyNtax;
	}

	public void setConMoneyNtax(BigDecimal conMoneyNtax) {
		this.conMoneyNtax = conMoneyNtax;
	}

	public BigDecimal getConMoneyTax() {
		return conMoneyTax;
	}

	public void setConMoneyTax(BigDecimal conMoneyTax) {
		this.conMoneyTax = conMoneyTax;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}

	public BigDecimal getWarrantyRate() {
		return warrantyRate;
	}

	public void setWarrantyRate(BigDecimal warrantyRate) {
		this.warrantyRate = warrantyRate;
	}

	public String getContractWay() {
		return contractWay;
	}

	public void setContractWay(String contractWay) {
		this.contractWay = contractWay;
	}

	public String getProjectQuality() {
		return projectQuality;
	}

	public void setProjectQuality(String projectQuality) {
		this.projectQuality = projectQuality;
	}

	public String getConRange() {
		return conRange;
	}

	public void setConRange(String conRange) {
		this.conRange = conRange;
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

	public List<ProContractPayment> getConPaymentList() {
		return conPaymentList;
	}

	public void setConPaymentList(List<ProContractPayment> conPaymentList) {
		this.conPaymentList = conPaymentList;
	}

	public List<ProContractProjectlist> getConProjectList() {
		return conProjectList;
	}

	public void setConProjectList(List<ProContractProjectlist> conProjectList) {
		this.conProjectList = conProjectList;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public String getSplitStatus() {
		return splitStatus;
	}

	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}

	public List<ProContractPlandetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProContractPlandetail> detailList) {
		this.detailList = detailList;
	}

	public String getDutyDname() {
		return dutyDname;
	}

	public void setDutyDname(String dutyDname) {
		this.dutyDname = dutyDname;
	}

	public String getConType() {
		return conType;
	}

	public void setConType(String conType) {
		this.conType = conType;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getPartaName() {
		return partaName;
	}

	public void setPartaName(String partaName) {
		this.partaName = partaName;
	}

	public String getPartbName() {
		return partbName;
	}

	public void setPartbName(String partbName) {
		this.partbName = partbName;
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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public double getFinishproNum() {
		return finishproNum;
	}

	public void setFinishproNum(double finishproNum) {
		this.finishproNum = finishproNum;
	}

	public double getFinishValue() {
		return finishValue;
	}

	public void setFinishValue(double finishValue) {
		this.finishValue = finishValue;
	}

	public double getRecordDays() {
		return recordDays;
	}

	public void setRecordDays(double recordDays) {
		this.recordDays = recordDays;
	}

	public double getActrecordDays() {
		return actrecordDays;
	}

	public void setActrecordDays(double actrecordDays) {
		this.actrecordDays = actrecordDays;
	}
	
}
