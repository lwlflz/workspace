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
 * @className BsMeasureUnitEntity
 * @descripion 计量单位实体
 * @author WangJun
 * @date 2018年4月11日下午3:59:11
 */
@TableName("bs_measure_unit")
public class BsMeasureUnitEntity extends DataEntity{

	private static final long serialVersionUID = 1L;
	/*
	 * 计量单位名称
	 */
	@TableField(value = "measure_name")
	@NotBlank(message = "计量单位名称不能为空",groups = { AddGroup.class, UpdateGroup.class })
	private String measureName;
	/*
	 * 计量单位编码
	 */
	@NotBlank(message = "计量单位编码不能为空",groups = { AddGroup.class, UpdateGroup.class })
	@TableField(value = "measure_code")
	private String measureCode;
	/*
	 * 封存标记
	 */
	@TableField(value = "status")
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
