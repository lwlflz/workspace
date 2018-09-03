package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;

import java.io.Serializable;

/**
 * <p>
 * 出库详情表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@TableName("mat_purchase_stock_outlist")
public class MatPurchaseStockOutlistEntity extends Model<MatPurchaseStockOutlistEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 出库表id
     */
    @TableField("stock_id")
    private String stockId;
    /**
     * 材料分类编码
     */
    @TableField("mtr_kind_code")
    @NotBlank(message = "材料分类编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mtrKindCode;
    /**
     * 材料分类名称
     */
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
     * 计量单位编码
     */
    @TableField("measure_code")
    private String measureCode;
    /**
     * 计量单位名称
     */
    @TableField("measure_name")
    private String measureName;
    /**
     * 规格
     */
    private String specs;
    /**
     * 出库量
     */
    @TableField("out_number")
    private BigDecimal outNumber;
    /**
     * 出库时间
     */
    @TableField("out_time")
    private Date outTime;
    /**
     * 用料部位
     */
    private String position;

    /**
     * 库存数量
     */
    @TableField(exist = false)
    private BigDecimal inventory;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getInventory() {
		return inventory;
	}

	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}

	public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    public String getMeasureCode() {
        return measureCode;
    }

    public void setMeasureCode(String measureCode) {
        this.measureCode = measureCode;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public BigDecimal getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(BigDecimal outNumber) {
        this.outNumber = outNumber;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MatPurchaseStockOutlist{" +
        "id=" + id +
        ", stockId=" + stockId +
        ", mtrKindCode=" + mtrKindCode +
        ", mtrKindName=" + mtrKindName +
        ", mtrCode=" + mtrCode +
        ", mtrName=" + mtrName +
        ", measureCode=" + measureCode +
        ", measureName=" + measureName +
        ", specs=" + specs +
        ", outNumber=" + outNumber +
        ", outTime=" + outTime +
        ", position=" + position +
        "}";
    }
}
