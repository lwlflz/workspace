package com.build.cloud.modules.ls.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.ls.dao.LsContractLaborPaymentDao;
import com.build.cloud.modules.ls.entity.LsContractLaborPaymentEntity;
import com.build.cloud.modules.ls.service.ILsContractLaborPaymentService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

@Service("lsContractLaborPaymentService")
public class LsContractLaborPaymentServiceImpl extends ServiceImpl<LsContractLaborPaymentDao, LsContractLaborPaymentEntity> implements ILsContractLaborPaymentService{

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String conId = MapUtil.getStr(params, "conId");
		EntityWrapper<LsContractLaborPaymentEntity> wrapper = new EntityWrapper<LsContractLaborPaymentEntity>();
		wrapper.eq(StrUtil.isNotBlank(conId),"con_id", conId);
		Page<LsContractLaborPaymentEntity> page = 
				this.selectPage(new Query<LsContractLaborPaymentEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}
}
