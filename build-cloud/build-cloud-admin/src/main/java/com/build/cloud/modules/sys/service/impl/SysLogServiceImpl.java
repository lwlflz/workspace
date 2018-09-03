package com.build.cloud.modules.sys.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysLogDao;
import com.build.cloud.modules.sys.entity.SysLogEntity;
import com.build.cloud.modules.sys.service.ISysLogService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements ISysLogService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = MapUtil.getStr(params, "userName");
		String startDateStr = MapUtil.getStr(params, "startDate");
		String endDateStr = MapUtil.getStr(params, "endDate");


		Date startDate = null;
		Date endDate = null;
		if (startDateStr != null) {
			startDate = DateUtils.stringToDate(startDateStr, DateUtils.DATE_PATTERN);
		}
		if (endDateStr != null) {
			endDate = DateUtils.stringToDate(endDateStr, DateUtils.DATE_PATTERN);
		}

		EntityWrapper<SysLogEntity> wrapper = new EntityWrapper<SysLogEntity>();
		wrapper.like(StrUtil.isNotBlank(key), "username", key);
		if( (startDate!=null && endDate==null) || (startDate==null&& endDate!= null)) {
			String sql = " DATE_FORMAT(create_date, '%Y-%c-%d') >= DATE_FORMAT({0}, '%Y-%c-%d') "
					+ " or DATE_FORMAT(create_date, '%Y-%c-%d') <= DATE_FORMAT({1}, '%Y-%c-%d')";

			wrapper.where(sql, startDate, endDate);
		} else if(startDate!=null && endDate!=null){
			String sql = " DATE_FORMAT(create_date, '%Y-%c-%d') >= DATE_FORMAT({0}, '%Y-%c-%d') "
					+ " and DATE_FORMAT(create_date, '%Y-%c-%d') <= DATE_FORMAT({1}, '%Y-%c-%d')";
			wrapper.where(sql, startDate, endDate);
		}	
		Page<SysLogEntity> page = this.selectPage(new Query<SysLogEntity>(params).getPage(), wrapper);

		return new PageUtils(page);
	}
}
