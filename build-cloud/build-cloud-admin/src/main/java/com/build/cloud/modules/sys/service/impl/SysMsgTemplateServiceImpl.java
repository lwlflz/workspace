package com.build.cloud.modules.sys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysMsgTemplateDao;
import com.build.cloud.modules.sys.entity.SysMsgTemplateEntity;
import com.build.cloud.modules.sys.service.ISysMsgTemplateService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 
* Title: 短信模板实现类
* Description:  
* @author 鲁四围 
* @date 2018年3月29日
 */
@Service("sysMsgTemplateService")
public class SysMsgTemplateServiceImpl  extends ServiceImpl<SysMsgTemplateDao, SysMsgTemplateEntity> implements ISysMsgTemplateService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String status = MapUtil.getStr(params, "status");
		String tplType = MapUtil.getStr(params, "tplType");
		EntityWrapper<SysMsgTemplateEntity> wrapper = new EntityWrapper<SysMsgTemplateEntity>();
		wrapper.eq(StrUtil.isNotBlank(status),"status", status);
		wrapper.eq(StrUtil.isNotBlank(tplType),"tpl_type", tplType);
		Page<SysMsgTemplateEntity> page = 
				this.selectPage(new Query<SysMsgTemplateEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}

}
