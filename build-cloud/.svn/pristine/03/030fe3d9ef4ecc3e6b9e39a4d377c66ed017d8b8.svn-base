package com.build.cloud.modules.sys.util;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.SpringContextUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.entity.SysDictEntity;
import com.build.cloud.modules.sys.service.ISysDictService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
public class DictUtils {
	private static ISysDictService sysDictService = SpringContextUtils.getBean(ISysDictService.class);
	public static RedisUtils buildRedisUtils() {
		return (RedisUtils)SpringContextUtils.getBean("redisUtils");
	}
	public static List<DictBean> getDictList(String type) {
		Map<String, List<DictBean>> dictMap = buildRedisUtils().get(RedisKeys.getDictKey("ddjs"), Map.class);
		List<DictBean> dictList = null;
		if (dictMap != null) {
			dictList = dictMap.get(type);
		}
		if (CollectionUtil.isEmpty(dictMap) || CollectionUtil.isEmpty(dictList)) {
			dictMap = Maps.newHashMap();
			dictList = Lists.newArrayList();
			EntityWrapper<SysDictEntity> wrapper = new EntityWrapper<SysDictEntity>();
			Map<String, Object> params = Maps.newHashMap();
			params.put("corp_code", 0);
			params.put("corp_name", "ddjs");
			params.put("type", type);
			wrapper.allEq(params);
			wrapper.orderBy("order_num", true);
			List<SysDictEntity> dList = sysDictService.selectList(wrapper);
			for (SysDictEntity dictEntity : dList) {
				DictBean bean = new DictBean();
				BeanUtil.copyProperties(dictEntity, bean);
				dictList.add(bean);
			}
			dictMap.put(type, dictList);
		} else {
			dictList = JSON.parseArray(JSON.toJSONString(dictList), DictBean.class);
		}
		return dictList;
	}
	public static String getDictLabel(String value, String type, String defaultValue) {
		if (StrUtil.isNotBlank(type) && StrUtil.isNotBlank(value)) {
			for (DictBean dict : getDictList(type)) {
				if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
					return dict.getName();
				}
			}
		}
		return defaultValue;
	}
	public static String getDictLabelByCode(String code, String type, String defaultValue) {
		if (StrUtil.isNotBlank(type) && StrUtil.isNotBlank(code)) {
			for (DictBean dict : getDictList(type)) {
				if (type.equals(dict.getType()) && code.equals(dict.getCode())) {
					return dict.getName();
				}
			}
		}
		return defaultValue;
	}
	public static String getDictLabels(String values, String type, String defaultValue) {
		if (StrUtil.isNotBlank(type) && StrUtil.isNotBlank(values)) {
			List<String> valueList = Lists.newArrayList();
			for (String value : StrUtil.split(values, ",")) {
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StrUtil.join(",", valueList);
		}
		return defaultValue;
	}
	public static String getDictValue(String label, String type, String defaultLabel) {
		if (StrUtil.isNotBlank(type) && StrUtil.isNotBlank(label)) {
			for (DictBean dict : getDictList(type)) {
				if (type.equals(dict.getType()) && label.equals(dict.getCode())) {
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
}
