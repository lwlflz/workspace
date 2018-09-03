package com.build.cloud.modules.sys.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.annotation.DataFilter;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.sys.dao.SysUserDao;
import com.build.cloud.modules.sys.entity.SysDeptEntity;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.form.CompanyUserForm;
import com.build.cloud.modules.sys.form.SysUserRoleListForm;
import com.build.cloud.modules.sys.service.ISysDeptService;
import com.build.cloud.modules.sys.service.ISysMenuService;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.collect.Lists;
/**
 * @ClassName: SysUserServiceImpl
 * @Description: 系统用户
 * @author: liutao
 * @date: 2018年3月16日 下午12:20:07
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements ISysUserService {
//	@Autowired
//	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysDeptService sysDeptService;
	
	@Autowired
	private ISysMenuService sysMenuService;
	
	@Override
	public List<String> queryAllMenuId(String userId) {
		return baseMapper.queryAllMenuId(userId);
	}
	@Override
	public List<String> queryAllPerms(String userId) {
		return baseMapper.queryAllPerms(userId);
	}
	@Override
	public SysUserEntity queryByLoginName(String loginName) {
		return baseMapper.queryByLoginName(loginName);
	}
	
	/**
	 * add by liangsen
	 * 集团管理员所拥有的菜单id(无重复)
	 */
	@Override
	public List<String> queryCorpAdminMenuId(String userId) {
		List<String> menuIds = baseMapper.selectCorpAdminMenuId(userId);
		return menuIds;
	}
	
	/**
	 * add by liangsen
	 * 集团管理员所拥有的权限标识id(有重复)
	 */
	@Override
	public List<String> queryCorpAdminPerms(String userId) {
		List<String> menuIdList = queryCorpAdminMenuId(userId);
		String menuIds = StringUtil.join(menuIdList.toArray(), ",");
		
		EntityWrapper<SysMenuEntity> menuEw = new EntityWrapper<>();
		menuEw.in(StrUtil.isNotBlank(menuIds), "id", menuIds);
		menuEw.or();
		menuEw.in(StrUtil.isNotBlank(menuIds), "parent_id", menuIds);
		List<SysMenuEntity> menuList = sysMenuService.selectList(menuEw);
		
		List<String> permsList = Lists.newArrayListWithCapacity(menuList.size());
		for (SysMenuEntity menu : menuList) {
			permsList.add(menu.getPerms());
		}
		return permsList;
	}
	
	@Override
	@DataFilter(subDept = true, user = false)
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("userName");
		String corpCode = (String)params.get("corpCode");
		Page<SysUserEntity> page =
			this.selectPage(new Query<SysUserEntity>(params).getPage(),
				new EntityWrapper<SysUserEntity>().like(StrUtil.isNotBlank(username), "user_name", username)
					.eq(StrUtil.isNotBlank(corpCode), "corp_code",corpCode)	
					.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null,
							(String)params.get(Constant.SQL_FILTER)));
		for (SysUserEntity sysUserEntity : page.getRecords()) {
			if(StrUtil.isNotBlank(sysUserEntity.getDeptId())) {
				SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
				sysUserEntity.setDeptName(sysDeptEntity.getDeptName());
			}	
		}
		return new PageUtils(page);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserEntity user) {
		// sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		this.insert(user);
		/*// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());*/
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if (StrUtil.isNotBlank(user.getPassword())) {
			// user.setPassword(null);
			// } else {
			user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		}
		this.updateAllColumnById(user);
		// 保存用户与角色关系
//		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
	}
	@Override
	public boolean updatePassword(String userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		userEntity.setPwdUpdateDate(new Date());
		return this.update(userEntity, new EntityWrapper<SysUserEntity>().eq("id", userId).eq("password", password));
	}
	@Override
	public PageUtils queryUserRolePage(Map<String, Object> params) {
		Query<SysUserRoleListForm> query = new Query<SysUserRoleListForm>(params);
		Page<SysUserRoleListForm> page = query.getPage();
		List<SysUserRoleListForm> records = baseMapper.querUserRoleByUserId(page, query);
		page.setRecords(records);	
		return new PageUtils(page);
	}
	@Override
	public List<Map<String, Object>> querCompanyByUserId(Map<String, Object> params) {
		if("0".equals(params.get("mgrType").toString())) {
			return baseMapper.querCompanyByUserIdV2(params);
		}else {
			return baseMapper.querCompanyByUserIdV1(params);
		}
		
	}
	@Override
	public List<CompanyUserForm> selectLaborUserList(Map<String, Object> params) {		
		return baseMapper.selectLaborUserList(params);
	}
	@Override
	public List<CompanyUserForm> selectEmpUserList(Map<String, Object> params) {		
		return baseMapper.selectEmpUserList(params);
	}
}
