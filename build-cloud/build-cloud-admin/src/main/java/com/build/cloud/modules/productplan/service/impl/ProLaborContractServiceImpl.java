package com.build.cloud.modules.productplan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.cloud.modules.productplan.dao.ProLaborContractDao;
import com.build.cloud.modules.productplan.form.ProLaborContractFrom;
import com.build.cloud.modules.productplan.service.IProLaborContractService;

/**
 * 
* Title: ProLaborContractServiceImpl
* Description:  劳务合同首页查询
* @author 鲁四围 
* @date 2018年5月22日
 */
@Service
public class ProLaborContractServiceImpl  implements IProLaborContractService {
	
	@Autowired
	private ProLaborContractDao proLaborContractDao;
	
	@Override
	public List<ProLaborContractFrom> getProLaborContractList(Map<String, Object> map) {
		return proLaborContractDao.getProLaborContractList(map);
	}

}
