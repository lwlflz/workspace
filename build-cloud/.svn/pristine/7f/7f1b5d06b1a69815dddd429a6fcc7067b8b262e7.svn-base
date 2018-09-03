package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.build.cloud.modules.mat.entity.MatPurchaseContractEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 物资采购合同文本表 Dao 接口
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
public interface MatPurchaseContractDao extends BaseMapper<MatPurchaseContractEntity> {
	
	/**
	 * 分页查询物资采购合同文本表
	 * @param page
	 * @param params
	 * @return
	 */
	public List<MatPurchaseContractEntity> selectPage(Page<MatPurchaseContractEntity> page, Map<String, Object> params);
	
	/**
	 * 已采购清单
	 * @param page
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> happenedPuList(Page<Map<String, Object>> page, Map<String, Object> params);
	
	/**
	 * 根据id查询物资采购合同文本
	 * @param id
	 * @return
	 */
	MatPurchaseContractEntity selectByKey(@Param(value = "id") String id);
	
	/**
	 * 物资采购合同首页
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectIndexPage(Page<Map<String, Object>> page, Map<String, Object> params);
}
