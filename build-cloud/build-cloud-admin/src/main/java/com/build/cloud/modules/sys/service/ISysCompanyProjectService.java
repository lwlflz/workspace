/**
 * 
 */
package com.build.cloud.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sys.entity.SysCompanyProjectEntity;

/**
 * @className 
 * @descripion 
 * @author huangchao
 * @date 2018年4月29日上午1:04:23
 */
public interface ISysCompanyProjectService extends IService<SysCompanyProjectEntity>{

	/**
	 * 根据公司ID查询公司对应的项目ID集合
	 * @param companyId
	 * @return
	 */
	public SysCompanyProjectEntity findPanyProjectByCompanyId(Map<String, Object> params);
	
	/**
	 * 项目分配
	 * @param companyId 公司ID
	 * @param projectId 项目ID
	 * @return
	 */
	void findPanyProjectByCompanyId(String companyId,List<String> projectIds,String userId);
}
