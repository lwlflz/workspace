package com.build.cloud.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.build.cloud.modules.sys.entity.SysRoleEntity;
import com.build.cloud.modules.sys.form.RoleCompanyForm;

/**
 * 
 * @ClassName: SysRoleDao   
 * @Description: 角色管理
 * @author: liutao
 * @date: 2018年3月16日 下午3:01:09
 */
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
	public List<Map<String, Object>> selectPageByMap(Pagination page, Map<String, Object> params);
	
	/**
	 * 根据角色ID获取已分配的人员信息（分页）
	 * @param roleId 角色ID
	 * @return
	 */
	public List<Map<String, Object>> findUserAndProjectByRoleId(Pagination page, Map<String, Object> params);

	public List<RoleCompanyForm> selectRoleCompanyList();
	
	public List<SysRoleEntity> findRoleByUserId(String userId);
}
