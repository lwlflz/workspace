package com.build.cloud.modules.sys.dao;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.form.CompanyUserForm;
import com.build.cloud.modules.sys.form.SysUserRoleListForm;
/**
 * @ClassName: SysUserDao
 * @Description: 系统用户
 * @author: liutao
 * @date: 2018年3月16日 下午3:01:46
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	/**
	 * 查询用户的所有权限
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(String userId);
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByLoginName(String username);
	
	public List<SysUserRoleListForm> querUserRoleByUserId(Page<?> page, Map<String, Object> params);

	List<Map<String, Object>> querCompanyByUserIdV1(Map<String, Object> params);
	
	List<Map<String, Object>> querCompanyByUserIdV2(Map<String, Object> params);
	
	/**
	 * 根据公司ID获取劳务人员列表
	 */
	List<CompanyUserForm> selectLaborUserList(Map<String, Object> params);
	
	/**
	 *  根据公司ID获取人员档案列表
	 */
	List<CompanyUserForm> selectEmpUserList(Map<String, Object> params);
	
	/**
	 * add by liangsen
	 * 根据用户id(集团管理员)查询集团拥有的的套餐权限对应的菜单id(结果集不包含按钮)
	 */
	List<String> selectCorpAdminMenuId(String userId);
}
