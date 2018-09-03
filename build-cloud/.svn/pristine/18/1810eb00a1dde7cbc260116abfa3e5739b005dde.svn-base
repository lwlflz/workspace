/**
 * 
 */
package com.build.cloud.modules.sys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysGroupDao;
import com.build.cloud.modules.sys.entity.SysGroupEntity;
import com.build.cloud.modules.sys.service.ISysGroupService;

/**
 * @className 
 * @descripion 
 * @author WangJun
 * @date 2018年4月27日下午4:45:48
 */
@Service("sysGroupService")
public class SysGroupServiceImpl extends ServiceImpl<SysGroupDao, SysGroupEntity> implements ISysGroupService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		EntityWrapper<SysGroupEntity> wrapper = new EntityWrapper<SysGroupEntity>();
		// TODO Auto-generated method stub
		Page<SysGroupEntity> page = 
				this.selectPage(new Query<SysGroupEntity>(params).getPage(),wrapper );
		return new PageUtils(page);
	}


}
