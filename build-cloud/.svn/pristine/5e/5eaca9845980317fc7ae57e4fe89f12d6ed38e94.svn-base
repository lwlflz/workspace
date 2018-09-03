/**
 * 
 */
package com.build.cloud.modules.bs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsMtrArchiveDao;
import com.build.cloud.modules.bs.entity.BsMtrArchiveEntity;
import com.build.cloud.modules.bs.service.IBsMtrArchiveService;

/**
 * @className BsMtrArchiveService
 * @descripion 材料档案服务层实现
 * @author WangJun
 * @date 2018年4月12日上午9:07:13
 */
@Service("bsMtrArchiveService")
public class BsMtrArchiveServiceImpl extends ServiceImpl<BsMtrArchiveDao, BsMtrArchiveEntity> implements IBsMtrArchiveService{
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<BsMtrArchiveEntity> query = new Query<BsMtrArchiveEntity>(params);
		Page<BsMtrArchiveEntity> page = query.getPage();
		List<BsMtrArchiveEntity> kindList = baseMapper.selectPageByKindId(page,params);
		page.setRecords(kindList);
		return new PageUtils(page);
	}
	@Override
	public BsMtrArchiveEntity selectById(String id) {
		BsMtrArchiveEntity bsMtrArchiveEntity = null;
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<BsMtrArchiveEntity> list = baseMapper.selectPageByKindId(map);
		bsMtrArchiveEntity = list.get(0);
		return bsMtrArchiveEntity;
	}
}
