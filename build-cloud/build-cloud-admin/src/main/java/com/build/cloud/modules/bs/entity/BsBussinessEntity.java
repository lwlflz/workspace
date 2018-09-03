package com.build.cloud.modules.bs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

@TableName("bs_bussiness")
public class BsBussinessEntity extends DataEntity {
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = -1833673676015151980L;
	
	@TableField(value="bus_type_code")
	private String busTypeCode;
	
	@TableField(value="bussiness_code")
	private String bussinessCode;
	
	@TableField(value="bussiness_name")
	private String bussinessName;
	
	@TableField(value="tax_reg_numm")
	private String taxRegNumm;
	
	@TableField(value="legal_person")
	private String legalPerson;
	
	@TableField(value="contact")
	private String contact;
	
	@TableField(value="con_phone")
	private String conPhone;
	
	@TableField(value="bus_address")
	private String busAddress;
	
	@TableField(value="bus_lic_num")
	private String busLicNum;
	
	@TableField(value="web_url")
	private String webUrl;
	
	@TableField(value="email")
	private String email;
	
	@TableField(value="status")
	private String status = "0";
	
	@TableField(value="remark")
	private String remark;
	
	private String companyId;

	@TableField(value="relation_company_id")
	private String relationCompanyId;
	
	@TableField(exist=false)
	private String relationCompanyName;
	
	public String getRelationCompanyName() {
		return relationCompanyName;
	}

	public void setRelationCompanyName(String relationCompanyName) {
		this.relationCompanyName = relationCompanyName;
	}

	public String getBusTypeCode() {
		return busTypeCode;
	}

	public void setBusTypeCode(String busTypeCode) {
		this.busTypeCode = busTypeCode;
	}

	public String getBussinessCode() {
		return bussinessCode;
	}

	public void setBussinessCode(String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	public String getBussinessName() {
		return bussinessName;
	}

	public void setBussinessName(String bussinessName) {
		this.bussinessName = bussinessName;
	}

	public String getTaxRegNumm() {
		return taxRegNumm;
	}

	public void setTaxRegNumm(String taxRegNumm) {
		this.taxRegNumm = taxRegNumm;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getConPhone() {
		return conPhone;
	}

	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}

	public String getBusLicNum() {
		return busLicNum;
	}

	public void setBusLicNum(String busLicNum) {
		this.busLicNum = busLicNum;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRelationCompanyId() {
		return relationCompanyId;
	}

	public void setRelationCompanyId(String relationCompanyId) {
		this.relationCompanyId = relationCompanyId;
	}
	
	
}
