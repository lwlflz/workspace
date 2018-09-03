package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutEntity;
import com.build.cloud.modules.productplan.dto.ProContract;

/**
 * <p>
 * 出库表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface IMatPurchaseStockOutService extends IService<MatPurchaseStockOutEntity> {

	PageUtils queryPageList(Map<String, Object> params);

	MatPurchaseStockOutEntity save(MatPurchaseStockOutEntity entity);

	MatPurchaseStockOutEntity info(String id);

	void update(MatPurchaseStockOutEntity entity);

	Integer getInventory(Map<String, Object> params);

	void endReturn(MatPurchaseStockOutEntity entity);

	void reject(MatPurchaseStockOutEntity entity);

	void check(MatPurchaseStockOutEntity entity);

	void submit(MatPurchaseStockOutEntity entity);

	/**
	 * 查询项目材料的库存
	 * projectId 项目ID
	 * mtr_code 材料编码
	 * @param entity
	 * @return
	 */
//	MatPurchaseStockEntity getMatPurchaseStockEntity(MatPurchaseStockOutlistEntity entity);

	/**
	 * 根据 出库单下出库详情修改库存
	 * @param id 出库ID
	 */
	boolean updateMatPurchaseStockEntity(String id);

	String verifyStock(MatPurchaseStockOutEntity entity);

	PageUtils findMaterialsList(Map<String, Object> params);
	/**
	 * 劳务班组合同参照
	 * @param params
	 * @return
	 */
	PageUtils getConList(Map<String, Object> params);
}
