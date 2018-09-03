package com.build.cloud.modules.bs.dao;

import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.entity.BsCodeEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-05-16
 */
public interface BsCodeDao extends BaseMapper<BsCodeEntity> {
	public void physicsDelete(String codeType);
	
	/**
	 * 获取班组类型
	 * @param type类型type通过type获取数据
	 * @return
	 */
	public List<CodeBean> getTeamType(String type);
	
}