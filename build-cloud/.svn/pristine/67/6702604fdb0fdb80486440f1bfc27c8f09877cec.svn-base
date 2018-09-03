package com.build.cloud.modules.mat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;

/**
 * <p>Title: MatPurchaseOrderlist</p>  
 * <p>Description: 采购订单详情 </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:44:35
 */
@TableName("mat_purchase_orderlist")
public class MatPurchaseOrderlistEntity extends Model<MatPurchaseOrderlistEntity>{

	private static final long serialVersionUID = 1L;
	 /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 采购计划详情id
     */
    @TableId("planlist_id")
    @NotBlank(message = "采购计划详情id不能为空", groups = {UpdateGroup.class})
    private String planlistId; 
    /**
     * 采购合同id
     */
    @TableField("order_id")
    @NotBlank(message = "采购订单id不能为空", groups = {UpdateGroup.class})
    private String orderId;
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
     * 订单数量
     */
    @TableField("order_number")
    @NotNull(message = "订单数量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal orderNumber;
    /**
     * 计量单位编码
     */
    @NotNull(message = "计量单位编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String measureCode;
    /**
     * 计量单位名称
     */
    @NotNull(message = "计量单位名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String measureName;
    
    /**
     * 计划使用数量
     */
    @TableField("plan_number")
    @NotNull(message = "计划数量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal planNumber;
    /**
     * 已下单数量
     */
    @TableField(exist = false)
    private BigDecimal usedNumber;
    /**
     * 计划到场时间
     */
    @TableField("plan_date")
    private Date planDate;
    
    @TableField(exist = false)
    private BigDecimal warehousNumber;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMtrKindCode() {
		return mtrKindCode;
	}

	public void setMtrKindCode(String mtrKindCode) {
		this.mtrKindCode = mtrKindCode;
	}

	public String getPlanlistId() {
		return planlistId;
	}

	public void setPlanlistId(String planlistId) {
		this.planlistId = planlistId;
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

	public BigDecimal getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(BigDecimal orderNumber) {
		this.orderNumber = orderNumber;
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

	public BigDecimal getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(BigDecimal planNumber) {
		this.planNumber = planNumber;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	public BigDecimal getWarehousNumber() {
		return warehousNumber;
	}

	public void setWarehousNumber(BigDecimal warehousNumber) {
		this.warehousNumber = warehousNumber;
	}
	
	public BigDecimal getUsedNumber() {
		return usedNumber;
	}

	public void setUsedNumber(BigDecimal usedNumber) {
		this.usedNumber = usedNumber;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
