package com.build.cloud.modules.bs.bean;

import java.io.Serializable;

public class CodeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String type;//类型
	private String code;//编码
	private String value;//名称
	private String isSpecial;//是否属于特殊工种(0, 否 1，是)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	
}
