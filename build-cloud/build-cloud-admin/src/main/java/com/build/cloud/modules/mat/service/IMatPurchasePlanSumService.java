package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.ls.entity.LsContractLaborEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;

public interface IMatPurchasePlanSumService extends IService<MatPurchasePlanSumEntity>{

	public PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询月度采购计划汇总  --无分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPurchasePlanSumList(Map<String, Object> params);
	
	public void savePurchasePlanSum(MatPurchasePlanSumEntity entity);
	
	public void updatePurchasePlanSum(MatPurchasePlanSumEntity entity);
	
	/**
	 * 查询月度采购计划汇总--详细信息
	 * @param id
	 * @return
	 */
	public MatPurchasePlanSumEntity getPurchasePlanSumById(String id);
	
	/**
	 * @param entity
	 */
	public void submit(MatPurchasePlanSumEntity entity);

	/**
	 * @param entity
	 */
	public void check(MatPurchasePlanSumEntity entity);

	/**
	 * @param entity
	 */
	public void reject(MatPurchasePlanSumEntity entity);

	/**
	 * @param entity
	 */
	public void endReturn(MatPurchasePlanSumEntity entity);
	
	/**
	 * 参照接口
	 * @param params
	 * @return
	 */
	PageUtils getPlanSumList(Map<String, Object> params);

	/**
	 * 还原已修改数据
	 * @param id
	 */
	public void restorePlan(String id);

}
