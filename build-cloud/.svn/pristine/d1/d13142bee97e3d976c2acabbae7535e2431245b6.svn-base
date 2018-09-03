package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderEntity;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;

/**
 * <p>Title: IMatPurchaseOrderService</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月7日 上午10:53:23
 */
@Service
public interface IMatPurchaseOrderService extends IService<MatPurchaseOrderEntity>{
	public PageUtils queryPage(Map<String,Object> params);

	/**
	 * @param entitys
	 */
	public void insertOrder(MatPurchaseOrderEntity entitys);

	/**
	 * @param entitys
	 */
	public void updateOrder(MatPurchaseOrderEntity entitys);
	/**
	 * 
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
	public PageUtils selectPage(Map<String, Object> params);

	/**
	 * 审批提交
	 * @param entity
	 */
	public void submit(MatPurchaseOrderEntity entity);

	/**
	 * 审批通过
	 * @param entity
	 */
	public void check(MatPurchaseOrderEntity entity);

	/**
	 * 审批驳回
	 * @param entity
	 */
	public void reject(MatPurchaseOrderEntity entity);

	/**
	 * 审批弃申
	 * @param entity
	 */
	public void endReturn(MatPurchaseOrderEntity entity);
	
	/**
	 * 订单参照
	 * @param params
	 * @return
	 */
	public PageUtils getOrderList(Map<String, Object> params);

	/**
	 * flag 标记新增  或  更新
	 * 更新或保存时  更新计划清单数据
	 * @param list 
	 */
	public void submitUpdatePlanList(List<MatPurchaseOrderlistEntity> list);

	/**
	 * 更新或保存时  更新计划清单数据
	 * @param list
	 * @param i 
	 */
	public void submitUpdatePlanSumList(List<MatPurchaseOrderlistEntity> list);

	/**
	 * 根据订单id 和类型  恢复已锁定的数据
	 * @param id
	 * @param orderType
	 */
	void rejectList(String id, String orderType);

}
