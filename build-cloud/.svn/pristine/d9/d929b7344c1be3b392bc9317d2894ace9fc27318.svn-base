package com.build.cloud.modules.sta.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sta.StaEmploymentBean;
import com.build.cloud.modules.sta.entity.StaEmploymentEntity;

public interface StaEmploymentDao extends BaseMapper<StaEmploymentEntity> {
	
	public List<StaEmploymentEntity> selectWorkerRecord();
	
	public List<StaEmploymentBean> selectStaEmployment(Map<String, Object> params);
	
	public List<Map<String, Object>> selectByDay(Map<String, Object> params);
}
