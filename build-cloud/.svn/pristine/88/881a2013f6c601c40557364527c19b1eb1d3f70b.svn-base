package com.build.cloud.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 
 * @ClassName: SysRoleDeptDao   
 * @Description: 角色与部门对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午3:01:24
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryDeptIdList(String[] roleIds);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
