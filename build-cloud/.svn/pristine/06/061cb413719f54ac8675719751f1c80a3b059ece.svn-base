package com.build.cloud.modules.ls.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.ls.entity.LsContractLaborEntity;

public interface ILsContractLaborService extends IService<LsContractLaborEntity>{

	
	/**
	 * 查询劳务分包合同列表 --分页
	 * @param map
	 * @return
	 */
	public PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询劳务分包合同列表 --分页(不区分收入支出)
	 * @param map
	 * @return
	 */
	public PageUtils queryPageAll(Map<String, Object> params);
	
	
	/**
	 * 查询劳务分包合同--详细信息
	 * @param id
	 * @return
	 */
	public LsContractLaborEntity getContractLaborById(String id);
	
	public void updateContractLabor(LsContractLaborEntity entity);
	
	/**
	 * @param entity
	 */
	public void submit(LsContractLaborEntity entity);

	/**
	 * @param entity
	 */
	public void check(LsContractLaborEntity entity);

	/**
	 * @param entity
	 */
	public void reject(LsContractLaborEntity entity);

	/**
	 * @param entity
	 */
	public void endReturn(LsContractLaborEntity entity);
	
	public List<Map<String, Object>> getConLaborList(Map<String, Object> params);
}
