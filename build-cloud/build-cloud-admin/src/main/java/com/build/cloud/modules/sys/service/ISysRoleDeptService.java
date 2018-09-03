package com.build.cloud.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;


/**
 * 
 * @ClassName: SysRoleDeptService   
 * @Description: 角色与部门对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午5:38:40
 */
public interface ISysRoleDeptService extends IService<SysRoleDeptEntity> {
	
	void saveOrUpdate(String roleId, List<String> deptIdList);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryDeptIdList(String[] roleIds) ;

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
