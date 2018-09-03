package com.build.cloud.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 
 * @ClassName: SysUserRoleDao   
 * @Description: 用户与角色对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午3:01:56
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
