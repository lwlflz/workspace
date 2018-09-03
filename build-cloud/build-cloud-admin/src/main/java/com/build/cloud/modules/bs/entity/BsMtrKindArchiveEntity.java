package com.build.cloud.modules.bs.entity;



import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
/**
 * 
 * @className BsMtrKindArchiveEntity
 * @descripion 材料分类档案实体
 * @author WangJun
 * @date 2018年4月10日下午7:30:08
 */
@TableName("bs_mtr_kind_archive")
public class BsMtrKindArchiveEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	//父级分类ID  根目录默认为0
	@TableField(value ="parent_id")
	private String parentId;
	//材料分类编码
	@TableField(value = "mtr_kind_code")
	@NotBlank(message = "材料分类编码不能为空",groups = { AddGroup.class, UpdateGroup.class })
	private String mtrKindCode;
	//材料分类名称
	@TableField(value = "mtr_kind_name")
	@NotBlank(message = "材料分类名称不能为空",groups = { AddGroup.class, UpdateGroup.class })
	private String mtrKindName;
	//封存标志
	@TableField(value = "status")
	private String status;
	
	private String pinyinCode;
	
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
