package com.build.cloud.core.base.service.impl;
import java.util.Map;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.mapper.BaseMapper;
import com.build.cloud.core.base.service.ICommonService;
public class CommonServiceImpl<D extends BaseMapper<T>, T> extends BaseServiceImpl<BaseMapper<T>, T>
		implements ICommonService<T> {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		return null;
	}
}
