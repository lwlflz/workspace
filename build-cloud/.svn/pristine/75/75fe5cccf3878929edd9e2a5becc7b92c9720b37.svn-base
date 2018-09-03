package com.build.cloud.modules.punch.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.punch.entity.DevInfoEntity;

/**
 * <p>Title: IDevInfoService</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年8月4日 下午4:01:14
 */
public interface IDevInfoService extends IService<DevInfoEntity>{

	/**
	 * @param id
	 * @return
	 */
	DevInfoEntity selectinfoById(String id);

	/**
	 * @param params
	 * @return
	 */
	PageUtils selectPageByCompanyId(Map<String, Object> params);
	
	/**
	 * 保存设备基本信息
	 * @param entity
	 */
	void save(DevInfoEntity entity); 

	
}
