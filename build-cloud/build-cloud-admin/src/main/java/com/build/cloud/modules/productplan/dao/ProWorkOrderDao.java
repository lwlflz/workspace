package com.build.cloud.modules.productplan.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.productplan.dto.ProWorkOrderEntity;
import com.build.cloud.modules.productplan.form.ProWorkOrderForm;

/**
* 
* Title: ProWorkOrderDao
* Description:  任务单
* @author 鲁四围 
* @date 2018年4月24日
**/
public interface ProWorkOrderDao  extends BaseMapper<ProWorkOrderEntity>{
	
	public List<ProWorkOrderForm> selectPageByFrom(Page<?> page, Map<String, Object> params);

}
