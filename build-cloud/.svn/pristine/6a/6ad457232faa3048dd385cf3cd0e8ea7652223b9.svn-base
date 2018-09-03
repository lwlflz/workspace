package com.build.cloud.modules.bs.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsBussinessTypeDao;
import com.build.cloud.modules.bs.entity.BsBussinessTypeEntity;
import com.build.cloud.modules.bs.service.IBsBussinessTypeService;

/**
 * 
* Title: BsBussinessTypeServiceImpl
* Description: 客商分类表
* @author 鲁四围 
* @date 2018年4月11日
 */
@Service("bsBussinessTypeService")
public class BsBussinessTypeServiceImpl extends ServiceImpl<BsBussinessTypeDao, BsBussinessTypeEntity> implements IBsBussinessTypeService{
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<BsBussinessTypeEntity> page =
			this.selectPage(new Query<BsBussinessTypeEntity>(params).getPage(), new EntityWrapper<BsBussinessTypeEntity>());
		return new PageUtils(page);
	}

}
