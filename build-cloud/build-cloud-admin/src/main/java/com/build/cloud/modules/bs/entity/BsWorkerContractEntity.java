package com.build.cloud.modules.bs.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.core.entity.BaseEntity;
@TableName("bs_worker_contract")
public class BsWorkerContractEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	private String contractCode;//合同编码
	private String attachmentUrl;//附件路径
	private String attachmentName;//附件文件名称
	private String workerId;//劳务实名主键ID
	@TableField(exist = false)
	private List<Map<String, Object>> attachmentList;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public List<Map<String, Object>> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Map<String, Object>> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	
}
