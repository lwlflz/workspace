package com.build.cloud.common.utils;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.sunsine.common.util.encrypt.MD5Util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
/**
 * <p>
 * StringUtil.java
 * </p>
 * <p>
 * <Method Simple Comment>
 * </p>
 * <Detail Description>
 * @author 刘滔
 * @since 2017年6月2日 下午8:00:00
 */
public final class StringUtil extends StringUtils {
	public static String arrayToCommaDelimitedString(Object... params) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			sBuilder.append(params[i]).append(",");
		}
		sBuilder.deleteCharAt(sBuilder.length() - 1);
		return sBuilder.toString();
	}
	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 例如：format("aaa {} ccc", "bbb") ----> aaa bbb ccc
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param values 参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... values) {
		if (ArrayUtil.isEmpty(values) || isBlank(template)) {
			return template;
		}
		final StringBuilder sb = new StringBuilder();
		final int length = template.length();
		int valueIndex = 0;
		char currentChar;
		for (int i = 0; i < length; i++) {
			if (valueIndex >= values.length) {
				sb.append(StrUtil.sub(template, i, length));
				break;
			}
			currentChar = template.charAt(i);
			if (currentChar == '{') {
				final char nextChar = template.charAt(++i);
				if (nextChar == '}') {
					sb.append(values[valueIndex++]);
				} else {
					sb.append('{').append(nextChar);
				}
			} else {
				sb.append(currentChar);
			}
		}
		return sb.toString();
	}
	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * @param type 要转化的类型
	 * @param map 包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException 如果分析类属性失败
	 * @throws IllegalAccessException 如果实例化 JavaBean 失败
	 * @throws InstantiationException 如果实例化 JavaBean 失败
	 * @throws InvocationTargetException 如果调用属性的 setter 方法失败
	 */
	public static Object convertMap(Class<?> type, Map<?, ?> map)
		throws IntrospectionException,
		IllegalAccessException,
		InstantiationException,
		InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象
		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}
	/**
	 * 身份证号替换，保留前四位和后四位 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * @param idCard 身份证号
	 * @return
	 */
	public static String idCardReplaceWithStar(String idCard) {
		if (StrUtil.isNotBlank(idCard)) {
			return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
		} else {
			return StringUtil.EMPTY;
		}
	}
	/**
	 * 银行卡替换，保留后四位 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * @param bankCard 银行卡号
	 * @return
	 */
	public static String bankCardReplaceWithStar(String bankCard) {
		if (StrUtil.isNotBlank(bankCard)) {
			return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
		} else {
			return StringUtil.EMPTY;
		}
	}
	/**
	 * 手机号码替换，中间四位替换成* 如果手机号码为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * @param phone 手机号码
	 * @return
	 */
	public static String phoneReplaceWithStar(String phone) {
		if (StrUtil.isNotBlank(phone)) {
			return replaceAction(phone, "(?<=\\d{3})\\d(?=\\d{4})");
		} else {
			return StringUtil.EMPTY;
		}
	}
	/**
	 * 实际替换动作
	 * @param str
	 * @param regular 正则
	 * @return
	 */
	private static String replaceAction(String str, String regular) {
		return str.replaceAll(regular, "*");
	}
	public static final char UNDERLINE = '_';
	/**
	 * @Title: camelToUnderline @Description: 驼峰命名转下划线命名 @param @param str @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	public static String camelToUnderline(String str) {
		if (StrUtil.isBlank(str)) {
			return "";
		}
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	public static Map<String, Object> mapKeyToUnderline(Map<String, Object> param) {
		Map<String, Object> map = Maps.newHashMap();
		for (Map.Entry<String, Object> entity : param.entrySet()) {
			map.put(StrUtil.toUnderlineCase(entity.getKey()), entity.getValue());
		}
		return map;
	}
	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence)obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>)obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>)obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			Object[] object = (Object[])obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}
	/**
	 * 18位身份证号码的基本数字和位数验校
	 * @param idcard
	 * @return
	 */
	public static boolean is18Idcard(String idcard) {
		return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
			idcard);
	}
	/**
	 * 18位身份证号码的基本数字和位数验校
	 * @param idcard
	 * @return
	 */
	public static boolean is11Phone(String phone) {
		return Pattern.matches("^1[3-8][0-9]\\d{8}$", phone);
	}
	/**
	 * 
	 * @Title: getIdcard6   
	 * @Description: 获取身份证后6位
	 * @param @param idCard
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	public static String getIdcard6(String idCard) {
		if (isEmpty(idCard)) {
			return "";
		}
		try {
			idCard = MD5Util.encode(StrUtil.sub(idCard, idCard.length() - 6, idCard.length()).trim());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return idCard;
	}
	/**
	 * 传入数字字符串 并加1
	 * @param str
	 * @return
	 */
	public static String strAdd(String str) {
		BigInteger billCode = new BigInteger(str);
		return String.valueOf(billCode.add(new BigInteger("1")));
	}
	public static void main(String[] args) {
		System.out.println(is11Phone("18711019603"));
		// e10adc3949ba59abbe56e057f20f883e
		String idCard = "430124199610123456";
		
		System.out.println(StrUtil.sub(idCard, idCard.length() - 6, idCard.length()).trim());
		System.out.println(getIdcard6(idCard));
		try {
			System.out.println(MD5Util.encode("123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println(camelToUnderline("myJavaClass"));
		 * System.out.println(StrUtil.toUnderlineCase("myJavaClass")); Map<String, Object> map =
		 * Maps.newHashMap(); map.put("myJava", "23232"); map.put("myBava", "1111"); for
		 * (Map.Entry<String, Object> entity : mapKeyToUnderline(map).entrySet()) {
		 * System.out.println(entity.getKey() + "==" + entity.getValue()); }
		 */
	}
}
