package com.build.cloud.modules.sys.controller;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.IShiroService;
import com.build.cloud.modules.sys.service.ISysMenuService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.collect.Maps;
/**
 * @ClassName: SysMenuController
 * @Description: 系统菜单
 * @author: liutao
 * @date: 2018年3月16日 下午2:48:22
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	private IShiroService shiroService;
	/**
	 * 导航菜单
	 */
	@RequestMapping("/v1/nav")
	public Result nav() {
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		Map<String, Object> result = Maps.newHashMap();
		result.put("menuList", menuList);
		result.put("permissions", permissions);
		return Result.ok().putList(menuList);
	}
	/**
	 * 所有菜单列表(集团所拥有的菜单)
	 */
	@RequestMapping("/v1/list")
	@RequiresPermissions("sys:menu:list")
	public Result list() {
		// List<SysMenuEntity> menuList = sysMenuService.selectList(null);
		// for (SysMenuEntity sysMenuEntity : menuList) {
		// SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
		// if (parentMenuEntity != null) {
		// sysMenuEntity.setParentName(parentMenuEntity.getName());
		// }
		// }
		
		//add by liangsen
		SysUserEntity user = ShiroUtils.getUserEntity();
		List<SysMenuEntity> menuList = sysMenuService.selectCorpMenus(user.getCorpCode());
		
//		List<SysMenuEntity> menuList = redisUtils.get(RedisKeys.getMenuKey("all"), List.class);
//		if (CollectionUtil.isEmpty(menuList)) {
//			menuList = sysMenuService.selectListParentName();
//			redisUtils.set(RedisKeys.getMenuKey("all"), menuList);
//		}
		return Result.ok().putList(menuList);
	}
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/v1/select")
	@RequiresPermissions("sys:menu:select")
	public Result select() {
		// 查询列表数据
		List<SysMenuEntity> menuList = redisUtils.get(RedisKeys.getMenuKey("not_button"), List.class);
		if (CollectionUtil.isEmpty(menuList)) {
			menuList = sysMenuService.queryNotButtonList();
			// 添加顶级菜单
			SysMenuEntity root = new SysMenuEntity();
			root.setId("0");
			root.setName("一级菜单");
			root.setParentId("0");
			root.setOpen(true);
			menuList.add(root);
		}
		return Result.ok().putList(menuList);
	}
	/**
	 * 菜单信息
	 */
	@RequestMapping("/v1/info/{id}")
	@RequiresPermissions("sys:menu:info")
	public Result info(@PathVariable("id") String id) {
		if (StrUtil.isBlank(id)) {
			return Result.error("ID不能为空");
		}
		SysMenuEntity menu = sysMenuService.selectById(id);
		return Result.ok().putEntity(menu);
	}
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/v1/save")
	@RequiresPermissions("sys:menu:save")
	public Result save(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);
		sysMenuService.insert(menu);
		delCache();
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/v1/update")
	@RequiresPermissions("sys:menu:update")
	public Result update(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);
		sysMenuService.updateById(menu);
		delCache();
		return Result.ok();
	}
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/v1/delete")
	@RequiresPermissions("sys:menu:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if (StrUtil.isBlank(id)) {
			return Result.error("ID不能为空");
		}
		// 判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(id);
		if (CollectionUtil.isNotEmpty(menuList)) {
			return Result.error("请先删除子菜单或按钮");
		}
		sysMenuService.delete(id);// 删除资源
		sysMenuService.deleteMenuRoleByMenuId(id);// 删除资源同时删除资源与角色关联关系
		delCache();
		return Result.ok();
	}
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StrUtil.isBlank(menu.getName())) {
			throw new BusinessException("菜单名称不能为空");
		}
		if (menu.getParentId() == null) {
			throw new BusinessException("上级菜单不能为空");
		}
		// 菜单
		if (menu.getType() == Constant.MenuType.MENU.getValue()) {
			if (StrUtil.isBlank(menu.getUrl())) {
				throw new BusinessException("菜单URL不能为空");
			}
		}
		// 上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if (!StrUtil.equals(menu.getParentId(), "0")) {
			SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		// 目录、菜单
		if (menu.getType() == Constant.MenuType.CATALOG.getValue()
			|| menu.getType() == Constant.MenuType.MENU.getValue()) {
			if (parentType != Constant.MenuType.CATALOG.getValue()) {
				throw new BusinessException("上级菜单只能为目录类型");
			}
			return;
		}
		// 按钮
		if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
			if (parentType != Constant.MenuType.MENU.getValue()) {
				throw new BusinessException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
	
	private void delCache() {
		redisUtils.delete(RedisKeys.getMenuKey("all"));
		redisUtils.delete(RedisKeys.getMenuKey("not_button"));
	}
}
