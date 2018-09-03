package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumEntity;

public interface MatPurchasePlanSumDao extends BaseMapper<MatPurchasePlanSumEntity>{

	
	/**
	 * 查询月度采购计划汇总  --分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPurchasePlanSumPage(Pagination page, Map<String, Object> params);
	
	/**
	 * 查询月度采购计划汇总  --分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPurchasePlanSumPage(Map<String, Object> params);
	/**
	 * 查询月度采购计划汇总--详细信息
	 * @param id
	 * @return
	 */
	public MatPurchasePlanSumEntity getPurchasePlanSumById(String id);
	
	/**
	 * 参照
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getPlanSumList(Page<Map<String, Object>> page, Map<String, Object> params);
}
