package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 库存表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@TableName("mat_purchase_stock")
public class MatPurchaseStockEntity extends Model<MatPurchaseStockEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
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
     * 材料分类编码
     */
    @TableField("mtr_kind_code")
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
    @TableField("specs")
    private String specs;
    /**
     * 当前库存量
     */
    @TableField("stock_number")
    private BigDecimal stockNumber;
    /**
     * 累计库存量
     */
    @TableField("total_in_number")
    private BigDecimal totalInNumber;
    /**
     * 累计出库量
     */
    @TableField("total_out_number")
    private BigDecimal totalOutNumber;
    @TableField(exist = false)
    private BigDecimal outNumber;
    @TableField(exist = false)
    private BigDecimal warehousNumber;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(BigDecimal stockNumber) {
        this.stockNumber = stockNumber;
    }

    public BigDecimal getTotalInNumber() {
        return totalInNumber;
    }

    public void setTotalInNumber(BigDecimal totalInNumber) {
        this.totalInNumber = totalInNumber;
    }

    public BigDecimal getTotalOutNumber() {
        return totalOutNumber;
    }

    public void setTotalOutNumber(BigDecimal totalOutNumber) {
        this.totalOutNumber = totalOutNumber;
    }
    
    public BigDecimal getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(BigDecimal outNumber) {
		this.outNumber = outNumber;
	}

	public BigDecimal getWarehousNumber() {
		return warehousNumber;
	}

	public void setWarehousNumber(BigDecimal warehousNumber) {
		this.warehousNumber = warehousNumber;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return "MatPurchaseStock{" +
        "id=" + id +
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", mtrKindCode=" + mtrKindCode +
        ", mtrKindName=" + mtrKindName +
        ", mtrCode=" + mtrCode +
        ", mtrName=" + mtrName +
        ", measureCode=" + measureCode +
        ", measureName=" + measureName +
        ", specs=" + specs +
        ", stockNumber=" + stockNumber +
        ", totalInNumber=" + totalInNumber +
        ", totalOutNumber=" + totalOutNumber +
        "}";
    }
}
