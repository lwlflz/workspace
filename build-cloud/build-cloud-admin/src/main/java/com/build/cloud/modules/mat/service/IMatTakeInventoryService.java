package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.bs.entity.BsTeamEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseStockInEntity;
import com.build.cloud.modules.mat.entity.MatTakeInventoryEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 盘存表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
public interface IMatTakeInventoryService extends IService<MatTakeInventoryEntity> {
	
	/**
	 * 分页查询盘存数据
	 * @param page
	 * @param params
	 * @return
	 */
	PageUtils pageList(Map<String, Object> params);
	
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
	 * 保存盘存信息
	 * @param entity
	 * @return
	 */
	MatTakeInventoryEntity save(MatTakeInventoryEntity entity);
	
	/**
	 * 保存盘存信息
	 * @param entity
	 * @return
	 */
	void update(MatTakeInventoryEntity entity);
	
	/**
	 * 查询盘存信息
	 * @param id
	 * @return
	 */
	MatTakeInventoryEntity info(String id);
	
	/**
	 * 查询盘存新增数据
	 * @param params
	 */
	Map<String, Object> takeAddInfo(Map<String, Object> params);
	
	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo
	 */
	void submit(MatTakeInventoryEntity entity);
	
	/**
	 * 管理员审核通过操作
	 */
	void check(MatTakeInventoryEntity entity);
	
	/**
	 * 管理员审核驳回操作
	 */
	void reject(MatTakeInventoryEntity entity);
	
	/**
	 * 管理员审核不通过操作
	 */
	void endReturn(MatTakeInventoryEntity entity);

	/**
	 * 分页查询劳务班组
	 * @param params
	 * @return
	 */
	PageUtils getTeamList(Map<String, Object> params);

	/**
	 * @param key
	 */
	void delete(String key);
}
