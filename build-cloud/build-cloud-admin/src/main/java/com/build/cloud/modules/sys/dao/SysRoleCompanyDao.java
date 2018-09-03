package com.build.cloud.modules.sys.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysRoleCompanyEntity;

/**
 * 
 * @ClassName: SysRoleCompanyDao   
 * @Description: 角色与公司对应关系
 * @author: liutao
 * @date: 2018年4月10日 下午8:56:38
 */
public interface SysRoleCompanyDao extends BaseMapper<SysRoleCompanyEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryCompanyIdList(String[] roleIds);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
