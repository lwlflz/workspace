package com.build.cloud.modules.mat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.mat.entity.MatPurchasePlanlistEntity;

/**
 * <p>Title: MatPurchasePlanlistDao</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月9日 下午3:22:01
 */
public interface MatPurchasePlanlistDao extends BaseMapper<MatPurchasePlanlistEntity>{
	public void deleteByPlanId(String id);

	/**
	 * @param idList
	 * @return
	 */
	public List<MatPurchasePlanlistEntity> selectByIds(@Param("list") List<String> idList); 
}
