package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.mat.entity.MatPurchaseStockEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutEntity;
import com.build.cloud.modules.productplan.dto.ProContract;

/**
 * <p>
 * 出库表 Mapper 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface MatPurchaseStockOutDao extends BaseMapper<MatPurchaseStockOutEntity> {

	/**
	 * 分页查询出库信息
	 * 
	 * @param page
	 * @param params
	 * @return
	 */
	List<MatPurchaseStockOutEntity> queryPageList(Page<MatPurchaseStockOutEntity> page, Map<String, Object> params);

	Integer getInventory(Map<String, Object> params);
	
	List<MatPurchaseStockEntity> queryStockPageList(Page<MatPurchaseStockEntity> page, Map<String, Object> params);
	/**
	 * 查询出库单最小出库时间是否在盘存时间之前
	 * @param params
	 * @return
	 */
	Integer selectTakeTimes(Map<String, Object> params);
	/**
	 * 劳务班组合同参照
	 * @param params
	 * @return
	 */
	List<ProContract> getConList(Page<ProContract> page, Map<String, Object> params);
}
