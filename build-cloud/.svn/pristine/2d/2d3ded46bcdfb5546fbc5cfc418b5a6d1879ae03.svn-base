package com.build.cloud.modules.mat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.mat.dao.MatPurchasePlanSumlistDao;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumlistService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
@Service("purchasePlanSumlistService")
public class MatPurchasePlanSumlistServiceImpl extends ServiceImpl<MatPurchasePlanSumlistDao, MatPurchasePlanSumListEntity> implements IMatPurchasePlanSumlistService{

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String planId = MapUtil.getStr(params, "planId");
		EntityWrapper<MatPurchasePlanSumListEntity> wrapper = new EntityWrapper<MatPurchasePlanSumListEntity>();
		wrapper.eq(StrUtil.isNotBlank(planId),"plan_id", planId);
		Page<MatPurchasePlanSumListEntity> page = 
				this.selectPage(new Query<MatPurchasePlanSumListEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}

	@Override
	public List<MatPurchasePlanSumListEntity> selectByIds(List<String> idList) {
		// TODO Auto-generated method stub
		
		return baseMapper.selectByIds(idList);
	}
	
	
}
