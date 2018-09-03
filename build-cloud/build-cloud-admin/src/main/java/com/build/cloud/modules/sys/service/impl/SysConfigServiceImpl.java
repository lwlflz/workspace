package com.build.cloud.modules.sys.service.impl;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysConfigDao;
import com.build.cloud.modules.sys.entity.SysConfigEntity;
import com.build.cloud.modules.sys.redis.SysConfigRedis;
import com.build.cloud.modules.sys.service.ISysConfigService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements ISysConfigService {
	@Autowired
	private SysConfigRedis sysConfigRedis;
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = MapUtil.getStr(params, "key");
		Page<SysConfigEntity> page =
			this.selectPage(new Query<SysConfigEntity>(params).getPage(),
				new EntityWrapper<SysConfigEntity>().like(StrUtil.isNotBlank(key), "key", key).eq("status", 1));
		return new PageUtils(page);
	}
	@Override
	public void save(SysConfigEntity config) {
		this.insert(config);
		sysConfigRedis.saveOrUpdate(config);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		this.updateById(config);
		sysConfigRedis.saveOrUpdate(config);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		baseMapper.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] ids) {
		for (String id : ids) {
			SysConfigEntity config = this.selectById(id);
			sysConfigRedis.delete(config.getKey());
		}
		this.deleteBatchIds(Arrays.asList(ids));
	}
	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if (config == null) {
			config = baseMapper.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}
		return config == null ? null : config.getValue();
	}
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if (StrUtil.isNotBlank(value)) {
			return JSON.parseObject(value, clazz);
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new BusinessException("获取参数失败");
		}
	}
}