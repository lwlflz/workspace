package com.build.cloud.common.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * @ClassName: ReflectionUtils
 * @Description: 反射工具类
 * @author: liutao
 * @date: 2018年4月20日 上午10:47:22
 */
public class ReflectionUtils {
	
	private static final Logger logger = Logger.getLogger(ReflectionUtils.class);
	/**
	 * 获取实体类内 & 父类内的所有字段
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> result = new LinkedList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			result.add(field);
		}
		Class<?> superClass = clazz.getSuperclass();
		if (superClass.equals(Object.class)) {
			return result;
		}
		result.addAll(getAllFields(superClass));
		return result;
	}
	/**
	 * 获取实体类内的所有字段并自动排除存在@Transient注解的字段
	 * @param clazz
	 * @return
	 */
	public static List<Field> getAllFieldsExcludeTransient(Class<?> clazz) {
		List<Field> result = new LinkedList<Field>();
		List<Field> list = getAllFields(clazz);
		for (Field field : list) {
			if (Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			result.add(field);
		}
		return result;
	}
	/**
	 * 获取字段 检索本类内是否存在，检索不到再去找父类内的字段
	 * @param clazz
	 * @return
	 */
	public static Field getField(Class<?> clazz, String fieldName)
		throws Exception {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			try {
				field = clazz.getSuperclass().getDeclaredField(fieldName);
			} catch (NoSuchFieldException e1) {
				try {
					field = clazz.getSuperclass().getSuperclass().getDeclaredField(fieldName);
				} catch (NoSuchFieldException e2) {
				}
			}
		}
		if (field == null) {
			throw new Exception("Not Found Field：" + fieldName + " in Class " + clazz.getName() + " and super Class.");
		}
		return field;
	}
	
	/**
	 * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型 如: public EmployeeDao extends
	 * BaseDao<Employee, String>
	 * 
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}

		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class<?>) params[index];
	}

	/**
	 * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型 如:
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperGenericType(Class<?> clazz) {
		return (Class<T>) getSuperClassGenricType(clazz, 0);
	}
}
