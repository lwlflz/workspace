package com.build.cloud.modules.mat.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;

/**
 * <p>Title: MatOurchaseOrderlist</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:46:02
 */
public interface MatPurchaseOrderlistDao extends BaseMapper<MatPurchaseOrderlistEntity>{
	/**
	 * 通过orderId 删除orderlist
	 * @param orderId
	 */
	public void deleteByOrderId(String orderId);
	/**
	 * 查询订单详情list
	 * @param params
	 * @return
	 */
	public List<MatPurchaseOrderlistEntity> selectByList(Map<String,Object> params);
	/**
	 * 查询对应清单已下单的总数
	 * @param params
	 * @return
	 */
	public BigDecimal selectSumUsedNumber(Map<String, Object> params);
}
