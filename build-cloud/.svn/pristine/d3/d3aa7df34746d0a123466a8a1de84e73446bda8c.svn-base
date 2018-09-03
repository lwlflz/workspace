/**
 * 
 */
package com.build.cloud.modules.bs.entity;


import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;

/**
 * @className BsMtrArchiveEntity
 * @descripion 材料档案实体
 * @author WangJun
 * @date 2018年4月11日下午5:52:12
 */
/**
 * @className 
 * @descripion 
 * @author WangJun
 * @date 2018年4月14日下午3:06:48
 */
/**
 * @className 
 * @descripion 
 * @author WangJun
 * @date 2018年4月14日下午3:06:54
 */
@TableName("bs_mtr_archive")
public class BsMtrArchiveEntity extends DataEntity{

	private static final long serialVersionUID = 1L;
	/*
	 * 材料分类名称  保存的为材料分类ID
	 */
	@TableField(value = "mtr_kind_id")
	@NotBlank(message = "材料分类id不能为空",groups = { AddGroup.class, UpdateGroup.class })
	private String mtrKindId;
	
	@TableField(exist = false)
	private String mtrKindName;
	/*
	 * 材料编码
	 */
	@TableField(value = "mtr_code")
	@NotBlank(message = "材料编码不能为空",groups = { AddGroup.class, UpdateGroup.class })
	private String mtrCode;
	/*
	 * 材料名称
	 */
	@NotBlank(message = "材料名称不能为空",groups = { AddGroup.class, UpdateGroup.class })
	@TableField(value = "mtr_name")
	private String  mtrName;
	/*
	 * 计量单位
	 */
	@TableField(value = "measure_unit")
	private String  measureUnit;
	@TableField(exist = false)
	private String measureName;
	
	@TableField(exist = false)
	private String measureCode;
	/*
	 * 规格
	 */
	@TableField(value = "specs")
	private String  specs;
	/*
	 * 封存标记
	 */
	@TableField(value = "status")
	private String  status;
	
	private String pinyinCode;
	
	
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}
	public String getMtrKindId() {
		return mtrKindId;
	}
	public void setMtrKindId(String mtrKindId) {
		this.mtrKindId = mtrKindId;
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
	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMtrKindName() {
		return mtrKindName;
	}
	public void setMtrKindName(String mtrKindName) {
		this.mtrKindName = mtrKindName;
	}
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getMeasureCode() {
		return measureCode;
	}
	public void setMeasureCode(String measureCode) {
		this.measureCode = measureCode;
	}
	
}
