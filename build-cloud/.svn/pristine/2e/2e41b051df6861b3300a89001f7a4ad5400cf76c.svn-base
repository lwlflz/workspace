package com.build.cloud.common.utils.cache.serializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.sunsine.common.util.FstSerialize;
/**
 * @ClassName: RedisObjectSerializer
 * @Description: redis序列化对象
 * @author: liutao
 * @date: 2018年3月23日 上午10:17:30
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
	static final byte[] EMPTY_ARRAY = new byte[0];
	public Object deserialize(byte[] bytes) {
		if (isEmpty(bytes)) {
			return null;
		}
		try {
			return FstSerialize.deserialize(bytes);
		} catch (Exception ex) {
			throw new SerializationException("Cannot deserialize", ex);
		}
	}
	public byte[] serialize(Object object) {
		if (object == null) {
			return EMPTY_ARRAY;
		}
		try {
			return FstSerialize.serialize(object);
		} catch (Exception ex) {
			return EMPTY_ARRAY;
		}
	}
	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}