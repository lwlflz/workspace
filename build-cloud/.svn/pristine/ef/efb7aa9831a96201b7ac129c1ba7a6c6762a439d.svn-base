package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchaseContractEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 物资采购合同文本表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-07
 */
public interface IMatPurchaseContractService extends IService<MatPurchaseContractEntity> {
	
	/**
	 * 分页查询物资采购合同文本表
	 * @param params
	 * @return
	 */
	PageUtils queryPageList(Map<String, Object> params);
	
	/**
	 * 保存物资采购合同文本
	 * @param entity
	 * @return
	 */
	MatPurchaseContractEntity save(MatPurchaseContractEntity entity);
	
	/**
	 * 修改物资采购合同文本
	 * @param entity
	 */
	void update(MatPurchaseContractEntity entity);
	
	/**
	 * 查询物资采购合同文本信息
	 * @param id
	 * @return
	 */
	MatPurchaseContractEntity info(String id);
	
	/**
	 * 删除物资采购合同文本信息
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 已采购清单
	 * @return
	 */
	PageUtils happenedPuList(Map<String, Object> params);
	
	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo
	 */
	void submit(MatPurchaseContractEntity entity);
	
	/**
	 * 管理员审核通过操作
	 */
	void check(MatPurchaseContractEntity entity);
	
	/**
	 * 管理员审核驳回操作
	 */
	void reject(MatPurchaseContractEntity entity);
	
	/**
	 * 管理员审核不通过操作
	 */
	void endReturn(MatPurchaseContractEntity entity);
	
	/**
	 * 物资采购合同首页
	 * @param page
	 * @param params
	 * @return
	 */
	PageUtils selectIndexPage(Map<String, Object> params);
}
