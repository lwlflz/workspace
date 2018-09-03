package com.build.cloud.modules.mat.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;

/**
 * <p>Title: MatPurchaseOrderDao</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:48:34
 */
public interface MatPurchaseOrderDao extends BaseMapper<MatPurchaseOrderEntity>{
	/**
	 * 查询单个信息
	 * @param id
	 * @return
	 */
	public MatPurchaseOrderEntity selectInfoById(String id);
	/**
	 * 分页查询
	 * @param page
	 * @param params
	 * @return
	 */
	public List<MatPurchaseOrderEntity> selectPage(Page<MatPurchaseOrderEntity> page, Map<String, Object> params);
	
	/**
	 * 参照
	 * @param params
	 */
	public List<MatPurchaseOrderEntity> getOrderList(Page<MatPurchaseOrderEntity> page, Map<String, Object> params);
}
