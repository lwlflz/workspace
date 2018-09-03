package com.build.cloud.modules.sys.entity;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;
import com.build.cloud.modules.sys.form.RoleCompanyForm;
/**
 * @ClassName: SysRoleEntity
 * @Description: 角色
 * @author: liutao
 * @date: 2018年3月16日 下午5:33:45
 */
@TableName("sys_role")
public class SysRoleEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色名称
	 */
	@NotEmpty(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	/**
	 * 角色编码
	 */
	@NotEmpty(message = "角色编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String roleCode;
	/**
	 * 备注
	 */
	private String remark;

	private Long roleSort;
	/**
	 * 是否管理员角色（1是 0否）
	 */
	private String isSys;
	/**
	 * 归属集团CODE
	 */
	private String corpCode;
	
	/**
	 * 归属公司id
	 */
	@TableField(exist = false)
	private String companyId;
	
	/**
	 * 归属公司名称
	 */
	@TableField(exist = false)
	private String companyName;
	/**
	 * 归属集团名称
	 */
	@TableField(exist = false)
	private String corpName;
	/**
	 * 部门名称
	 */
	@TableField(exist = false)
	private String deptName;
	@TableField(exist = false)
	private List<String> menuIdList;
	@TableField(exist = false)
	private List<String> deptIdList;
	@TableField(exist = false)
	private List<String> companyIdList;
	@TableField(exist = false)
	private List<RoleCompanyForm> companyList = new ArrayList<RoleCompanyForm>();
	
	
	public List<RoleCompanyForm> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<RoleCompanyForm> companyList) {
		this.companyList = companyList;
	}
	/**
	 * 设置：角色名称
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}
	public Long getRoleSort() {
		return roleSort;
	}
	public void setRoleSort(Long roleSort) {
		this.roleSort = roleSort;
	}
	public List<String> getMenuIdList() {
		return menuIdList;
	}
	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<String> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<String> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public List<String> getCompanyIdList() {
		return companyIdList;
	}
	public void setCompanyIdList(List<String> companyIdList) {
		this.companyIdList = companyIdList;
	}
	public String getIsSys() {
		return isSys;
	}
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	/**
	 * @return the corpCode
	 */
	public String getCorpCode() {
		return corpCode;
	}
	/**
	 * @param corpCode the corpCode to set
	 */
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	/**
	 * @return the corpName
	 */
	public String getCorpName() {
		return corpName;
	}
	/**
	 * @param corpName the corpName to set
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
}
