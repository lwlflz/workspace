package com.build.cloud.modules.sys.service.impl;
import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.sys.dao.SysRoleDeptDao;
import com.build.cloud.modules.sys.entity.SysRoleDeptEntity;
import com.build.cloud.modules.sys.service.ISysRoleDeptService;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 
 * @ClassName: SysRoleDeptServiceImpl   
 * @Description: 角色与部门对应关系
 * @author: liutao
 * @date: 2018年4月10日 下午9:01:01
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDeptEntity>
		implements ISysRoleDeptService {
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> deptIdList) {
		// 先删除角色与部门关系
		deleteBatch(new String[] { roleId });
		if (CollectionUtil.isEmpty(deptIdList)) {
			return;
		}
		// 保存角色与菜单关系
		List<SysRoleDeptEntity> list = Lists.newArrayListWithCapacity(deptIdList.size());
		for (String deptId : deptIdList) {
			SysRoleDeptEntity sysRoleDeptEntity = new SysRoleDeptEntity();
			sysRoleDeptEntity.setDeptId(deptId);
			sysRoleDeptEntity.setRoleId(roleId);
			list.add(sysRoleDeptEntity);
		}
		this.insertBatch(list);
	}
	@Override
	public List<String> queryDeptIdList(String[] roleIds) {
		return baseMapper.queryDeptIdList(roleIds);
	}
	@Override
	public int deleteBatch(String[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
}
