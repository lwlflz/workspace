package com.build.cloud.modules.sys.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.sys.entity.SysConfigEntity;

import org.apache.ibatis.annotations.Param;
/**
 * @ClassName: SysConfigDao
 * @Description: 系统配置信息
 * @author: liutao
 * @date: 2018年3月16日 下午2:50:04
 */
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {
	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
