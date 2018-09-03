package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;

/**
 * <p>Title: IMatPurchasePlanlistService</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午3:22:53
 */
public interface IMatPurchasePlanlistService extends IService<MatPurchasePlanlistEntity>{

	/**
	 * 查询计划清单列表
	 * @param params
	 * @return
	 */
	public List<MatPurchasePlanlistEntity> queryList(Map<String, Object> params);

	/**
	 * 根据计划id 删除清单
	 * @param planId
	 */
	public void delteByPlanId(String planId);

	/**
	 * 根据id批量查询详情 并根据计划月份排序
	 * @param idList
	 * @return
	 */
	public List<MatPurchasePlanlistEntity> selectByIds(List<String> idList);

	/**
	 * 根据汇总清单 更新计划清单
	 * @param list
	 */
	void updatePlanlist(List<MatPurchasePlanSumListEntity> list);

}
