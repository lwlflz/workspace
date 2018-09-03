package com.build.cloud.modules.productplan.service;

import com.build.cloud.modules.productplan.dto.ProPlanDetail;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface IProPlanDetailService extends IService<ProPlanDetail> {
	void physicsDelete(String proId);

	List<ProPlanDetail> queryPlanDetailList(String projectId);
	
	List<ProPlanDetail> selectAuthPlanDetailList(Map<String,String> map);
	
	List<ProPlanDetail> selectLaborPlanDetailList(Map<String,String> map);
}
