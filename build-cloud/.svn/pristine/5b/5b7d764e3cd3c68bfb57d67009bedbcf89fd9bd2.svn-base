package com.build.cloud.modules.sys.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: CompanyEntity
 * @Description: 公司表
 * @author: liutao
 * @date: 2018年3月30日 下午3:26:40
 */
@TableName("sys_company")
public class SysCompanyEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 公司编码
	 */
	@NotEmpty(message = "公司编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String companyCode;
	/**
	 * 父级编号
	 */
	private String parentId;
	
	/**
	 * 父级公司名称
	 */
	@TableField(exist=false)
	private String parentName;
	/**
	 * 父级编号
	 */
	private String parentCode;
	/**
	 * 所有父级编号
	 */
	private String parentCodes;
	/**
	 * 本级排序号（升序）
	 */
	private BigDecimal treeSort;
	/**
	 * 所有级别排序号
	 */
	private String treeSorts;
	/**
	 * 公司名称
	 */
	@NotEmpty(message = "公司名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String companyName;
	
	/**
	 * 公司名称首字母
	 */
	private String pinyinCode;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 归属集团Code
	 */
	private String corpCode;
	/**
	 * 归属集团Name
	 */
	private String corpName;
	/**
	 * 公司法人
	 */
	private String corporate;
	/**
	 * 纳税人登记号
	 */
	private String taxpayerRegisNum;
	/**
	 * 所属行业
	 */
	private String industry;
	/**
	 * 注册资本
	 */
	private String registeredCapital;
	/**
	 * 公司成立时间
	 */
	private Date established;
	/**
	 * 区域编码
	 */
	private String areaCode;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	@TableField(value="contact")
	private String contact;
	/**
	 * 电话
	 */
	@TableField(value="phone")
	private String phone;
	/**
	 * 传真
	 */
	@TableField(value="fax")
	private String fax;
	/**
	 * web网址
	 */
	@TableField(value="web_url")
	private String webUrl;
	/**
	 * 状态(0正常 1停用）
	 */
	@TableField(value="status")
	private String status;
	/**
	 * 设置：公司编码
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * 获取：公司编码
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * 设置：父级编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级编号
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：父级编号
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * 获取：父级编号
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * 设置：所有父级编号
	 */
	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}
	/**
	 * 获取：所有父级编号
	 */
	public String getParentCodes() {
		return parentCodes;
	}
	/**
	 * 设置：本级排序号（升序）
	 */
	public void setTreeSort(BigDecimal treeSort) {
		this.treeSort = treeSort;
	}
	/**
	 * 获取：本级排序号（升序）
	 */
	public BigDecimal getTreeSort() {
		return treeSort;
	}
	/**
	 * 设置：所有级别排序号
	 */
	public void setTreeSorts(String treeSorts) {
		this.treeSorts = treeSorts;
	}
	/**
	 * 获取：所有级别排序号
	 */
	public String getTreeSorts() {
		return treeSorts;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：归属集团Code
	 */
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	/**
	 * 获取：归属集团Code
	 */
	public String getCorpCode() {
		return corpCode;
	}
	/**
	 * 设置：归属集团Name
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	/**
	 * 获取：归属集团Name
	 */
	public String getCorpName() {
		return corpName;
	}
	
	
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}
	/**
	 * 设置：公司法人
	 */
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	/**
	 * 获取：公司法人
	 */
	public String getCorporate() {
		return corporate;
	}
	/**
	 * 设置：纳税人登记号
	 */
	public void setTaxpayerRegisNum(String taxpayerRegisNum) {
		this.taxpayerRegisNum = taxpayerRegisNum;
	}
	/**
	 * 获取：纳税人登记号
	 */
	public String getTaxpayerRegisNum() {
		return taxpayerRegisNum;
	}
	/**
	 * 设置：所属行业
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	/**
	 * 获取：所属行业
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * 设置：注册资本
	 */
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	/**
	 * 获取：注册资本
	 */
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	/**
	 * 设置：公司成立时间
	 */
	public void setEstablished(Date established) {
		this.established = established;
	}
	/**
	 * 获取：公司成立时间
	 */
	public Date getEstablished() {
		return established;
	}
	/**
	 * 设置：区域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：区域编码
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：联系人
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 获取：传真
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * 设置：web网址
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	/**
	 * 获取：web网址
	 */
	public String getWebUrl() {
		return webUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
