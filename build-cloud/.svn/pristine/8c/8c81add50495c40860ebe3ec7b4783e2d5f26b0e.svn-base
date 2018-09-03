package com.build.cloud.modules.bs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.entity.BsCodeEntity;
import com.build.cloud.modules.bs.entity.BsCodeListEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangsen
 * @since 2018-05-16
 */
public interface IBsCodeService extends IService<BsCodeEntity> {
	void saveBsCode(BsCodeListEntity entity);
	
	String deleteJudge(BsCodeEntity entity);
	
	/**
	 * 获取班组类型
	 * @param type类型type通过type获取数据
	 * @return
	 */
	public List<CodeBean> getTeamType(String type);
}
