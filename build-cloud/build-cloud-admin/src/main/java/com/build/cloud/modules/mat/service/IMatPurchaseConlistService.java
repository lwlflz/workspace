package com.build.cloud.modules.mat.service;

import java.util.List;

import com.build.cloud.modules.mat.entity.MatPurchaseConlistEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 物资采购清单 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
public interface IMatPurchaseConlistService extends IService<MatPurchaseConlistEntity> {
	
	/**
	 * 修改物资采购清单
	 * @param list
	 */
	void update(List<MatPurchaseConlistEntity> list);
	
	/**
	 * 根据id删除清单
	 * @param id
	 */
	void deleteAsId(String id);

}
