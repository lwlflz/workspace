/**
 * 
 */
package com.build.cloud.modules.bs.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsMeasureUnitDao;
import com.build.cloud.modules.bs.entity.BsMeasureUnitEntity;
import com.build.cloud.modules.bs.entity.BsMtrKindArchiveEntity;
import com.build.cloud.modules.bs.service.IBsMeasureUnitService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @className BsMeasureUnitServiceImpl
 * @descripion 计量单位service实现
 * @author WangJun
 * @date 2018年4月11日下午4:18:20
 */
@Service("bsMeasureUnit")
public class BsMeasureUnitServiceImpl extends ServiceImpl<BsMeasureUnitDao,BsMeasureUnitEntity> implements IBsMeasureUnitService{


	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String measureName = MapUtil.getStr(params, "measureName");
		String measureCode = MapUtil.getStr(params, "measureCode");
		EntityWrapper<BsMeasureUnitEntity> wrapper = new EntityWrapper<BsMeasureUnitEntity>();
		wrapper.like(StrUtil.isNotBlank(measureName),"measure_name", measureName);
		wrapper.like(StrUtil.isNotBlank(measureCode),"measure_code", measureCode);
		Page<BsMeasureUnitEntity> page = 
				this.selectPage(new Query<BsMeasureUnitEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}
	
}
