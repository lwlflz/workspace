package com.build.cloud.modules.bs.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsMtrKindArchiveDao;
import com.build.cloud.modules.bs.entity.BsMtrKindArchiveEntity;
import com.build.cloud.modules.bs.service.IBsMtrKindArchiveService;
import com.build.cloud.modules.sys.entity.SysMsgTemplateEntity;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 
 * @className BsMtrKindArchiveService
 * @descripion 材料分类档案service实现
 * @author WangJun
 * @date 2018年4月10日下午7:32:07
 */
@Service("bsMtrKindArchiveService")
public class BsMtrKindArchiveServiceImpl extends ServiceImpl<BsMtrKindArchiveDao,BsMtrKindArchiveEntity> implements IBsMtrKindArchiveService{

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String mtrKindCode = MapUtil.getStr(params, "mtrKindCode");
		String mtrKindName = MapUtil.getStr(params, "mtrKindName");
		EntityWrapper<BsMtrKindArchiveEntity> wrapper = new EntityWrapper<BsMtrKindArchiveEntity>();
		wrapper.like(StrUtil.isNotBlank(mtrKindCode),"mtr_kind_code", mtrKindCode);
		wrapper.like(StrUtil.isNotBlank(mtrKindName),"mtr_kind_name", mtrKindName);
		Page<BsMtrKindArchiveEntity> page = 
				this.selectPage(new Query<BsMtrKindArchiveEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}

}
