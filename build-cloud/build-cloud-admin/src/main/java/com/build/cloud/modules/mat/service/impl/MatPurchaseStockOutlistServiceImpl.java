package com.build.cloud.modules.mat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.mat.dao.MatPurchaseStockOutlistDao;
import com.build.cloud.modules.mat.entity.MatPurchaseStockOutlistEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockOutlistService;


@Service
public class MatPurchaseStockOutlistServiceImpl extends ServiceImpl<MatPurchaseStockOutlistDao, MatPurchaseStockOutlistEntity> implements IMatPurchaseStockOutlistService {
	@Override
	public List<MatPurchaseStockOutlistEntity> selectListInfo(String id) {
		return baseMapper.selectListInfo(id);
	}
}
