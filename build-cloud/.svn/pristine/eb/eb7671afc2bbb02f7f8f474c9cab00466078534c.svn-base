/**
 * 
 */
package com.build.cloud.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.MapUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.sys.dao.SysCompanyProjectDao;
import com.build.cloud.modules.sys.entity.SysCompanyProjectEntity;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;
import com.build.cloud.modules.sys.service.ISysCompanyProjectService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @className 
 * @descripion 
 * @author huangchao
 * @date 2018年4月29日上午1:05:40
 */
@Service("sysCompanyProjectService")
public class SysCompanyProjectServiceImpl extends ServiceImpl<SysCompanyProjectDao, SysCompanyProjectEntity> implements ISysCompanyProjectService{

	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	@Override
	public SysCompanyProjectEntity findPanyProjectByCompanyId(Map<String, Object> params) {
		return baseMapper.findPanyProjectByCompanyId(params);
	}

	
	@Override
	public void findPanyProjectByCompanyId(String companyId,
			List<String> projectIds,String userId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("companyId", companyId);
		params.put("userId", userId);
		SysCompanyProjectEntity entity = baseMapper.findPanyProjectByCompanyId(params);
		if(entity != null){
			this.deleteByMap(new MapUtils().put("id", entity.getId()));
		}
		if (CollectionUtil.isEmpty(projectIds)) {
			return;
		}
		String projectIdlist = StringUtil.join(projectIds.toArray(), ",");
		
		SysCompanyProjectEntity panyProjectEntity = new SysCompanyProjectEntity();
		panyProjectEntity.setProjectIds(projectIdlist.substring(0,projectIdlist.length()));
		EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
		wrapper.eq("company_id", companyId);
		wrapper.eq("user_id", userId);
		SysUserRoleEntity userRoleEntity = sysUserRoleService.selectOne(wrapper);
		panyProjectEntity.setUserroleId(userRoleEntity.getId());
		
		this.insert(panyProjectEntity);
	}

	

}
