package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;

/**
 * <p>Title: MatPurchasePlanDao</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午2:31:37
 */
public interface MatPurchasePlanDao extends BaseMapper<MatPurchasePlanEntity>{
	/**
	 * 查询最大单据号
	 * @return
	 */
	public String selectMaxBillCode(Map<String,Object> params);
	/**
	 * 分页查询
	 * @param page
	 * @param params
	 * @return
	 */
	public List<MatPurchasePlanEntity> selectPage(Page<MatPurchasePlanEntity> page, Map<String, Object> params);
	/**
	 * 根据id删除计划
	 */
	public void deleteById(String id);
	
	/**
	 * 参照
	 * @param page
	 * @param params
	 * @return
	 */
	List<MatPurchasePlanEntity> getPlanList(Page<MatPurchasePlanEntity> page, Map<String, Object> params);

	/**
	 * @param page
	 * @param params
	 * @return
	 */
	public List<MatPurchasePlanEntity> selectReferPage(Page<MatPurchasePlanEntity> page, Map<String, Object> params);
}
