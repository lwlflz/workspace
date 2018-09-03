package com.build.cloud.modules.productplan.dao;

import com.build.cloud.modules.productplan.dto.ProPlanInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface ProPlanInfoMapper extends BaseMapper<ProPlanInfo> {
	Integer selectMaxVersionByProjectId(String projectId);
	
	void physicsDelete(String id);
}