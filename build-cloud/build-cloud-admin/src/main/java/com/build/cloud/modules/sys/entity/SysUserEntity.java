package com.build.cloud.modules.sys.entity;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @ClassName: SysUserEntity
 * @Description: 系统用户
 * @author: liutao
 * @date: 2018年3月17日 下午2:22:35
 */
@TableName("sys_user")
public class SysUserEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "登录用户名不能为空", groups = { AddGroup.class, UpdateGroup.class })
	@TableField("login_name")
	@Length(max = 50, message = "登录用户名长度不能超过50")
	private String loginName;
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空", groups = { AddGroup.class, UpdateGroup.class })
	@TableField("user_name")
	private String userName;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空", groups = AddGroup.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 邮箱
	 */
	@NotBlank(message = "邮箱不能为空", groups = { AddGroup.class, UpdateGroup.class })
	@Email(message = "邮箱格式不正确", groups = { AddGroup.class, UpdateGroup.class })
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 角色ID列表
	 */
	@TableField(exist = false)
	private List<SysUserRoleEntity> roleIdList;
	
	/**
	 * 员工信息
	 */
	@TableField(exist = false)
	private SysEmployeeEntity empEntity;
	
	/**
	 * 用户菜单列表
	 */
	@TableField(exist = false)
	private List<SysMenuEntity> menuList;
	
	/**
	 * 公司ID
	 */
	//@NotNull(message = "部门不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String companyId;
	/**
	 * 公司名称
	 */
	@TableField(exist = false)
	private String companyName;
	
	/**
	 * 当前公司ID
	 */
	@TableField(exist = false)
	private String curCompanyid;
	/**
	 * 部门ID
	 */
	//@NotNull(message = "部门不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String deptId;
	/**
	 * 部门名称
	 */
	@TableField(exist = false)
	private String deptName;
	/**
	 * 头像路径
	 */
	private String avatar;
	//@NotBlank(message = "用户类型不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String userType;
	private Date pwdUpdateDate;
	private String lastLoginIp;
	private Date lastLoginDate;
	/**
	 * 归属集团Code
	 */
	private String corpCode;
	/**
	 * 归属集团Name
	 */
	private String corpName;
	private Date freezeDate;
	private String freezeCause;
	private String remark;
	
	/**
	 * 状态(0正常 1离职 3封存）
	 */
	private String status;
	/**
	 * 离职日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date departureDate;
	
	/**
	 * 管理员类型（0非管理员 1超级管理员  2系统管理员）
	 */
	private String mgrType;
	
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 设置：用户名
	 * @param username 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}
	
	
	public String getCurCompanyid() {
		return curCompanyid;
	}
	public void setCurCompanyid(String curCompanyid) {
		this.curCompanyid = curCompanyid;
	}
	/**
	 * 设置：密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：邮箱
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：手机号
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：状态(0正常 1离职 3封存）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0正常 1离职 3封存）
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：离职日期
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	/**
	 * 获取：离职日期
	 */
	public Date getDepartureDate() {
		return departureDate;
	}
	public Date getFreezeDate() {
		return freezeDate;
	}
	public void setFreezeDate(Date freezeDate) {
		this.freezeDate = freezeDate;
	}
	public String getFreezeCause() {
		return freezeCause;
	}
	public void setFreezeCause(String freezeCause) {
		this.freezeCause = freezeCause;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<SysUserRoleEntity> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<SysUserRoleEntity> roleIdList) {
		this.roleIdList = roleIdList;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}
	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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
	public String getMgrType() {
		return mgrType;
	}
	public void setMgrType(String mgrType) {
		this.mgrType = mgrType;
	}
	
	public SysEmployeeEntity getEmpEntity() {
		return empEntity;
	}
	public void setEmpEntity(SysEmployeeEntity empEntity) {
		this.empEntity = empEntity;
	}
	/**
	 * @return the menuList
	 */
	public List<SysMenuEntity> getMenuList() {
		return menuList;
	}
	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<SysMenuEntity> menuList) {
		this.menuList = menuList;
	}
}
