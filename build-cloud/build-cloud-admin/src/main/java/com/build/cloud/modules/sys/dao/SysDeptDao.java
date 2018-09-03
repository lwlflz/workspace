package com.build.cloud.modules.sys.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysDeptEntity;

import java.util.List;
/**
 * @ClassName: SysDeptDao
 * @Description: 部门管理
 * @author: liutao
 * @date: 2018年3月16日 下午2:50:16
 */
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {
	/**
	 * 查询子部门ID列表
	 * @param parentId 上级部门ID
	 */
	List<String> queryDetpIdList(String parentId);
	
	/**
	 * 根据公司ID查询部门信息
	 * @param companyId 公司ID
	 * @return
	 */
	List<SysDeptEntity> getDeptTreeByCompanyId(String companyId);
	
	/**
	 * 根据当前登陆用户信息获取所有当前用户所在公司ID
	 * @param id 当前登录用户ID
	 * @return
	 */
	List<SysDeptEntity> getCompanyIdByUserId(String id);
	
}
