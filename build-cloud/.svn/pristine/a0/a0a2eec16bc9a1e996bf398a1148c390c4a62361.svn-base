package com.build.cloud.modules.sys.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;
/**
 * @ClassName: SysDeptService
 * @Description: 部门管理
 * @author: liutao
 * @date: 2018年3月16日 下午5:36:47
 */
public interface ISysDeptService extends IService<SysDeptEntity> {
	List<SysDeptEntity> queryList(Map<String, Object> map);
	/**
	 * 查询子部门ID列表
	 * @param parentId 上级部门ID
	 */
	List<String> queryDetpIdList(String parentId);
	/**
	 * 获取子部门ID，用于数据过滤
	 */
	List<String> getSubDeptIdList(String deptId);
	
	/**
	 * 根据公司ID查询部门信息组成树形结构
	 * @param companyId 公司ID
	 * @return
	 */
	List<SysDeptEntity> getDeptTreeByCompanyId(String companyId);
	
	/**
	 * 根据部门名称查询 部门列表
	 * @param deptName
	 * @return
	 */
	List<Map<String, Object>> getDeptByDeptName(String deptName);
	
}
