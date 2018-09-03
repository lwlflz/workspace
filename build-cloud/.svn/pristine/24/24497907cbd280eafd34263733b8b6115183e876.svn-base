package com.build.cloud.modules.sys.entity;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * @ClassName: SysRoleDeptEntity
 * @Description: 角色与部门对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午5:33:17
 */
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 部门ID
	 */
	private String deptId;
	
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
	 * 设置：部门ID
	 * @param deptId 部门ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门ID
	 * @return String
	 */
	public String getDeptId() {
		return deptId;
	}
}
