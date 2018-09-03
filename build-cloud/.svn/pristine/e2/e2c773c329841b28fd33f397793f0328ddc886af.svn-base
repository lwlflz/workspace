package com.build.cloud.modules.ls.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;

@TableName("ls_contract_labor_payment")
public class LsContractLaborPaymentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	private String conId;//劳务分包合同id
	@TableField(value="pay_node")
	@NotBlank(message = "付款节点不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String payNode;//付款节点
	@TableField(value="pay_rate")
	@NotNull(message = "付款比例不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal payRate;//付款比例
	@TableField(value="begin_date")
	@NotNull(message = "开始时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Date beginDate;//开始时间
	@TableField(value="end_date")
	@NotNull(message = "结束时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Date endDate;//结束时间
	@TableField(value="duration")
	@NotNull(message = "工期不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer duration;//工期
	@TableField(value="unit_money")
	@NotNull(message = "单价不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal unitMoney;//单价(元)
	@TableField(value="con_money_ntax")
	@NotNull(message = "合同金额(无税)能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal conMoneyNtax;//合同金额(无税)
	@TableField(value="con_money_tax")
	@NotNull(message = "合同金额(含税)不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal conMoneyTax;//合同金额(含税)
	private BigDecimal outputedValue;//已完成产值
	private BigDecimal applyTotal;//累计申请
	private BigDecimal payTotal;//累计已付
	private BigDecimal applyUnpayTotal;//累计申请未付
	
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
	public BigDecimal getUnitMoney() {
		return unitMoney;
	}
	public void setUnitMoney(BigDecimal unitMoney) {
		this.unitMoney = unitMoney;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
