package com.build.cloud.modules.productplan.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.productplan.dto.ProWorkOrderEntity;
import com.build.cloud.modules.productplan.form.TaskListForm;

/**
 * 
* Title: IProWorkOrderService
* Description:  任务单
* @author 鲁四围 
* @date 2018年4月24日
 */
public interface IProWorkOrderService  extends IService<ProWorkOrderEntity>{
	
	/**
	 * @param params
	 * @return
	 */
	PageUtils queryPage(Map<String, Object> params);
	
	void workSave(TaskListForm form) throws Exception;
	
	void cancelWork(String id) throws BusinessException;
}
