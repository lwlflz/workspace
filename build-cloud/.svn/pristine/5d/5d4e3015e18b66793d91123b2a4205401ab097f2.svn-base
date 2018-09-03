package com.build.cloud.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysCompanyEntity;

/**
 * 
 * @ClassName: CompanyDao   
 * @Description: 公司表
 * @author: liutao
 * @date: 2018年3月30日 下午3:26:28
 */
public interface SysCompanyDao extends BaseMapper<SysCompanyEntity> {
	
	public List<Map<String, Object>> selectByUserId(String userId);
	
	public List<SysCompanyEntity> getCompanyChild(Map<String, Object> params);
}
