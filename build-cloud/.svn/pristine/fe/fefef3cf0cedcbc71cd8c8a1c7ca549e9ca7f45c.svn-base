package com.build.cloud.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;


/**
 * 
 * @ClassName: SysRoleMenuService   
 * @Description: 角色与菜单对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午5:38:48
 */
public interface ISysRoleMenuService extends IService<SysRoleMenuEntity> {
	
	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	
}
