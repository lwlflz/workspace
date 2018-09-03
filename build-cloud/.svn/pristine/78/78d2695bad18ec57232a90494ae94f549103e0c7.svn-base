package com.build.cloud.modules.sys.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysConfigEntity;

import java.util.Map;
/**
 * @ClassName: SysConfigService
 * @Description: 系统配置信息
 * @author: liutao
 * @date: 2018年3月16日 下午5:36:36
 */
public interface ISysConfigService extends IService<SysConfigEntity> {
	PageUtils queryPage(Map<String, Object> params);
	/**
	 * 保存配置信息
	 */
	public void save(SysConfigEntity config);
	/**
	 * 更新配置信息
	 */
	public void update(SysConfigEntity config);
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);
	/**
	 * 根据key，获取配置的value值
	 * @param key key
	 */
	public String getValue(String key);
	/**
	 * 根据key，获取value的Object对象
	 * @param key key
	 * @param clazz Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
}
