package com.build.cloud.modules.productplan.service.impl;

import com.build.cloud.modules.productplan.dao.ProPlanDetailMapper;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.service.IProPlanDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
@Service
public class ProPlanDetailServiceImpl extends ServiceImpl<ProPlanDetailMapper, ProPlanDetail> implements IProPlanDetailService {

	@Autowired
	private ProPlanDetailMapper proPlanDetailMapper;

	@Override
	public void physicsDelete(String proId) {
		baseMapper.physicsDelete(proId);

	}

	@Override
	public List<ProPlanDetail> queryPlanDetailList(String projectId) {
		return proPlanDetailMapper.queryPlanDetailList(projectId);
	}

	@Override
	public List<ProPlanDetail> selectAuthPlanDetailList(Map<String, String> map) {
		return proPlanDetailMapper.selectAuthPlanDetailList(map);
	}

	@Override
	public List<ProPlanDetail> selectLaborPlanDetailList(Map<String, String> map) {
		return proPlanDetailMapper.selectLaborPlanDetailList(map);
	}

}
