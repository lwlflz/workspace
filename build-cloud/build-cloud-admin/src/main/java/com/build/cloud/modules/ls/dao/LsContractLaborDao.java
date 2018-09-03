package com.build.cloud.modules.ls.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.build.cloud.modules.ls.entity.LsContractLaborEntity;

public interface LsContractLaborDao extends BaseMapper<LsContractLaborEntity>{

	/**
	 * 查询劳务分包合同 --收入列表 --分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findContractLaborIncome(Pagination page, Map<String, Object> params);
	
	
	/**
	 * 查询劳务分包合同--支出列表 --分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findContractLaborSpending(Pagination page, Map<String, Object> params);
	
	
	/**
	 * 查询劳务分包合同列表 --分页(不区分支出、收入)
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getContractLaborAll(Pagination page, Map<String, Object> params);
	
	/**
	 * 查询劳务分包合同--详细信息
	 * @param id
	 * @return
	 */
	public LsContractLaborEntity getContractLaborById(String id);
	
	/**
	 * 查询劳务分包合同列表 --(不区分支出、收入)
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getContractLaborAll(Map<String, Object> params);
	
	/**
	 * 查询劳务分包合同
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findContractLabor(Pagination page, Map<String, Object> params);
	
	
	
}
