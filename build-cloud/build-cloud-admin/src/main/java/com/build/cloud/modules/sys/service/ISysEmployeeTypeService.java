package com.build.cloud.modules.sys.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysEmployeeTypeEntity;

/**
 * 
* Title: ISysEmployeeTypeService
* Description:  人员分类表
* @author 鲁四围 
* @date 2018年4月11日
 */
public interface ISysEmployeeTypeService extends IService<SysEmployeeTypeEntity>{
	PageUtils queryPage(Map<String, Object> params);
}
