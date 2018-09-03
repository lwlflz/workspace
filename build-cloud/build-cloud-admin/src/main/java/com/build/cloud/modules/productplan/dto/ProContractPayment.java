package com.build.cloud.modules.productplan.dto;

import java.math.BigDecimal;
import java.util.Date;

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
@TableName("pro_contract_payment")
public class ProContractPayment extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
	@TableField("con_id")
	private String conId;
    /**
     * 付款节点
     */
	@TableField("pay_node")
	private String payNode;
    /**
     * 付款比例
     */
	@TableField("pay_rate")
	private BigDecimal payRate;
    /**
     * 开始时间
     */
	@TableField("begin_date")
	private Date beginDate;
    /**
     * 结束时间
     */
	@TableField("end_date")
	private Date endDate;
    /**
     * 工期
     */
	private Integer duration;
    /**
     * 合同工程量
     */
	@TableField("project_num")
	private BigDecimal projectNum;
    /**
     * 综合单价
     */
	@TableField("unit_price")
	private BigDecimal unitPrice;
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
     * 已完成产值
     */
	@TableField("outputed_value")
	private BigDecimal outputedValue;
    /**
     * 累计申请
     */
	@TableField("apply_total")
	private BigDecimal applyTotal;
    /**
     * 累计已付
     */
	@TableField("pay_total")
	private BigDecimal payTotal;
    /**
     * 累计申请未付
     */
	@TableField("apply_unpay_total")
	private BigDecimal applyUnpayTotal;

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getPayNode() {
		return payNode;
	}

	public void setPayNode(String payNode) {
		this.payNode = payNode;
	}

	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(BigDecimal projectNum) {
		this.projectNum = projectNum;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUintPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
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

	public BigDecimal getOutputedValue() {
		return outputedValue;
	}

	public void setOutputedValue(BigDecimal outputedValue) {
		this.outputedValue = outputedValue;
	}

	public BigDecimal getApplyTotal() {
		return applyTotal;
	}

	public void setApplyTotal(BigDecimal applyTotal) {
		this.applyTotal = applyTotal;
	}

	public BigDecimal getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}

	public BigDecimal getApplyUnpayTotal() {
		return applyUnpayTotal;
	}

	public void setApplyUnpayTotal(BigDecimal applyUnpayTotal) {
		this.applyUnpayTotal = applyUnpayTotal;
	}
}
