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
import com.build.cloud.modules.sys.dao.SysMsgLogDao;
import com.build.cloud.modules.sys.entity.SysMsgLogEntity;
import com.build.cloud.modules.sys.service.ISysMsgLogService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 
* Title: 消息日志实现接口
* Description:  
* @author 鲁四围 
* @date 2018年3月29日
 */
@Service("sysMsgLogService")
public class SysMsgLogServiceImpl  extends ServiceImpl<SysMsgLogDao, SysMsgLogEntity> implements ISysMsgLogService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String msgType = MapUtil.getStr(params, "msgType");
		String startDateStr = MapUtil.getStr(params, "startDate");
		String endDateStr = MapUtil.getStr(params, "endDate");
		String receiveCode = MapUtil.getStr(params, "receiveCode");
		
		String sendStatus = MapUtil.getStr(params, "sendStatus");
		EntityWrapper<SysMsgLogEntity> wrapper = new EntityWrapper<SysMsgLogEntity>();
		//增加消息类型条件
		wrapper.eq(StrUtil.isNotBlank(msgType),"msg_type", msgType);
		//增加接受者账号条件
		wrapper.eq(StrUtil.isNotBlank(receiveCode), "receive_code",receiveCode);
		//增加发送状态条件
		wrapper.eq(StrUtil.isNotBlank(sendStatus), "send_status",sendStatus);
		//增加时间条件
		Date startDate = null;
		Date endDate = null;
		if (startDateStr != null) {
			startDate = DateUtils.stringToDate(startDateStr, DateUtils.DATE_PATTERN);
		}
		if (endDateStr != null) {
			endDate = DateUtils.stringToDate(endDateStr, DateUtils.DATE_PATTERN);
		}
		if((startDate!=null && endDate==null) || (startDate==null&& endDate!= null)) {
			String sql = " DATE_FORMAT(send_date, '%Y-%c-%d') >= DATE_FORMAT({0}, '%Y-%c-%d') "
					+ " or DATE_FORMAT(send_date, '%Y-%c-%d') <= DATE_FORMAT({1}, '%Y-%c-%d')";

			wrapper.where(sql, startDate, endDate);
		} else if(startDate!=null && endDate!=null){
			String sql = " DATE_FORMAT(send_date, '%Y-%c-%d') >= DATE_FORMAT({0}, '%Y-%c-%d') "
					+ " and DATE_FORMAT(send_date, '%Y-%c-%d') <= DATE_FORMAT({1}, '%Y-%c-%d')";
			wrapper.where(sql, startDate, endDate);
		}	
		Page<SysMsgLogEntity> page = 
				this.selectPage(new Query<SysMsgLogEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}

}
