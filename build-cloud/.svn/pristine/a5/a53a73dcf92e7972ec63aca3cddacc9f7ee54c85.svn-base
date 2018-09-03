package com.build.cloud.modules.mat.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.build.cloud.modules.bs.entity.BsTeamEntity;
import com.build.cloud.modules.mat.entity.MatTakeInventoryEntity;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 盘存表 Mapper 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
public interface MatTakeInventoryDao extends BaseMapper<MatTakeInventoryEntity> {
  
	/**
	 * 分页查询盘存数据
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> pageList(Page<Map<String, Object>> page, Map<String, Object> params);
	
	/**
	 * 查询盘存对应出库信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> outNumberList(Map<String, Object> params);
	
	
	/**
	 * 查询盘存对应任务单完成情况信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> takeNumberList(Map<String, Object> params);
	
	/**
	 * 查询任务单对应父级
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> takeNumberParent(Map<String, Object> params);
	
	/**
	 * 查询实际任务消耗量
	 * @param params
	 */
	BigDecimal takeNumber(Map<String, Object> params);
	
	/**
	 * 查询盘存物料清单
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> outNumberListAdd(Map<String, Object> params);

	/**
	 * @param page
	 * @param params
	 * @return
	 */
	List<BsTeamEntity> getTeamList(Page<BsTeamEntity> page, Map<String, Object> params);

	/**
	 * @param key
	 */
	void delete(String key);
	
}
