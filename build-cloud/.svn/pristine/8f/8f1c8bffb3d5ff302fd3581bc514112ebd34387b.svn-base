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
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 盘存表明细表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
@TableName("mat_take_inventorylist")
public class MatTakeInventorylistEntity extends Model<MatTakeInventorylistEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 盘存表id
     */
    @TableField("take_id")
    @NotBlank(message = "盘存表id不能为空", groups = {UpdateGroup.class})
    private String takeId;
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
    @NotBlank(message = "材料分类名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mtrKindName;
    /**
     * 材料编码
     */
    @TableField("mtr_code")
    @NotBlank(message = "材料编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mtrCode;
    /**
     * 材料名称
     */
    @TableField("mtr_name")
    @NotBlank(message = "材料名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mtrName;
    /**
     * 计量单位编码
     */
    @TableField("measure_code")
    @NotBlank(message = "计量单位编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String measureCode;
    /**
     * 计量单位名称
     */
    @TableField("measure_name")
    @NotBlank(message = "计量单位名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String measureName;
    /**
     * 规格
     */
    @NotBlank(message = "规格不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String specs;
    /**
     * 出库量
     */
    @TableField("out_number")
    @NotNull(message = "出库量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal outNumber;
    /**
     * 盘存数
     */
    @TableField("take_number")
    @NotNull(message = "盘存数不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal takeNumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTakeId() {
        return takeId;
    }

    public void setTakeId(String takeId) {
        this.takeId = takeId;
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

    public BigDecimal getTakeNumber() {
        return takeNumber;
    }

    public void setTakeNumber(BigDecimal takeNumber) {
        this.takeNumber = takeNumber;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MatTakeInventorylist{" +
        "id=" + id +
        ", takeId=" + takeId +
        ", mtrKindCode=" + mtrKindCode +
        ", mtrKindName=" + mtrKindName +
        ", mtrCode=" + mtrCode +
        ", mtrName=" + mtrName +
        ", measureCode=" + measureCode +
        ", measureName=" + measureName +
        ", specs=" + specs +
        ", outNumber=" + outNumber +
        ", takeNumber=" + takeNumber +
        "}";
    }
}
