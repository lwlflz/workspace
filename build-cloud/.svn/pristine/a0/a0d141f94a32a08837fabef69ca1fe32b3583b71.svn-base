package com.build.cloud.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: SysMenuService   
 * @Description: 菜单管理
 * @author: liutao
 * @date: 2018年3月16日 下午5:38:31
 */
public interface ISysMenuService extends IService<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(String parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuEntity> getUserMenuList(String userId);

	/**
	 * 删除
	 */
	void delete(String menuId);
	
	/**
	 * 根据当前角色ID获取当前角色所有的权限信息
	 * @param roleId 角色ID
	 * @return
	 */
	public List<SysMenuEntity> finRolePermission(String roleId);
	
	/**
	 * 根据资源ID删除资源与角色关联关系
	 * @param menuId资源ID
	 * @return
	 */
	public int deleteMenuRoleByMenuId(String menuId);
	
	public List<SysMenuEntity> selectListParentName();
	
	public List<Map<String, Object>> selectMenuByUserId(String userId);
	
	/**
	 * 根据集团(corpCode)查询集团权限套餐对应的菜单
	 */
	public List<SysMenuEntity> selectCorpMenus(String corpCode);
}
