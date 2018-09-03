package com.build.cloud.modules.productplan.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.productplan.dto.ProRota;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liangsen
 * @since 2018-05-03
 */
public interface ProRotaMapper extends BaseMapper<ProRota> {
	List<ProRota> selectRotaPage(Page<ProRota> page,ProRota rota);
	
	void deleteByIdPhysic(String id);
}