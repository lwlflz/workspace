package com.build.cloud.modules.bs.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
/**
 * @ClassName: BsProjectFileEntity
 * @Description: 项目档案
 * @author: huangchao
 * @date: 2018年4月11日  14:59:45
 */
@TableName("bs_project")
public class BsProjectEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 项目编码
	 */
	@NotBlank(message = "项目编码不能为空")
	private String projectCode;
	/**
	 * 项目名称
	 */
	@NotBlank(message = "项目名称不能为空")
	private String projectName;
	/**
	 * 归属公司ID
	 */
	private String companyId;
	/**
	 * 归属公司名称
	 */
	@TableField(exist = false)
	private String companyName;
	/**
	 * 负责人
	 */
	private String leader;
	/**
	 * 项目地址
	 */
	private String projectAddress;
	/**
	 * 立项时间
	 */
	private Date projectDate;
	private String constructionId;//建设方
	private String generalcontractorId;//总包
	private String supervisorId;//监理方
	private String designerId;//设计方
	private String laborId;//劳务方
	private String proId;//公共项目ID
	@TableField(exist = false)
	private List<DevDeviceEntity> devDeviceParam;
	@TableField(exist = false)
	private String opProjectCode;
	@TableField(exist = false)
	private String opProjectName;
//	@TableField(exist = false)
//	private String ngId;
//	
//	@TableField(exist = false)
//	private String isIn;
//	
//	@TableField(exist = false)
//	private String projectId;
	/**
	 * 结构类型
	 */
	private String structureType;
	/**
	 * 高度
	 */
	private String height;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 封存
	 */
	private String status;
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getProjectAddress() {
		return projectAddress;
	}
	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}
	public Date getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}
	public String getConstructionId() {
		return constructionId;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public String getGeneralcontractorId() {
		return generalcontractorId;
	}
	public void setGeneralcontractorId(String generalcontractorId) {
		this.generalcontractorId = generalcontractorId;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getDesignerId() {
		return designerId;
	}
	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	public String getLaborId() {
		return laborId;
	}
	public void setLaborId(String laborId) {
		this.laborId = laborId;
	}
	public String getStructureType() {
		return structureType;
	}
	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<DevDeviceEntity> getDevDeviceParam() {
		return devDeviceParam;
	}
	public void setDevDeviceParam(List<DevDeviceEntity> devDeviceParam) {
		this.devDeviceParam = devDeviceParam;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	
}
