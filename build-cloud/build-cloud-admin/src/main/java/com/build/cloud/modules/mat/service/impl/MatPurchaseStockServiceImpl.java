package com.build.cloud.modules.mat.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.mat.dao.MatPurchaseStockDao;
import com.build.cloud.modules.mat.entity.MatPurchaseStockEntity;
import com.build.cloud.modules.mat.service.IMatPurchaseStockService;

/**
 * <p>
 * 库存表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@Service
public class MatPurchaseStockServiceImpl extends ServiceImpl<MatPurchaseStockDao, MatPurchaseStockEntity> implements IMatPurchaseStockService {

	@Override
	public PageUtils queryPageList(Map<String,Object> params) {
		Query<MatPurchaseStockEntity> query = new Query<MatPurchaseStockEntity>(params);
		Page<MatPurchaseStockEntity> page = query.getPage();
		page.setRecords(baseMapper.selectPage(page, params));
		return new PageUtils(page);
	}
}
