package com.build.cloud.modules.productplan.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.productplan.dto.ProRota;
import com.build.cloud.modules.productplan.dto.ProRotaWorker;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangsen
 * @since 2018-05-03
 */
public interface IProRotaService extends IService<ProRota> {
	String insertProRota(ProRota info);
	
	void insertWorkers(ProRotaWorker param);
	
	PageUtils queryPage(Map<String, Object> params);
	/**
	 * 进场
	 * @param worker
	 */
	void inField(ProRotaWorker worker);
	/**
	 * 退场
	 * @param worker
	 */
	void outField(ProRotaWorker worker);
	/**
	 * 单个删除
	 * @param worker
	 */
	void deleteAsId(ProRotaWorker worker);
	/**
	 * 删除项目花名册
	 * @param id
	 */
	void deleteRota(String id);
}
