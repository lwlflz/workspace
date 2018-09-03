package com.build.cloud.modules.productplan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.productplan.dto.ProMessageChildEntity;
import com.build.cloud.modules.productplan.dto.ProMessageEntity;

/**
 * <p>
 * 消息主表 服务类
 * </p>
 *
 * @author gongjy
 * @since 2018-05-28
 */
public interface IProMessageService extends IService<ProMessageEntity> {
	
	/**
	 * 保存消息
	 * @param proMessageEntity
	 * @param childEntities
	 */
	public void insert(ProMessageEntity proMessageEntity, List<ProMessageChildEntity> childEntities);
	
	/**
	 * 获取我发出的消息
	 * @return
	 */
	public PageUtils getMsgList(Map<String, Object> param);
	
}
