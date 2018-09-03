package com.build.cloud.modules.ls.service;

import java.util.List;
import java.util.Map;

import com.build.cloud.modules.ls.entity.LsContractLaborEntity;
import com.build.cloud.modules.ls.entity.LsContractPlandetailEntity;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 劳务分包合同拆分详情 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-08-27
 */
public interface ILsContractPlandetailService extends IService<LsContractPlandetailEntity> {
	
	/**
	 * 生产计划参照
	 */
	public List<ProPlanDetail> queryContractPlandetail(Map<String, Object> params); 
	
	/**
	 * 保存劳务分包合同拆分
	 * @param list
	 */
	public void saveConPlandetail(LsContractLaborEntity entity);
	
	/**
	 * 查询合同拆分详情
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryConPlanInfo(Map<String, Object> params);
}
