package com.build.cloud.modules.bs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.constant.Constant.BsCodeType;
import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.dao.BsCodeDao;
import com.build.cloud.modules.bs.entity.BsCodeEntity;
import com.build.cloud.modules.bs.entity.BsCodeListEntity;
import com.build.cloud.modules.bs.service.IBsCodeService;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.productplan.service.IProContractService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangsen
 * @since 2018-05-16
 */
@Service
public class BsCodeServiceImpl extends ServiceImpl<BsCodeDao, BsCodeEntity> implements IBsCodeService {
	@Autowired
	private IProContractService proContractService;
	
	@Override
	public void saveBsCode(BsCodeListEntity entity) {
		String codeType = entity.getCodeType();
		baseMapper.physicsDelete(codeType);
		
		List<BsCodeEntity> list = entity.getCodeList();
		if(list!=null&&list.size()>0){
			this.insertBatch(list);
		}
	}

	@Override
	public String deleteJudge(BsCodeEntity entity) {
		String type = entity.getCodeType();
		BsCodeType enumType = BsCodeType.getByValue(type);
		if(enumType==null){
			return "类型无对应枚举";
		}
		switch (enumType) {
			case GS :
				break;
			case XM :
				break;
			case LD :
				break;
			case JGXS :
				break;
			case FB :
				break;
			case ZFB :
				break;
			case FX :
				break;
			case HYGH :
				Integer count = proContractService.selectCount(new EntityWrapper<ProContract>());
				if(count!=null&&count>0){
					return "已存在编码合同,不能删除";
				}
			default:
		}
		return null;
	}

	@Override
	public List<CodeBean> getTeamType(String type) {
		return baseMapper.getTeamType(type);
	}
	
}
