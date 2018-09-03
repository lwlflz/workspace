package com.build.cloud.modules.productplan.service.impl;

import java.util.List;
import java.util.Map;

import com.build.cloud.modules.productplan.dto.ProContractPlandetail;
import com.build.cloud.modules.productplan.dao.ProContractPlandetailMapper;
import com.build.cloud.modules.productplan.service.IProContractPlandetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-26
 */
@Service
public class ProContractPlandetailServiceImpl extends ServiceImpl<ProContractPlandetailMapper, ProContractPlandetail> implements IProContractPlandetailService {

	@Override
	public void physicsDelete(String conId) {
		baseMapper.physicsDelete(conId);
		
	}
	
	@Override
	public List<ProContractPlandetail> selectConDetail(ProContractPlandetail pcp){
		return baseMapper.selectConDetail(pcp);
	}

	@Override
	public Map<String, Integer> selectLaborFinishedCount(Map<String, String> map) {
		return baseMapper.selectLaborFinishedCount(map);
	}
	
}
