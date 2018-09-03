package com.build.cloud.modules.punch.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.punch.dao.DevInfoDao;
import com.build.cloud.modules.punch.entity.DevFaceCommandEntity;
import com.build.cloud.modules.punch.entity.DevInfoEntity;
import com.build.cloud.modules.punch.service.IDevFaceCommandService;
import com.build.cloud.modules.punch.service.IDevInfoService;
import com.google.common.collect.Maps;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: DevInfoServiceImpl</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年8月4日 下午4:02:02
 */
@Service
public class DevInfoServiceImpl extends ServiceImpl<DevInfoDao, DevInfoEntity> implements IDevInfoService{
	
	@Autowired
	private IDevFaceCommandService devFaceCommandService;

	@Override
	public DevInfoEntity selectinfoById(String id) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("id", id);
		List<Map<String, Object>> queryName = baseMapper.queryName(map);
		DevInfoEntity entity = baseMapper.selectById(id);
		entity.setList(queryName);
		return entity;
	}

	@Override
	public PageUtils selectPageByCompanyId(Map<String, Object> params) {
		String companyId = MapUtil.getStr(params, "companyId");
		EntityWrapper<DevInfoEntity> wrapper = new EntityWrapper<DevInfoEntity>();
		wrapper.eq(StrUtil.isNotBlank(companyId), "company_id", companyId);
		Page<DevInfoEntity> page = 
				this.selectPage(new Query<DevInfoEntity>(params).getPage(),wrapper);
		return new PageUtils(page);
	}

	@Override
	@Transactional
	public void save(DevInfoEntity entity) {
		baseMapper.insert(entity);
		DevFaceCommandEntity dateTime = new DevFaceCommandEntity();
		dateTime.setSzSerial(entity.getSzSerial());
		dateTime.setCommand("SetDateTime(date=\"{0}\" time=\"{1}\")");
		devFaceCommandService.insert(dateTime);
	}

}
