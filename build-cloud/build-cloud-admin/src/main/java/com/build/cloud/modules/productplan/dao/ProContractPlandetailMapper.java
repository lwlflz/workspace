package com.build.cloud.modules.productplan.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-04-26
 */
public interface ProContractPlandetailMapper extends BaseMapper<ProContractPlandetail> {
	void physicsDelete(String conId);
	
	List<ProContractPlandetail> selectConDetail(ProContractPlandetail pcp);
	
	Map<String,Integer> selectLaborFinishedCount(Map<String,String> map);
}