package com.build.cloud.modules.productplan.dto;

import java.math.BigDecimal;

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
@TableName("pro_contract_projectlist")
public class ProContractProjectlist extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
	@TableField("con_id")
	private String conId;
    /**
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 项目特征
     */
	@TableField("project_feature")
	private String projectFeature;
    /**
     * 计算规则
     */
	@TableField("compute_rule")
	private String computeRule;
    /**
     * 计量单位
     */
	@TableField("compute_unit")
	private String computeUnit;
    /**
     * 暂定量
     */
	@TableField("tentative_quantity")
	private Float tentativeQuantity;
    /**
     * 综合单价
     */
	@TableField("unit_price")
	private BigDecimal unitPrice;
    /**
     * 金额(万元)
     */
	@TableField("money_wan")
	private BigDecimal moneyWan;

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectFeature() {
		return projectFeature;
	}

	public void setProjectFeature(String projectFeature) {
		this.projectFeature = projectFeature;
	}

	public String getComputeRule() {
		return computeRule;
	}

	public void setComputeRule(String computeRule) {
		this.computeRule = computeRule;
	}

	public String getComputeUnit() {
		return computeUnit;
	}

	public void setComputeUnit(String computeUnit) {
		this.computeUnit = computeUnit;
	}

	public Float getTentativeQuantity() {
		return tentativeQuantity;
	}

	public void setTentativeQuantity(Float tentativeQuantity) {
		this.tentativeQuantity = tentativeQuantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getMoneyWan() {
		return moneyWan;
	}

	public void setMoneyWan(BigDecimal moneyWan) {
		this.moneyWan = moneyWan;
	}
}
