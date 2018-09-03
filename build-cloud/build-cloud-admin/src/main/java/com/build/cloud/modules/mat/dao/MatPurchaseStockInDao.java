package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 入库表 Mapper 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface MatPurchaseStockInDao extends BaseMapper<MatPurchaseStockInEntity> {
	
	/**
	 * 分页查询入库信息
	 * @param page
	 * @param params
	 * @return
	 */
	List<MatPurchaseStockInEntity> queryPageList(Page<MatPurchaseStockInEntity> page, Map<String, Object> params);
	
	/**
	 * 根据id查询入库信息
	 * @param id
	 */
	MatPurchaseStockInEntity selectByKey(@Param(value = "id") String id);
	
	/**
	 * 查询当前未审核入库单下物料已入库情况
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectAlreadyList(Map<String, Object> params);
}
