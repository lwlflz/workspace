package com.build.cloud.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysRoleEntity;
import com.build.cloud.modules.sys.form.RoleCompanyForm;


/**
 * 
 * @ClassName: SysRoleService   
 * @Description: 角色
 * @author: liutao
 * @date: 2018年3月16日 下午5:38:57
 */
public interface ISysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);

	PageUtils queryUserRolePage(Map<String, Object> params);
	
	void save(SysRoleEntity role);

	void update(SysRoleEntity role);
	
	void deleteBatch(String[] roleIds);
	
	/**
	 * 根据角色ID获取已分配的人员信息（分页）
	 * @param roleId 角色ID
	 * @return
	 */
	PageUtils findUserAndProjectByRoleId(Map<String, Object> params);
	
	List<RoleCompanyForm> selectRoleCompanyList();
	
	public List<SysRoleEntity> findRoleByUserId(String userId);
	
	/**
	 * 角色默认公司保存
	 * @param roleId
	 * @param companyIds
	 */
	public void saverolepany(String roleId,List<String> companyIds);

}
