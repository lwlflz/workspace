package com.build.cloud.modules.sys.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysMsgTemplateEntity;

/**
 * 
* Title: 短信模板接口
* Description:  
* @author 鲁四围 
* @date 2018年3月29日
 */
public interface ISysMsgTemplateService  extends IService<SysMsgTemplateEntity> {
	PageUtils queryPage(Map<String, Object> params);
}
