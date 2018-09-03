package com.build.cloud.modules.sta.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.sta.StaEmploymentBean;
import com.build.cloud.modules.sta.entity.StaEmploymentEntity;

public interface IStaEmploymentService extends IService<StaEmploymentEntity> {
	
	public void syncWorkerRecord();
	
	public List<StaEmploymentBean> selectStaEmployment(Map<String, Object> params);
	
	public List<Map<String, Object>> selectByDay(Map<String, Object> params);
}
