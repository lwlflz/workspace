package com.build.cloud.modules.ls.service.impl;

import java.util.List;
import java.util.Map;


import com.build.cloud.modules.ls.entity.LsContractLaborEntity;
import com.build.cloud.modules.ls.entity.LsContractPlandetailEntity;
import com.build.cloud.modules.ls.dao.LsContractPlandetailDao;
import com.build.cloud.modules.ls.service.ILsContractPlandetailService;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProPlanInfo;
import com.build.cloud.modules.productplan.service.IProPlanDetailService;
import com.build.cloud.modules.productplan.service.IProPlanInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 劳务分包合同拆分详情 服务实现类
 * </p>
 *
 * @author gongjy
 * @since 2018-08-27
 */
@Service
public class LsContractPlandetailServiceImpl extends ServiceImpl<LsContractPlandetailDao, LsContractPlandetailEntity> implements ILsContractPlandetailService {
	
	@Autowired
	private IProPlanInfoService proPlanInfoService;
	
	@Autowired
	private IProPlanDetailService proPlanDetailService;
	
	@Override
	public List<ProPlanDetail> queryContractPlandetail(Map<String, Object> params) {
		List<ProPlanDetail> details = baseMapper.selectLayeredPlan(params);
		return details;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveConPlandetail(LsContractLaborEntity entity) {
		
		for(LsContractPlandetailEntity  plandetailEntity : entity.getPlanDetailList()) {
			plandetailEntity.setProjectId(entity.getProjectId());
			plandetailEntity.setConId(entity.getConId());
		}
		baseMapper.deleteByConId(entity.getConId());
		this.insertBatch(entity.getPlanDetailList());
		
		// 查询生产计划主表
		ProPlanInfo proPlanInfo = new ProPlanInfo();
		proPlanInfo.setEffStatus("1");// 已生效
		proPlanInfo.setProjectId(entity.getProjectId());
		EntityWrapper<ProPlanInfo> proPlanEw = new EntityWrapper<ProPlanInfo>();
		proPlanEw.setEntity(proPlanInfo);
		ProPlanInfo proPlanInfoRes = proPlanInfoService.selectOne(proPlanEw);
		// 根据主表id更新详情表
		for (LsContractPlandetailEntity pEntity : entity.getPlanDetailList()) {
			EntityWrapper<ProPlanDetail> planDtailEw = new EntityWrapper<ProPlanDetail>();
			planDtailEw.eq("pro_id", proPlanInfoRes.getId());
			planDtailEw.eq("unique_id", pEntity.getProplanUnique());
			planDtailEw.eq("property_type", "layered");
			ProPlanDetail upDetail = new ProPlanDetail();
			upDetail.setConsplitId(pEntity.getConId());
			proPlanDetailService.update(upDetail, planDtailEw);
		}
		
	}

	@Override
	public List<Map<String, Object>> queryConPlanInfo(Map<String, Object> params) {
		List<Map<String, Object>> cList = baseMapper.selectLsConPlan(params);
//		if (CollectionUtil.isEmpty(cList)) {
//			return null;
//		}
//		Set<String> set = new HashSet<String>();
//		for (Map<String, Object> map : cList) {
//			if (map.get("parentIds") != null && StringUtil.isNotBlank(map.get("parentIds").toString())) {
//				List<String> list = Splitter.on(",").trimResults().splitToList(map.get("parentIds").toString());
//				set.addAll(list);
//			}
//		}
//		List<String> idList = new ArrayList<String>();
//		idList.addAll(set);
//		Map<String, Object> maps = new HashMap<String, Object>();  
//		maps.put("list", idList);
//		maps.put("projectId", params.get("projectId"));
//		List<Map<String, Object>> pList = baseMapper.selectLsConPlanParent(maps);
//		pList.addAll(cList);
		return cList;
		
	}
	
}
