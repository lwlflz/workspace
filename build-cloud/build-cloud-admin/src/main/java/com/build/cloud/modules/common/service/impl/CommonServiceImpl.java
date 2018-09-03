package com.build.cloud.modules.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.common.dao.CommonDao;
import com.build.cloud.modules.common.service.ICommonService;
import com.google.common.collect.Maps;

/**
 * <p>Title: CommonServiceImpl</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月12日 下午2:49:19
 */
@Service
public class CommonServiceImpl implements ICommonService{

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private IBsProjectService bsProjectService;
	
	@Override
	public String selectMaxCode(Map<String, Object> params) {
		return commonDao.selectMaxCode(params);
	}

	@Override
	public String selectMaxCodeByLsContractLobar() {
		return commonDao.selectMaxCodeByLsContractLobar();
	}

	@Override
	public Map<String, String> getCompanyProType(String companyId, String projectId) {
		Map<String, String> map = Maps.newHashMap();
		BsProjectEntity entity = bsProjectService.selectById(projectId);
		//建设方
		String constructionId = entity.getConstructionId();
		//总包方
		String beneralcontractorId = entity.getGeneralcontractorId();
		//分包方
		String laborId = entity.getLaborId();
		//监理方
		String supervisorId = entity.getSupervisorId();
		//设计方
		String designerId = entity.getDesignerId();
		//公共项目id
		String proId = entity.getProId();
		
		map.put(Constant.CompanyProRole.CONSTRUCTION.getName(), StringUtil.isNotBlank(constructionId) ? constructionId : "");
		map.put(Constant.CompanyProRole.BENERALCONTRACTOR.getName(), StringUtil.isNotBlank(beneralcontractorId) ? beneralcontractorId : "");
		map.put(Constant.CompanyProRole.LABOR.getName(), StringUtil.isNotBlank(laborId) ? laborId : "");
		map.put(Constant.CompanyProRole.SUPERVISOR.getName(), StringUtil.isNotBlank(supervisorId) ? supervisorId : "");
		map.put(Constant.CompanyProRole.DESIGNER.getName(), StringUtil.isNotBlank(designerId) ? designerId : "");
		map.put(Constant.PRO_ID, StringUtil.isNotBlank(proId) ? proId : "");
		
		if (StringUtil.isNotBlank(constructionId) && StringUtil.equals(companyId, constructionId)) {
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.CONSTRUCTION.getType());
		}else if (StringUtil.isNotBlank(beneralcontractorId) && StringUtil.equals(companyId, beneralcontractorId)){
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.BENERALCONTRACTOR.getType());
		}else if (StringUtil.isNotBlank(laborId) && StringUtil.equals(companyId, laborId)){
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.LABOR.getType());
		}else if (StringUtil.isNotBlank(supervisorId) && StringUtil.equals(companyId, supervisorId)){
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.SUPERVISOR.getType());
		}else if (StringUtil.isNotBlank(designerId) && StringUtil.equals(companyId, designerId)){
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.DESIGNER.getType());
		} else {
			map.put(Constant.COMPANY_ROLE_TYPE, Constant.CompanyProRole.OTHER.getType());
		}
		return map;
	}

}
