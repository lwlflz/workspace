package com.build.cloud.modules.sys.service;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.form.CompanyUserForm;
/**
 * @ClassName: SysUserService
 * @Description: 系统用户
 * @author: liutao
 * @date: 2018年3月16日 下午5:39:17
 */
public interface ISysUserService extends IService<SysUserEntity> {
	PageUtils queryPage(Map<String, Object> params);
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	/**
	 * 查询用户的所有权限
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(String userId);
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByLoginName(String loginName);
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	/**
	 * 修改密码
	 * @param userId 用户ID
	 * @param password 原密码
	 * @param newPassword 新密码
	 */
	boolean updatePassword(String userId, String password, String newPassword);
	
	PageUtils queryUserRolePage(Map<String, Object> params);
	
	List<Map<String, Object>> querCompanyByUserId(Map<String, Object> params);
	
	/**
	 * 根据公司ID获取劳务人员列表
	 */
	List<CompanyUserForm> selectLaborUserList(Map<String, Object> params);
	
	/**
	 *  根据公司ID获取人员档案列表
	 */
	List<CompanyUserForm> selectEmpUserList(Map<String, Object> params);
	
	/**
	 * 查询集团管理员的所有菜单
	 * @param userId 用户ID
	 */
	List<String> queryCorpAdminMenuId(String userId);
	
	/**
	 * 查询集团管理员的所有权限
	 * @param userId 用户ID
	 */
	List<String> queryCorpAdminPerms(String userId);
}
