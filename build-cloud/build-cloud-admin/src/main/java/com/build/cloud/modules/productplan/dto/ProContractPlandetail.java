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
 * @since 2018-04-26
 */
@TableName("pro_contract_plandetail")
public class ProContractPlandetail extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
	@TableField("con_id")
	private String conId;
    /**
     * 生产计划详情行标识
     */
	@TableField("proplan_unique")
	private String proplanUnique;
	/**
     * 项目id
     */
	@TableField("project_id")
	private String projectId;
	
	//单价
	@TableField("unit_price")
	private BigDecimal unitPrice;
	
	//合价
	@TableField("total_price")
	private BigDecimal totalPrice;
	
	//总量
	@TableField("gross")
	private BigDecimal gross;
	
	//平米含量
	@TableField("square")
	private BigDecimal square;
	
	//付款节点
	@TableField("pay_node")
	private String payNode;
	
	//是否完成(0-未完成,1-已完成)
	@TableField("finished")
	private String finished;

	@TableField(exist = false)
	private ProPlanDetail planDetail;
	
	/**
     * wbs名称
     */
	@TableField(exist = false)
	private String wbsName;
    /**
     * 名称属性
     */
	@TableField(exist = false)
	private String nameProperty;
	/**
     * 名称属性
     */
	@TableField(exist = false)
	private String propertyType;
    /**
     * 计划开始时间
     */
	@TableField(exist = false)
	private Date planBeginDate;
    /**
     * 计划完成时间
     */
	@TableField(exist = false)
	private Date planEndDate;
    /**
     * 工期
     */
	@TableField(exist = false)
	private BigDecimal duration;
    /**
     * 工程量
     */
	@TableField(exist = false)
	private BigDecimal quantities;
    /**
     * 单位
     */
	@TableField(exist = false)
	private String unit;
	/**
     * 父行
     */
	@TableField(exist = false)
	private String parentId;
	/**
     * 是否叶子节点
     */
	@TableField(exist = false)
	private String leaf;

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getProplanUnique() {
		return proplanUnique;
	}

	public void setProplanUnique(String proplanUnique) {
		this.proplanUnique = proplanUnique;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	public BigDecimal getSquare() {
		return square;
	}

	public void setSquare(BigDecimal square) {
		this.square = square;
	}

	public String getPayNode() {
		return payNode;
	}

	public void setPayNode(String payNode) {
		this.payNode = payNode;
	}

	public ProPlanDetail getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(ProPlanDetail planDetail) {
		this.planDetail = planDetail;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getWbsName() {
		return wbsName;
	}

	public void setWbsName(String wbsName) {
		this.wbsName = wbsName;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
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

	public BigDecimal getQuantities() {
		return quantities;
	}

	public void setQuantities(BigDecimal quantities) {
		this.quantities = quantities;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

}
