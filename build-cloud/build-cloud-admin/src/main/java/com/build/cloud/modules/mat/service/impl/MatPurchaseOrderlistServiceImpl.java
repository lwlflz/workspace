package com.build.cloud.modules.mat.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.mat.dao.MatPurchaseOrderlistDao;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderService;
import com.build.cloud.modules.mat.service.IMatPurchaseOrderlistService;
import com.google.common.collect.Maps;


/**
 * <p>Title: MatPurchaseOrderlistServiceImpl</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:54:33
 */
@Service
public class MatPurchaseOrderlistServiceImpl extends ServiceImpl<MatPurchaseOrderlistDao, MatPurchaseOrderlistEntity> implements IMatPurchaseOrderlistService{

	@Autowired
	private IMatPurchaseOrderService matPurchaseOrderService;
	@Override
	public List<MatPurchaseOrderlistEntity> queryList(String orderId) {
		MatPurchaseOrderEntity orderEntity = matPurchaseOrderService.selectById(orderId);
		List<MatPurchaseOrderlistEntity> list = new ArrayList<MatPurchaseOrderlistEntity>();
		//先查询出清单信息
		Map<String, Object> map = Maps.newHashMap();
		map.put("orderId", orderId);
		map.put("orderType", orderEntity.getOrderType());
		List<MatPurchaseOrderlistEntity> selectByMap = baseMapper.selectByList(map);
		for (MatPurchaseOrderlistEntity  orderlistEntity: selectByMap) {
			String planlistId = orderlistEntity.getPlanlistId();
			//获取一个已下单的总数
			HashMap<String, Object> param = Maps.newHashMap();
			param.put("id", planlistId); 
			param.put("orderType", orderEntity.getOrderType());
			BigDecimal selectSumUsedNumber = baseMapper.selectSumUsedNumber(param);
//			BigDecimal otherNumber = new BigDecimal(0);
//			if (selectSumUsedNumber != null) {
//				otherNumber = selectSumUsedNumber.subtract(orderlistEntity.getOrderNumber());
//			}
//			//其他订单所下的数量
//	
//			//当前可下单数
//			BigDecimal nowNumber = orderlistEntity.getPlanNumber().subtract(otherNumber);
			orderlistEntity.setUsedNumber(selectSumUsedNumber);
			list.add(orderlistEntity);
		}
		
		for(MatPurchaseOrderlistEntity entity:list) {
			if(entity.getWarehousNumber()==null) {
				entity.setWarehousNumber(new BigDecimal("0"));
			}
		}
		return list;
	}
//	@Override
//	public MatPurchaseOrderlistEntity selectUsedNumer(String orderType, MatPurchaseOrderlistEntity entity){
//		String planlistId = entity.getPlanlistId();
//		List<String> idList = Arrays.asList(planlistId.split(","));
//		//获取一个已下单的总数
//		HashMap<String, Object> param = Maps.newHashMap();
//		param.put("list", idList); 
//		param.put("orderType", orderType);
//		BigDecimal selectSumUsedNumber = baseMapper.selectSumUsedNumber(param);
//		BigDecimal otherNumber = new BigDecimal(0);
//		if (selectSumUsedNumber != null) {
//			otherNumber = selectSumUsedNumber.subtract(entity.getOrderNumber());
//		}
//		//当前可下单数
//		BigDecimal nowNumber = entity.getPlanNumber().subtract(otherNumber);
//		entity.setUsedNumber(nowNumber);
//		return entity;
//	}
	@Override
	public void deleteOrderId(String orderId) {
		// TODO Auto-generated method stub
		baseMapper.deleteByOrderId(orderId);
	}
//	@Override
//	public BigDecimal selectSumUsedNumber(Map<String, Object> param) {
//		return baseMapper.selectSumUsedNumber(param);
//	}
}
