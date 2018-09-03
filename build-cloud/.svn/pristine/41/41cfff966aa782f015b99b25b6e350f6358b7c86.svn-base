package com.build.cloud.modules.sys.entity;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * 
 * @ClassName: SysRoleCompanyEntity   
 * @Description: 角色与公司对应关系
 * @author: liutao
 * @date: 2018年4月10日 下午8:56:48
 */
@TableName("sys_role_company")
public class SysRoleCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 公司ID
	 */
	private String companyId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置：角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 * @return String
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * 设置：公司ID
	 * @param companyId 公司ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：公司ID
	 * @return String
	 */
	public String getCompanyId() {
		return companyId;
	}
}
