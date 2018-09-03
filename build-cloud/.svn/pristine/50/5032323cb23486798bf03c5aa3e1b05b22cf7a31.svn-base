package com.build.cloud.modules.productplan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.productplan.dao.ProMessageDao;
import com.build.cloud.modules.productplan.dto.ProMessageChildEntity;
import com.build.cloud.modules.productplan.dto.ProMessageEntity;
import com.build.cloud.modules.productplan.service.IProMessageChildService;
import com.build.cloud.modules.productplan.service.IProMessageService;

/**
 * <p>
 * 消息主表 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-05-28
 */
@Service
public class ProMessageServiceImpl extends ServiceImpl<ProMessageDao, ProMessageEntity> implements IProMessageService {
	
	@Autowired
	private IProMessageChildService proMessageChildService;
	
	@Autowired
	private ProMessageDao proMessageDao;
	
	@Override
	@Transactional
	public void insert(ProMessageEntity proMessageEntity,
			List<ProMessageChildEntity> childEntities) {
		this.insert(proMessageEntity);
		for(ProMessageChildEntity entity : childEntities) {
			entity.setMsgId(proMessageEntity.getId());
		}
		proMessageChildService.insertBatch(childEntities);
	}

	@Override
	public PageUtils getMsgList(Map<String, Object> param) {
//		Query<Map<String, Object>> query = new Query<Map<String, Object>>(param);
//		com.baomidou.mybatisplus.plugins.Page<Map<String, Object>> page = query.getPage();
//		com.github.pagehelper.Page<Map<String, Object>> pages = PageHelper.startPage(page.getCurrent(), page.getSize());
//		proMessageDao.selectMsg(query);
//		page.setRecords(pages.getResult());
//		page.setTotal(pages.getTotal());
//		return new PageUtils(page);
		return null;
	}


}
