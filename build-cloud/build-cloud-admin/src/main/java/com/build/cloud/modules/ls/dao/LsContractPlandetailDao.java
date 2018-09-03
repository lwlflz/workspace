package com.build.cloud.modules.ls.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.build.cloud.modules.ls.entity.LsContractPlandetailEntity;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 劳务分包合同拆分详情 Mapper 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-08-27
 */
public interface LsContractPlandetailDao extends BaseMapper<LsContractPlandetailEntity> {
	
	/**
	 * 劳务分包合同拆分生产计划参照
	 * @param params
	 * @return
	 */
	List<ProPlanDetail> selectLayeredPlan(Map<String, Object> params);
	/**
	 * 根据合同删除
	 * @param conId
	 */
	void deleteByConId(@Param("conId") String conId);
	
	/**
	 * 查询合同拆分详情对应生产计划信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectLsConPlan(Map<String, Object> params);
	/**
	 *  查询合同拆分详情对应生产计划父级信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectLsConPlanParent(Map<String, Object> params);
	
}
