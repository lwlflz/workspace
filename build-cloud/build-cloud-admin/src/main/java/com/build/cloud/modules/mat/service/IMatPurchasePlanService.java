package com.build.cloud.modules.mat.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;

/**
 * <p>Title: IMatPurchasePlanService</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午2:32:19
 */
public interface IMatPurchasePlanService extends IService<MatPurchasePlanEntity>{
	public PageUtils queryPage(Map<String,Object> params);

	/**
	 * @param entity
	 */
	public void insertPurchasePlan(MatPurchasePlanEntity entity);

	/**
	 * @param entity
	 */
	public void updatePurchasePlan(MatPurchasePlanEntity entity);
	
	/**
	 * 分页查询
	 * @param page
	 * @param params
	 * @return
	 */
	public PageUtils selectPage(Map<String, Object> params);

	/**
	 * @param entity
	 */
	public void submit(MatPurchasePlanEntity entity);

	/**
	 * @param entity
	 */
	public void check(MatPurchasePlanEntity entity);

	/**
	 * @param entity
	 */
	public void reject(MatPurchasePlanEntity entity);

	/**
	 * @param entity
	 */
	public void endReturn(MatPurchasePlanEntity entity);
	/**
	 * 根据id删除采购计划
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * 采购计划汇总参照
	 * @param params
	 * @return
	 */
	PageUtils getPlanList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public PageUtils selectReferList(Map<String, Object> params);
}
