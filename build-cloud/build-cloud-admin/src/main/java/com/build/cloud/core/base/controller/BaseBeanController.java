package com.build.cloud.core.base.controller;
import java.io.Serializable;

import com.build.cloud.common.utils.ReflectionUtils;
public abstract class BaseBeanController<Entity extends Serializable> extends AbstractController {
	/**
	 * 实体类型
	 */
	protected final Class<Entity> entityClass;
	protected BaseBeanController() {
		this.entityClass = ReflectionUtils.getSuperGenericType(getClass());
	}
	protected Entity newModel() {
		try {
			return entityClass.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
		}
	}
}
