package com.build.cloud.modules.productplan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.core.constant.DictConstant;
import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.service.IBsCodeService;
import com.build.cloud.modules.productplan.dao.ProRotaWorkerMapper;
import com.build.cloud.modules.productplan.dto.ProRotaWorker;
import com.build.cloud.modules.productplan.service.IProRotaWorkerService;
import com.build.cloud.modules.sys.util.DictUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangsen
 * @since 2018-05-03
 */
@Service
public class ProRotaWorkerServiceImpl extends ServiceImpl<ProRotaWorkerMapper, ProRotaWorker> implements IProRotaWorkerService {
	
	@Autowired
	protected IBsCodeService bsCodeService;
	public List<ProRotaWorker> selectWorkersInfo(Map<String,Object> params){
		List<ProRotaWorker> workerList = baseMapper.selectWorkersInfo(params);
		List<CodeBean> code = bsCodeService.getTeamType(DictConstant.WORK_KIND);
		for (ProRotaWorker proRotaWorker : workerList) {
			for (CodeBean codeBean : code) {
				if (codeBean.getCode().equals(proRotaWorker.getWorkType())) {
					proRotaWorker.setWorkTypename(codeBean.getValue());
				}
			}
//			proRotaWorker.setWorkTypename(DictUtils.getDictLabelByCode(proRotaWorker.getWorkType(), "work_type", ""));
			proRotaWorker.setSkillLevelname(DictUtils.getDictLabelByCode(proRotaWorker.getSkillLevel(), "skill_level", ""));
		}
		
		return workerList;
	}
}
