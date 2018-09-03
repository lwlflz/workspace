package com.build.cloud.modules.sys.service.impl;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.cloud.common.constant.Constant;
import com.build.cloud.modules.sys.dao.SysMenuDao;
import com.build.cloud.modules.sys.dao.SysUserDao;
import com.build.cloud.modules.sys.dao.SysUserTokenDao;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserTokenEntity;
import com.build.cloud.modules.sys.service.IShiroService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.hutool.core.util.StrUtil;
@Service
public class ShiroServiceImpl implements IShiroService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserTokenDao sysUserTokenDao;
	@Override
	public Set<String> getUserPermissions(String userId) {
		List<String> permsList;
		// 系统管理员，拥有最高权限
		if (userId.equals(Constant.SUPER_ADMIN)||userId.equals("2")||userId.equals("3")) {
			List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
			permsList = Lists.newArrayListWithCapacity(menuList.size());
			for (SysMenuEntity menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserDao.queryAllPerms(userId);
		}
		// 用户权限列表
		Set<String> permsSet = Sets.newHashSet();
		for (String perms : permsList) {
			if (StrUtil.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}
	@Override
	public SysUserTokenEntity queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}
	@Override
	public SysUserEntity queryUser(String userId) {
		return sysUserDao.selectById(userId);
	}
}
