package com.build.cloud.modules.productplan.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface ProPlanDetailMapper extends BaseMapper<ProPlanDetail> {
	void physicsDelete(String proId);
	
	List<ProPlanDetail> queryPlanDetailList(String projectId);
	
	List<ProPlanDetail> selectAuthPlanDetailList(Map<String,String> map);
	
	List<ProPlanDetail> selectLaborPlanDetailList(Map<String,String> map);
}