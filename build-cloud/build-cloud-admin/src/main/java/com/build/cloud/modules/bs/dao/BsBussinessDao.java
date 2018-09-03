package com.build.cloud.modules.bs.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.bs.entity.BsBussinessEntity;

/**
 * 
* Title: BsBussinessDao
* Description: 客商档案
* @author 鲁四围 
* @date 2018年4月11日
 */
public interface BsBussinessDao extends BaseMapper<BsBussinessEntity>{
	
	/**
	 * @param page
	 * @param params
	 * @return
	 */
	public List<BsBussinessEntity> selectPageByBusId(Page<BsBussinessEntity> page, Map<String, Object> params);

	public List<BsBussinessEntity> partbJoin(Page<BsBussinessEntity> page, Map<String, Object> params);
	
	
	void saveComIdByBusId(Map<String,Object> params);
}
