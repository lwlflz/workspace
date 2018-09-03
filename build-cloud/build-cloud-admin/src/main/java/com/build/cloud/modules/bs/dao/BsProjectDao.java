package com.build.cloud.modules.bs.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.build.cloud.modules.bs.entity.BsProjectEntity;

/**
 * 
 * @ClassName: BsProjectFileDao   
 * @Description: 档案管理
 * @author: huangchao
 * @date: 2018年4月11日 15:03:24
 */
public interface BsProjectDao extends BaseMapper<BsProjectEntity> {

	/**
	 * 查询项目档案列表 --分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findProjectListByObject(Pagination page, Map<String, Object> params);
	
	/**
	 * 获取用户所在公司所有项目
	 * @param userId
	 * @return
	 */
	public List<BsProjectEntity> getProjectListByCompanyId(String companyId);
	
	/**
	 * 根据id查找项目
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findByProId(Map<String, Object> params);
	
	/**
	 * 查询生产计划引用项目记录条数
	 * @param projectId
	 * @return
	 */
	public int getPlanCiteProject(String projectId);
	
}
