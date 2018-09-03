package com.build.cloud.modules.punch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.build.cloud.core.base.mapper.BaseMapper;
import com.build.cloud.modules.punch.bean.PunchBean;
import com.build.cloud.modules.punch.entity.PunchEntity;


@Mapper
public interface PunchDao extends BaseMapper<PunchEntity> {
	
	public List<PunchEntity> queryStatCard();
	
	public List<PunchBean> queryPunch(Map<String, Object> params);
	
	public Map<String, Object> queryEntryTime(Map<String, Object> params);

	public String queryExitTimes(Map<String, Object> params);
	
}
