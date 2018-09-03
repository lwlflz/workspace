package com.build.cloud.modules.sys.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;
/**
 * @ClassName: SysRoleMenuDao
 * @Description: 角色与菜单对应关系
 * @author: liutao
 * @date: 2018年3月16日 下午3:01:35
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
