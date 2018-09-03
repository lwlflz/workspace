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
 * <p>Title: MatPurchasePlanlist</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午2:56:29
 */
@TableName("mat_purchase_planlist")
public class MatPurchasePlanlistEntity extends Model<MatPurchasePlanlistEntity>{
	private static final long serialVersionUID = 1L;
	 /**
	* 主键
	*/
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	* 采购合同id
	*/
	@TableField("plan_id")
	@NotBlank(message = "采购订单id不能为空", groups = {UpdateGroup.class})
	private String planId;
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
	@NotBlank(message = "材料编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrCode;
	/**
	* 材料名称
	*/
	@TableField("mtr_name")
	@NotBlank(message = "材料名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrName;
	/**
	* 规格
	*/
	@NotBlank(message = "规格不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String specs;
	/**
	* 计划使用数量
	*/
	@TableField("plan_number")
	@NotNull(message = "计划数量不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal planNumber;
	/**
	* 计量单位编码
	*/
	@TableField("measure_code")
	@NotNull(message = "计量单位编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String measureCode;
	/**
	* 计量单位名称
	*/
	@TableField("measure_name")
	@NotNull(message = "计量单位名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String measureName;
	   
	/**
	* 使用数量
	*/
	@TableField("used_number")
	private BigDecimal usedNumber;
	/**
	* 计划使用时间
	*/
	@TableField("plan_date")

	private Date planDate;
	/**
	 * 使用情况
	 */
	@TableField("used_state")
	private String usedState;
	/**
	 * 采购方式
	 */
	@TableField(exist = false)
	private String purchaseType;
	@TableField("planlist_id")
	private String planlistId;
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getPlanlistId() {
		return planlistId;
	}

	public void setPlanlistId(String planlistId) {
		this.planlistId = planlistId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public BigDecimal getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(BigDecimal planNumber) {
		this.planNumber = planNumber;
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

	public BigDecimal getUsedNumber() {
		return usedNumber;
	}

	public void setUsedNumber(BigDecimal usedNumber) {
		this.usedNumber = usedNumber;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getUsedState() {
		return usedState;
	}

	public void setUsedState(String usedState) {
		this.usedState = usedState;
	}
}
