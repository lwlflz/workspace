package com.build.cloud.modules.sys.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.MapUtils;
import com.build.cloud.modules.sys.dao.SysUserRoleDao;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.google.common.collect.Lists;

import cn.hutool.core.collection.CollectionUtil;
/**
 * @ClassName: SysUserRoleServiceImpl
 * @Description: 用户与角色对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午5:35:16
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity>
		implements ISysUserRoleService {
	@Transactional
	@Override
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		// 先删除用户与角色关系
		this.deleteByMap(new MapUtils().put("user_id", userId));
		if (CollectionUtil.isEmpty(roleIdList)) {
			return;
		}
		// 保存用户与角色关系
		List<SysUserRoleEntity> list = Lists.newArrayListWithCapacity(roleIdList.size());
		for (String roleId : roleIdList) {
			SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);
			list.add(sysUserRoleEntity);
		}
		this.insertBatch(list);
	}
	
	/**
	 * 人员分配公司、角色
	 * @param userIds 人员主键集合
	 * @param roleId 角色ID
	 * @param companyId 公司ID
	 */
	@Transactional
	@Override
	public void saveOrUpdateUserRoleByRole(List<String> laborArray,List<String> userIds,
			String roleId, List<String> companyIds) {
		if (CollectionUtil.isEmpty(userIds)&&CollectionUtil.isEmpty(laborArray)) {
			return;
		}
		if(!CollectionUtil.isEmpty(userIds)){
			EntityWrapper<SysUserRoleEntity> wrApper = new EntityWrapper<SysUserRoleEntity>();
			wrApper.in("user_id", userIds);
			this.delete(wrApper);
			List<SysUserRoleEntity> list = Lists.newArrayListWithCapacity(userIds.size());
			for (String userId : userIds) {
				for (String companyId : companyIds) {
					SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
					sysUserRoleEntity.setUserId(userId);
					sysUserRoleEntity.setRoleId(roleId);
					sysUserRoleEntity.setCompanyId(companyId);
					list.add(sysUserRoleEntity);
				}
			}
			for (SysUserRoleEntity sysUserRoleEntity : list) {
				this.insert(sysUserRoleEntity);
			}
			
		}
		if(!CollectionUtil.isEmpty(laborArray)){
			EntityWrapper<SysUserRoleEntity> laborWrApper = new EntityWrapper<SysUserRoleEntity>();
			laborWrApper.in("user_id", laborArray);
			this.delete(laborWrApper);
			
			List<SysUserRoleEntity> laborList = Lists.newArrayListWithCapacity(laborArray.size());
			for (String labors : laborArray) {
				for (String companyId : companyIds) {
					SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
					sysUserRoleEntity.setUserId(labors);
					sysUserRoleEntity.setRoleId(roleId);
					sysUserRoleEntity.setCompanyId(companyId);
					laborList.add(sysUserRoleEntity);
				}
			}
			for (SysUserRoleEntity userRoleEntity : laborList) {
				this.insert(userRoleEntity);
			}
			
		}
		
	
		
	}
	
	/**
	 * 人员分配公司、角色
	 * @param companyIds 公司主键集合
	 * @param roleId 角色ID
	 * @param userId 人员ID
	 */
	@Transactional
	@Override
	public void saveOrUpdateUserRoleByUser(List<Map<String, Object>> mapList, String userId) {
		// 先删除用户与角色关系
		
		for (Map<String, Object> map : mapList) {
			List<String> companyIds = (List<String>)map.get("companyIds");
			if (CollectionUtil.isEmpty(companyIds)) {
				return;
			}
			String roleId = map.get("roleId").toString();
			EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
			wrapper.eq("role_id", roleId);
			wrapper.eq("user_id", userId);
			wrapper.notIn("company_id", companyIds);
			this.delete(wrapper);
			List<SysUserRoleEntity> list = Lists.newArrayListWithCapacity(companyIds.size());
			for (String companyId : companyIds) {
				SysUserRoleEntity entity = new SysUserRoleEntity();
				entity.setUserId(userId);
				entity.setRoleId(roleId);
				entity.setCompanyId(companyId);
				entity = baseMapper.selectOne(entity);
				if(entity == null) {
					SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
					sysUserRoleEntity.setUserId(userId);
					sysUserRoleEntity.setRoleId(roleId);
					sysUserRoleEntity.setCompanyId(companyId);
					list.add(sysUserRoleEntity);
				}
				
			}
			if(!CollectionUtil.isEmpty(list)) {
				this.insertBatch(list);
			}
			
		}
	}
	@Override
	public List<String> queryRoleIdList(String userId) {
		return baseMapper.queryRoleIdList(userId);
	}
	@Override
	public int deleteBatch(String[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
	
}
