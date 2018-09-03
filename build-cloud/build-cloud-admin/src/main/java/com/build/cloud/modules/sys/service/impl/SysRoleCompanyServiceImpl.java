package com.build.cloud.modules.sys.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.sys.dao.SysRoleCompanyDao;
import com.build.cloud.modules.sys.entity.SysRoleCompanyEntity;
import com.build.cloud.modules.sys.service.ISysRoleCompanyService;
import com.google.common.collect.Lists;

import cn.hutool.core.collection.CollectionUtil;
/**
 * @ClassName: SysRoleCompanyServiceImpl
 * @Description: 角色与公司对应关系
 * @author: liutao
 * @date: 2018年4月10日 下午9:01:23
 */
@Service("sysRoleCompanyService")
public class SysRoleCompanyServiceImpl extends ServiceImpl<SysRoleCompanyDao, SysRoleCompanyEntity>
		implements ISysRoleCompanyService {
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> companyIdList) {
		// 先删除角色与部门关系
		deleteBatch(new String[] { roleId });
		if (CollectionUtil.isEmpty(companyIdList)) {
			return;
		}
		// 保存角色与菜单关系
		List<SysRoleCompanyEntity> list = Lists.newArrayListWithCapacity(companyIdList.size());
		for (String companyId : companyIdList) {
			SysRoleCompanyEntity sysRoleCompanyEntity = new SysRoleCompanyEntity();
			sysRoleCompanyEntity.setCompanyId(companyId);
			sysRoleCompanyEntity.setRoleId(roleId);
			list.add(sysRoleCompanyEntity);
		}
		this.insertBatch(list);
	}
	@Override
	public int deleteBatch(String[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
	@Override
	public List<String> queryCompanyIdList(String[] roleIds) {
		return baseMapper.queryCompanyIdList(roleIds);
	}
}
