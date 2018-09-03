package com.build.cloud.modules.mat.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.mat.dao.MatPurchasePlanlistDao;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanlistService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: MatPurchasePlanlistServiceImpl</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午3:24:11
 */
@Service
public class MatPurchasePlanlistServiceImpl extends ServiceImpl<MatPurchasePlanlistDao, MatPurchasePlanlistEntity> implements IMatPurchasePlanlistService{

	@Override
	public List<MatPurchasePlanlistEntity> queryList(Map<String, Object> params) {
		EntityWrapper<MatPurchasePlanlistEntity> wrapper = new EntityWrapper<MatPurchasePlanlistEntity>();
		String planId = MapUtil.getStr(params, "planId");
		wrapper.eq(StrUtil.isNotBlank(planId),"plan_id", planId);
		
		return baseMapper.selectList(wrapper);
	}

	
	@Override
	public void delteByPlanId(String planId) {
		baseMapper.deleteByPlanId(planId);
		
	}
	@Override
	public void updatePlanlist(List<MatPurchasePlanSumListEntity> list) {
		if(!CollectionUtil.isEmpty(list)) {
			for (MatPurchasePlanSumListEntity purchasePlanSumListEntity : list) {
				String planlistId = purchasePlanSumListEntity.getPlanlistId();
				if(!StrUtil.isBlank(planlistId)) {
					List<String> ids = Arrays.asList(planlistId.split(","));
					for (String id : ids) {
						System.out.println("***********************进入并执行SQL**************************");
						MatPurchasePlanlistEntity planlistEntity = baseMapper.selectById(id);
						//MatPurchasePlanlistEntity planlistEntity = new MatPurchasePlanlistEntity();
						planlistEntity.setUsedState("1");
						planlistEntity.setUsedNumber(planlistEntity.getPlanNumber());
						baseMapper.updateById(planlistEntity);
					}
				}
			}
		}
	}
	@Override
	public List<MatPurchasePlanlistEntity> selectByIds(List<String> idList) {
		
		return baseMapper.selectByIds(idList);
	}

}
