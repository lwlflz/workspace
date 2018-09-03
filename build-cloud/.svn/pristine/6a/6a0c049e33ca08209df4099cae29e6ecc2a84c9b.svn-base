package com.build.cloud.modules.bs.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.entity.OpPublicProjectEntity;

/**
 * 
 * @className IBsProjectFileService
 * @descripion 档案管理
 * @author huangchao
 * @date 2018年4月11日下午3:08:00
 */
public interface IBsProjectService extends IService<BsProjectEntity>{
	
	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 根据用户ID获取所在公司所有项目
	 * @param userId
	 * @return
	 */
	public List<BsProjectEntity> getProjectListByCompanyId(String companyId);
	
	/**
	 * 根据项目id查找项目信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getProById(Map<String, Object> params);
	
	/**
	 * 查询生产计划引用项目记录条数
	 * @param projectId
	 * @return
	 */
	public int getPlanCiteProject(String projectId);
	/**
	 * 获取公共项目list
	 * @param params
	 * @return
	 */
	public List<OpPublicProjectEntity> queryList(Map<String, Object> params);
	/**
	 * 生产计划相关--根据登录用户获取项目id(总包登录则返回总包项目id，分包登录则先查公共项目，再找到总包项目id返回)
	 * @param projectId
	 * @return
	 */
	public Map<String,String> quryProProjectId(String projectId);
}
