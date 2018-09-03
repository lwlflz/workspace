package com.build.cloud.modules.mat.service.impl;

import java.util.List;

import com.build.cloud.modules.mat.entity.MatPurchaseConlistEntity;
import com.build.cloud.modules.mat.dao.MatPurchaseConlistDao;
import com.build.cloud.modules.mat.service.IMatPurchaseConlistService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 物资采购清单 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
@Service
public class MatPurchaseConlistServiceImpl extends ServiceImpl<MatPurchaseConlistDao, MatPurchaseConlistEntity> implements IMatPurchaseConlistService {

	@Override
	@Transactional
	public void update(List<MatPurchaseConlistEntity> list) {
		EntityWrapper<MatPurchaseConlistEntity> wrapper = new EntityWrapper<MatPurchaseConlistEntity>();
		wrapper.eq("con_id", list.get(0).getConId());
		baseMapper.delete(wrapper);
		this.insertBatch(list);
	}

	@Override
	public void deleteAsId(String id) {
		baseMapper.deleteAsId(id);
	}

}
