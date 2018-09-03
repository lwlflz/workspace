package com.build.cloud.modules.sys.service.impl;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.annotation.DataFilter;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysRoleDao;
import com.build.cloud.modules.sys.entity.SysRoleCompanyEntity;
import com.build.cloud.modules.sys.entity.SysRoleEntity;
import com.build.cloud.modules.sys.form.RoleCompanyForm;
import com.build.cloud.modules.sys.service.ISysCompanyProjectService;
import com.build.cloud.modules.sys.service.ISysCompanyService;
import com.build.cloud.modules.sys.service.ISysRoleCompanyService;
import com.build.cloud.modules.sys.service.ISysRoleDeptService;
import com.build.cloud.modules.sys.service.ISysRoleMenuService;
import com.build.cloud.modules.sys.service.ISysRoleService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.google.common.collect.Lists;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysRoleServiceImpl
 * @Description: 角色
 * @author: liutao
 * @date: 2018年3月16日 下午12:19:37
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements ISysRoleService {
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	@Autowired
	private ISysRoleDeptService sysRoleDeptService;
	@Autowired
	private ISysRoleCompanyService sysRoleCompanyService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysCompanyService sysCompanyService;
	@Autowired
	private ISysCompanyProjectService sysCompanyProjectService;
	@Override
	@DataFilter(subDept = true, user = false)
	public PageUtils queryPage(Map<String, Object> params) {
		String roleName = (String)params.get("roleName");
		String corpCode = (String)params.get("corpCode");// 获取当前登陆人员归属集团CODE
		Page<SysRoleEntity> page =
			this.selectPage(new Query<SysRoleEntity>(params).getPage(),
				new EntityWrapper<SysRoleEntity>().like(StrUtil.isNotBlank(roleName), "role_name", roleName)
						.eq(StrUtil.isNotBlank(corpCode), "corp_code", corpCode).addFilterIfNeed(
							params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER)));
		return new PageUtils(page);
	}
	@Override
	public PageUtils queryUserRolePage(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		List<Map<String, Object>> records = baseMapper.selectPageByMap(page, query);
		page.setRecords(records);
		return new PageUtils(page);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		this.insert(role);
		// 保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
		// 保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());
		// 保存角色与公司关系
		sysRoleCompanyService.saveOrUpdate(role.getId(), role.getCompanyIdList());
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleEntity role) {
		this.updateById(role);
		// 更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
		// 保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());
		// 保存角色与公司关系
		//sysRoleCompanyService.saveOrUpdate(role.getId(), role.getCompanyIdList());
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] roleIds) {
		// 删除角色
		this.deleteBatchIds(Arrays.asList(roleIds));
		// 删除角色与菜单关联
		sysRoleMenuService.deleteBatch(roleIds);
		// 删除角色与部门关联
		sysRoleDeptService.deleteBatch(roleIds);
		// 删除角色与用户关联
		sysUserRoleService.deleteBatch(roleIds);
		// 删除角色与公司关系
		sysRoleCompanyService.deleteBatch(roleIds);
	}
	/**
	 * 根据角色ID获取当前角色所分配的用户列表（分页）
	 */
	@Override
	public PageUtils findUserAndProjectByRoleId(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		List<Map<String, Object>> records = baseMapper.findUserAndProjectByRoleId(page, query);
		for (Map<String, Object> map : records) {
			List<Map<String, Object>> listMap = sysCompanyService.selectByUserId(map.get("userId").toString());
			map.put("companyList", listMap);
			for (Map<String, Object> companyMap : listMap) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("companyId",companyMap.get("id").toString());
				m.put("userId", map.get("userId").toString());
				companyMap.put("projectIds",
					sysCompanyProjectService.findPanyProjectByCompanyId(m));
			}
		}
		page.setRecords(records);
		return new PageUtils(page);
	}
	/**
	 * 获取角色公司列表
	 */
	@Override
	public List<RoleCompanyForm> selectRoleCompanyList() {
		return baseMapper.selectRoleCompanyList();
	}
	/**
	 * 根据角色id获取角色信息
	 */
	@Override
	public List<SysRoleEntity> findRoleByUserId(String userId) {
		return baseMapper.findRoleByUserId(userId);
	}
	@Override
	public void saverolepany(String roleId, List<String> companyIds) {
		EntityWrapper<SysRoleCompanyEntity> wrapper = new EntityWrapper<SysRoleCompanyEntity>();
		wrapper.in("role_id", roleId);
		sysRoleCompanyService.delete(wrapper);
		if (CollectionUtil.isEmpty(companyIds)) {
			return;
		}
		List<SysRoleCompanyEntity> list = Lists.newArrayListWithCapacity(companyIds.size());
		for (String companyId : companyIds) {
			SysRoleCompanyEntity entity = new SysRoleCompanyEntity();
			entity.setCompanyId(companyId);
			entity.setRoleId(roleId);
			list.add(entity);
		}
		sysRoleCompanyService.insertBatch(list);
	}
}
