package com.build.cloud.modules.mat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("mat_purchase_plan_sumlist")
public class MatPurchasePlanSumListEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	@NotBlank(message = "采购计划汇总ID不能为空", groups = {UpdateGroup.class})
	private String planId;//采购计划汇总id
	@NotBlank(message = "材料分类编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrKindCode;//材料分类编码
	@NotBlank(message = "材料分类名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrKindName;//材料分类名称
	@NotBlank(message = "材料编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrCode;//材料编码
	@NotBlank(message = "材料名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mtrName;//材料名称
	@NotBlank(message = "规格不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String specs;//规格
	@NotBlank(message = "计量单位编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String measureCode;//计量单位编码
	@NotBlank(message = "计量单位名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String measureName;//计量单位名称
	@NotNull(message = "计划使用数量不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal planNumber;//计划使用数量
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@NotNull(message = "计划进场时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Date planDate;//计划进场时间
	private String usedState;//使用状态(0未使用1已使用)
	private BigDecimal usedNumber;//已使用数量
	private String planlistId;//月度采购计划清单id（多个id以逗号隔开）
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
	public String getUsedState() {
		return usedState;
	}
	public void setUsedState(String usedState) {
		this.usedState = usedState;
	}
	public BigDecimal getUsedNumber() {
		return usedNumber;
	}
	public void setUsedNumber(BigDecimal usedNumber) {
		this.usedNumber = usedNumber;
	}
	public String getPlanlistId() {
		return planlistId;
	}
	public void setPlanlistId(String planlistId) {
		this.planlistId = planlistId;
	}

	
	
}
