package com.build.cloud.modules.mat.service.impl;

import java.util.List;

import com.build.cloud.modules.mat.entity.MatPurchaseStockInlistEntity;
import com.build.cloud.modules.mat.dao.MatPurchaseStockInlistDao;
import com.build.cloud.modules.mat.service.IMatPurchaseStockInlistService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 入库详情表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@Service
public class MatPurchaseStockInlistServiceImpl extends ServiceImpl<MatPurchaseStockInlistDao, MatPurchaseStockInlistEntity> implements IMatPurchaseStockInlistService {

	@Override
	public List<MatPurchaseStockInlistEntity> selectListInfo(String id) {
		return baseMapper.selectListInfo(id);
	}

}
