package com.build.cloud.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysRoleCompanyEntity;


/**
 * 
 * @ClassName: ISysRoleCompanyService   
 * @Description: 角色与公司对应关系
 * @author: liutao
 * @date: 2018年4月10日 下午8:59:45
 */
public interface ISysRoleCompanyService extends IService<SysRoleCompanyEntity> {
	
	void saveOrUpdate(String roleId, List<String> companyIdList);
	
	/**
	 * 根据角色ID，获取公司ID列表
	 */
	List<String> queryCompanyIdList(String[] roleIds) ;

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
