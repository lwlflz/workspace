package com.build.cloud.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: SysUserRoleService   
 * @Description: 用户与角色对应关系U
 * @author: liutao
 * @date: 2018年3月16日 下午5:39:06
 */
public interface ISysUserRoleService extends IService<SysUserRoleEntity>  {
	
	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	
	void saveOrUpdateUserRoleByRole(List<String> laborArray,List<String> userIds, String roleId,List<String> companyId);
	
	void saveOrUpdateUserRoleByUser(List<Map<String, Object>> mapList,String userId);
}
