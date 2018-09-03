package com.build.cloud.modules.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
/**
 * @ClassName: SysMenuDao
 * @Description: 菜单管理
 * @author: liutao
 * @date: 2018年3月16日 下午3:00:56
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
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
	 * 根据当前角色ID获取当前角色所有的权限
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
	
	public List<String> selectCorpMenuIds(String corpCode);
}
