package com.build.cloud.modules.common.dao;

import java.util.Map;

/**
 * <p>Title: CommonDao</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月12日 下午2:52:45
 */
public interface CommonDao {

	public String selectMaxCode(Map<String,Object> params);

	public String selectMaxCodeByLsContractLobar();
}
