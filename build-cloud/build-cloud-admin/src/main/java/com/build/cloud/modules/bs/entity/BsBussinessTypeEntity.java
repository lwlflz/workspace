package com.build.cloud.modules.bs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

@TableName("bs_bussiness_type")
public class BsBussinessTypeEntity extends DataEntity{	

	/** serialVersionUID*/  
	private static final long serialVersionUID = -4391548346693586601L;
	
	@TableField(value="bus_type_name")
	private String busTypeName;
	
	@TableField(value="bus_type_code")
	private String busTypeCode;
	
	@TableField(value="pinyin_code")
	private String pinyinCode;
	
	@TableField(value="status")
	private String status;
	
	@TableField(value="remark")
	private String remark;

	public String getBusTypeName() {
		return busTypeName;
	}

	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}

	public String getBusTypeCode() {
		return busTypeCode;
	}

	public void setBusTypeCode(String busTypeCode) {
		this.busTypeCode = busTypeCode;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
