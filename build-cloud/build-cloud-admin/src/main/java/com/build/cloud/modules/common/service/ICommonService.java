package com.build.cloud.modules.common.service;

import java.util.Map;


/**
 * <p>Title: CommonService</p>  
 * <p>Description: 公共服务类</p>  
 * @author WangJun
 * @date 2018年7月12日 下午2:48:11
 */
public interface ICommonService {
	
	/**
	 * 获取（单据号）最大值
	 * @param params
	 * 字段名：paramName
	 * 表名：tableName
	 * @return
	 */
	public String selectMaxCode(Map<String,Object> params);
	
	/**
	 * 劳务分包获取（单据号）最大值
	 * @param params
	 * 字段名：paramName
	 * 表名：tableName
	 * @return
	 */
	public String selectMaxCodeByLsContractLobar();
	
	/**
	 * 获取当前公司在所选项目中所属的角色
	 * @param companyId
	 * @param projectId
	 * @return
	 */
	public Map<String, String> getCompanyProType(String companyId, String projectId);
}
