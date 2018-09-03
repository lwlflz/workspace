package com.build.cloud.modules.mat.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;

public interface IMatPurchasePlanSumlistService extends IService<MatPurchasePlanSumListEntity>{

	public PageUtils queryPage(Map<String, Object> params);

	/**
	 * 根据id批量查询 并根据汇总计划月份排序
	 * @param idList
	 * @return
	 */
	public List<MatPurchasePlanSumListEntity> selectByIds(List<String> idList);
}
