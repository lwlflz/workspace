package com.build.cloud.modules.sys.entity;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: SysDeptEntity
 * @Description: 部门管理
 * @author: liutao
 * @date: 2018年3月16日 下午5:31:05
 */
@TableName("sys_dept")
public class SysDeptEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	// 上级部门ID，一级部门为0
	private String parentId;
	// 部门编码
	@NotBlank(message = "部门编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deptCode;
	// 父部门编码，一级部门编码为0
	private String parentCode;
	//公司编码
	private String companyId;
	//公司名称
	@NotBlank(message = "公司名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@TableField(exist = false)
	private String companyName;
	// 部门名称
	@NotBlank(message = "部门名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deptName;
	// 上级部门名称
	@TableField(exist = false)
	private String parentName;
	// 排序
	private Integer orderNum;
	private String leader;
	private String phone;
	private String corpCode;
	private String corpName;
	private String pinyinCode;
	private String status;
	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：部门名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
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
	
	
	
}
