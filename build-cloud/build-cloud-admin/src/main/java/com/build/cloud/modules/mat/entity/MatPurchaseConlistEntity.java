package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 物资采购清单
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
@TableName("mat_purchase_conlist")
public class MatPurchaseConlistEntity extends Model<MatPurchaseConlistEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 采购合同id
     */
    @TableField("con_id")
    @NotBlank(message = "采购合同id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conId;
    /**
     * 材料分类编码
     */
    @TableField("mtr_kind_code")
    @NotBlank(message = "材料分类编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mtrKindCode;
    /**
     * 材料分类名称
     */
    @NotBlank(message = "材料分类名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("mtr_kind_name")
    private String mtrKindName;
    /**
     * 材料编码
     */
    @TableField("mtr_code")
    private String mtrCode;
    /**
     * 材料名称
     */
    @TableField("mtr_name")
    private String mtrName;
    /**
     * 规格
     */
    @NotBlank(message = "规格不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String specs;
    /**
     * 数量
     */
    @TableField("con_number")
    @NotNull(message = "数量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal conNumber;
    /**
     * 单位
     */
    @TableField("con_unit")
    @NotNull(message = "单位不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conUnit;
    /**
     * 税率
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;
    /**
     * 单价（含税）
     */
    @TableField("unit_price_tax")
    private BigDecimal unitPriceTax;
    /**
     * 单价（无税）
     */
    @TableField("unit_price_ntax")
    private BigDecimal unitPriceNtax;
    /**
     * 合价（无税）
     */
    @TableField("total_money_ntax")
    private BigDecimal totalMoneyNtax;
    /**
     * 合价（含税）
     */
    @TableField("total_money_tax")
    private BigDecimal totalMoneyTax;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getMtrKindCode() {
        return mtrKindCode;
    }

    public void setMtrKindCode(String mtrKindCode) {
        this.mtrKindCode = mtrKindCode;
    }

    public String getMtrKindName() {
        return mtrKindName;
    }

    public void setMtrKindName(String mtrKindName) {
        this.mtrKindName = mtrKindName;
    }

    public String getMtrCode() {
        return mtrCode;
    }

    public void setMtrCode(String mtrCode) {
        this.mtrCode = mtrCode;
    }

    public String getMtrName() {
        return mtrName;
    }

    public void setMtrName(String mtrName) {
        this.mtrName = mtrName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public BigDecimal getConNumber() {
        return conNumber;
    }

    public void setConNumber(BigDecimal conNumber) {
        this.conNumber = conNumber;
    }

    public String getConUnit() {
        return conUnit;
    }

    public void setConUnit(String conUnit) {
        this.conUnit = conUnit;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getUnitPriceTax() {
        return unitPriceTax;
    }

    public void setUnitPriceTax(BigDecimal unitPriceTax) {
        this.unitPriceTax = unitPriceTax;
    }

    public BigDecimal getUnitPriceNtax() {
        return unitPriceNtax;
    }

    public void setUnitPriceNtax(BigDecimal unitPriceNtax) {
        this.unitPriceNtax = unitPriceNtax;
    }

    public BigDecimal getTotalMoneyNtax() {
        return totalMoneyNtax;
    }

    public void setTotalMoneyNtax(BigDecimal totalMoneyNtax) {
        this.totalMoneyNtax = totalMoneyNtax;
    }

    public BigDecimal getTotalMoneyTax() {
        return totalMoneyTax;
    }

    public void setTotalMoneyTax(BigDecimal totalMoneyTax) {
        this.totalMoneyTax = totalMoneyTax;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MatPurchaseConlist{" +
        "id=" + id +
        ", conId=" + conId +
        ", mtrKindCode=" + mtrKindCode +
        ", mtrKindName=" + mtrKindName +
        ", mtrCode=" + mtrCode +
        ", mtrName=" + mtrName +
        ", specs=" + specs +
        ", conNumber=" + conNumber +
        ", conUnit=" + conUnit +
        ", taxRate=" + taxRate +
        ", unitPriceTax=" + unitPriceTax +
        ", unitPriceNtax=" + unitPriceNtax +
        ", totalMoneyNtax=" + totalMoneyNtax +
        ", totalMoneyTax=" + totalMoneyTax +
        "}";
    }
}
