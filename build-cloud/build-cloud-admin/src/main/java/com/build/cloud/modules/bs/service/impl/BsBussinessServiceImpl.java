package com.build.cloud.modules.bs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.dao.BsBussinessDao;
import com.build.cloud.modules.bs.entity.BsBussinessEntity;
import com.build.cloud.modules.bs.service.IBsBussinessService;
import com.mchange.lang.IntegerUtils;

/**
 * 
* Title: BsBussinessServiceImpl
* Description:  客商档案表
* @author 鲁四围 
* @date 2018年4月11日
 */
@Service("bsBussinessService")
public class BsBussinessServiceImpl extends ServiceImpl<BsBussinessDao, BsBussinessEntity> implements IBsBussinessService{
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<BsBussinessEntity> query = new Query<BsBussinessEntity>(params);
		Page<BsBussinessEntity> page = query.getPage();
		List<BsBussinessEntity> kindList = baseMapper.selectPageByBusId(page,params);
		page.setRecords(kindList);
		return new PageUtils(page);
	}
	@Override
	public PageUtils partbJoin(Map<String, Object> params) {
		Query<BsBussinessEntity> query = new Query<BsBussinessEntity>(params);
		Page<BsBussinessEntity> page = query.getPage();
		List<BsBussinessEntity> list = baseMapper.partbJoin(page,params);
		page.setRecords(list);
		return new PageUtils(page);
	}
	@Override
	public Result saveCompanyIdByBusId(String busId, String comId) {
		BsBussinessEntity selectByIdBs = baseMapper.selectById(busId);
		if(selectByIdBs != null) {
			selectByIdBs.setRelationCompanyId(comId);
			Integer update = baseMapper.updateAllColumnById(selectByIdBs);
			return Result.ok("关联成功");
		}else {
			return Result.error("当前客商id不存在");
		}
	}
}
