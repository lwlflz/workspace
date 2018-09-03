package com.build.cloud.modules.bs.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("bs_worker_certificate")
public class BsWorkerCertificateEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	
	private String certificateType;//证书类别
	private String certificateLevel;//证书等级
	private String certificateCode;//证书编号
	private String issuingAgencies;//发证机关
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date issuingDate;//发证时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date failureDate;//证书失效日期
	private String workerId;//劳务实名主键ID
	private String attachmentUrl;//附件地址
	private String attachmentName;//附件文件名称
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateLevel() {
		return certificateLevel;
	}
	public void setCertificateLevel(String certificateLevel) {
		this.certificateLevel = certificateLevel;
	}
	public String getCertificateCode() {
		return certificateCode;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	public String getIssuingAgencies() {
		return issuingAgencies;
	}
	public void setIssuingAgencies(String issuingAgencies) {
		this.issuingAgencies = issuingAgencies;
	}
	public Date getIssuingDate() {
		return issuingDate;
	}
	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}
	public Date getFailureDate() {
		return failureDate;
	}
	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
}
