package com.build.cloud.modules.mat.dao;

import com.build.cloud.modules.mat.entity.MatPurchasePlanEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 库存表 Mapper 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface MatPurchaseStockDao extends BaseMapper<MatPurchaseStockEntity> {
	/**
	 * 分页查询
	 */
	public List<MatPurchaseStockEntity> selectPage(Page<MatPurchaseStockEntity> page, Map<String, Object> params);
}
