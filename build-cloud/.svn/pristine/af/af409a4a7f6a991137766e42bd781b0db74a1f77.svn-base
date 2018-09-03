package com.build.cloud.modules.sys.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.MapUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.sys.dao.SysMenuDao;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysMenuService;
import com.build.cloud.modules.sys.service.ISysRoleMenuService;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements ISysMenuService {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysMenuDao sysMenuDao;
	@Override
	@Cacheable(value = "menu", key = "'menu'.concat(#parentId.toString())")
	public List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if (menuIdList == null) {
			return menuList;
		}
		List<SysMenuEntity> userMenuList = Lists.newArrayList();
		for (SysMenuEntity menu : menuList) {
			if (menuIdList.contains(menu.getId())) {
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}
	@Override
	@Cacheable(value = "menu", key = "'menu'.concat(#parentId.toString())")
	public List<SysMenuEntity> queryListParentId(String parentId) {
		return baseMapper.queryListParentId(parentId);
	}
	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}
	
	@Override
//	@Cacheable(value = "menu", key = "'menu'.concat(#userId.toString())")
	public List<SysMenuEntity> getUserMenuList(String userId) {
//		// 系统管理员，拥有最高权限
//		if (StrUtil.equals(userId, Constant.SUPER_ADMIN)) {
//			return getAllMenuList(null);
//		}
		//add by liangsen
		SysUserEntity user = sysUserService.selectById(userId);
		//集团管理员,拥有集团对应权限套餐对应的菜单
		if (Objects.equal(user.getMgrType(), "2")) {//2-集团管理员
			List<SysMenuEntity> menuList = Lists.newArrayList();
			
			List<String> menuIdList = sysUserService.queryCorpAdminMenuId(userId);
			if(menuIdList!=null&&menuIdList.size()>0){
				String menuIds = StringUtil.join(menuIdList.toArray(),",");
				
				EntityWrapper<SysMenuEntity> menuEw = new EntityWrapper<>();
				menuEw.in(StrUtil.isNotBlank(menuIds), "id", menuIds);
				menuEw.eq(StrUtil.isNotBlank(menuIds),"valid", "0");
				menuEw.or();
				menuEw.in(StrUtil.isNotBlank(menuIds), "parent_id",menuIds);
				menuEw.eq(StrUtil.isNotBlank(menuIds),"type", "2");
				menuList = this.selectList(menuEw);
				return menuList;
			}
		}
		
		// 用户菜单列表
		List<String> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	@CacheEvict(value = "user", key = "'menu'.concat(#menuId.toString())")
	public void delete(String menuId) {
		// 删除菜单
		this.deleteById(menuId);
		// 删除菜单与角色关联
		sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
	}
	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<String> menuIdList) {
		// 查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId("0", menuIdList);
		// 递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		return menuList;
	}
	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<String> menuIdList) {
		List<SysMenuEntity> subMenuList = Lists.newArrayList();
		for (SysMenuEntity entity : menuList) {
			// 目录
			if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {
				entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}

	@Override
	public List<SysMenuEntity> finRolePermission(String roleId) {
		return sysMenuDao.finRolePermission(roleId);
	}

	@Override
	public int deleteMenuRoleByMenuId(String menuId) {
		return sysMenuDao.deleteMenuRoleByMenuId(menuId);
	}
	@Override
	public List<SysMenuEntity> selectListParentName() {
		return baseMapper.selectListParentName();
	}
	@Override
	public List<Map<String, Object>> selectMenuByUserId(String userId) {
		return baseMapper.selectMenuByUserId(userId);
	}
	
	/**
	 * 根据集团(corpCode)查询集团权限套餐对应的菜单
	 */
	@Override
	public List<SysMenuEntity> selectCorpMenus(String corpCode) {
		List<String> menuIdList = baseMapper.selectCorpMenuIds(corpCode);
		if(menuIdList!=null&&menuIdList.size()>0){
			String menuIds = StringUtil.join(menuIdList.toArray(),",");
			
			EntityWrapper<SysMenuEntity> menuEw = new EntityWrapper<>();
			menuEw.in(StrUtil.isNotBlank(menuIds), "id", menuIds);
			menuEw.or();
			menuEw.in(StrUtil.isNotBlank(menuIds), "parent_id", menuIds);
			List<SysMenuEntity> menuList = this.selectList(menuEw);
			return menuList;
		}
		return null;
	}
}
