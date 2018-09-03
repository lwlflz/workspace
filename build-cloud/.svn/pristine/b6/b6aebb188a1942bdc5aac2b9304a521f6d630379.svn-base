package com.build.cloud.modules.sys.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.dao.SysDictDao;
import com.build.cloud.modules.sys.entity.SysDictEntity;
import com.build.cloud.modules.sys.service.ISysDictService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements ISysDictService {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String name = (String)params.get("name");
		String code = (String)params.get("code");
		String orderNum = (String)params.get("orderNum");
		EntityWrapper<SysDictEntity> wrapper = new EntityWrapper<SysDictEntity>();
		wrapper.like(StrUtil.isNotBlank(name), "name", name);
		wrapper.like(StrUtil.isNotBlank(code), "code", code);
		wrapper.like(StrUtil.isNotBlank(orderNum), "order_num", orderNum);
		Page<SysDictEntity> page = this.selectPage(new Query<SysDictEntity>(params).getPage(), wrapper);
		return new PageUtils(page);
	}
	@Override
	public Map<String, List<DictBean>> selectAll() {
		Map<String, List<DictBean>> dictMap = redisUtils.get(RedisKeys.getDictKey("jsy"), Map.class);
		if (CollectionUtil.isEmpty(dictMap)) {
			List<SysDictEntity> list = super.selectList(new EntityWrapper<SysDictEntity>());
			dictMap = Maps.newConcurrentMap();
			for (SysDictEntity dict : list) {
				if (dictMap.get(dict.getType()) == null) {
					DictBean bean = new DictBean();
					BeanUtil.copyProperties(dict, bean);
					dictMap.put(dict.getType(), Lists.newArrayList(bean));
				} else {
					List<DictBean> l = dictMap.get(dict.getType());
					DictBean bean = new DictBean();
					BeanUtil.copyProperties(dict, bean);
					l.add(bean);
					dictMap.put(dict.getType(), l);
				}
			}
			redisUtils.set(RedisKeys.getDictKey("jsy"), dictMap);
		}
		return dictMap;
	}
}
