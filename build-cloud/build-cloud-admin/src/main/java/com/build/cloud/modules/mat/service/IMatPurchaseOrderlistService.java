package com.build.cloud.modules.mat.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;

/**
 * <p>Title: IMatPurchaseOrderlistService</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:52:40
 */
public interface IMatPurchaseOrderlistService extends IService<MatPurchaseOrderlistEntity>{

	/**
	 * g根据orderId 查询清单
	 * @param string 
	 * @return
	 */
	List<MatPurchaseOrderlistEntity> queryList(String string);

	/**
	 * 根据orderId 删除清单
	 * @param orderId
	 */
	void deleteOrderId(String orderId);

//	/**
//	 * 查询已下单数量
//	 * @param param
//	 * @return
//	 */
//	BigDecimal selectSumUsedNumber(Map<String, Object> param);

//	/**
//	 * @param orderType
//	 * @param list
//	 * @return
//	 */
//	MatPurchaseOrderlistEntity selectUsedNumer(String orderType, MatPurchaseOrderlistEntity list);


}
