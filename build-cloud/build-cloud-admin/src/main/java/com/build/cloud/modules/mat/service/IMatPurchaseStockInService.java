package com.build.cloud.modules.mat.service;

import java.util.Map;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchaseContractEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 入库表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
public interface IMatPurchaseStockInService extends IService<MatPurchaseStockInEntity> {
	
	/**
	 * 分页查询入库信息
	 * @param params
	 * @return
	 */
	PageUtils queryPageList(Map<String, Object> params);
	
	/**
	 * 查询入库信息
	 */
	MatPurchaseStockInEntity info(String id);
	
	/**
	 * 保存入库信息
	 * @param entity
	 * @return
	 */
	MatPurchaseStockInEntity save(MatPurchaseStockInEntity entity);
	
	/**
	 * 修改入库信息
	 * @param entity
	 */
	void update(MatPurchaseStockInEntity entity);
	
	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo
	 */
	void submit(MatPurchaseStockInEntity entity);
	
	/**
	 * 管理员审核通过操作
	 */
	void check(MatPurchaseStockInEntity entity);
	
	/**
	 * 管理员审核驳回操作
	 */
	void reject(MatPurchaseStockInEntity entity);
	
	/**
	 * 管理员审核不通过操作
	 */
	void endReturn(MatPurchaseStockInEntity entity);
		
}
