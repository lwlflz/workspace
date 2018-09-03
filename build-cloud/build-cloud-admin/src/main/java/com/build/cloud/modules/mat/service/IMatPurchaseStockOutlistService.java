package com.build.cloud.modules.mat.service;

import com.build.cloud.modules.mat.entity.MatPurchaseStockOutlistEntity;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 出库详情表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface IMatPurchaseStockOutlistService extends IService<MatPurchaseStockOutlistEntity> {

	/**
	 * 根据出库id查询清单list
	 * @param id
	 * @return
	 */
	List<MatPurchaseStockOutlistEntity> selectListInfo(String id);

}
