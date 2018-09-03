package com.build.cloud.modules.bs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.build.cloud.modules.bs.bean.BsWorkerBean;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;

/**
 * 
 * @ClassName: BsWorkerDao   
 * @Description: 劳务用工
 * @author: liutao
 * @date: 2018年4月13日 下午3:26:02
 */
@Mapper
public interface BsWorkerDao extends BaseMapper<BsWorkerEntity> {
	
	public List<Map<String, Object>> selectPageByMap(Pagination page, Map<String, Object> params);
	
	public int selectByCount(Map<String, Object> params);
	
	public List<Map<String, Object>> selectWorker(Map<String, Object> params);
	
	public List<BsWorkerBean> getInfoByIdCard(Map<String, Object> params);
	
}
