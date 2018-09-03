package com.build.cloud.common.utils;
import java.util.HashMap;
/**
 * 
 * @ClassName: MapUtils   
 * @Description: Map工具类
 * @author: liutao
 * @date: 2018年3月16日 上午11:57:11
 */
public class MapUtils extends HashMap<String, Object> {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;

	@Override
	public MapUtils put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
