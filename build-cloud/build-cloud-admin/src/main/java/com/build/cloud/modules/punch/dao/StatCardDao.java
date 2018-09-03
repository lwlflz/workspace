package com.build.cloud.modules.punch.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.punch.entity.StatCardEntity;

/**
 * 
 * @ClassName: StatCardDao   
 * @Description: 考勤记录表
 * @author: liutao
 * @date: 2018年5月21日 下午8:38:50
 */
public interface StatCardDao extends BaseMapper<StatCardEntity> {
	
	public Integer selectWorkerStatCard(Map<String, Object> params);
	
}
