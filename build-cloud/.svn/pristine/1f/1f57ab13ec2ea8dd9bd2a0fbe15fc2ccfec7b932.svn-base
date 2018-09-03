package com.build.cloud.common.utils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.collect.Lists;
import com.sunsine.common.util.MathUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: DateUtils
 * @Description: 日期处理
 * @author: liutao
 * @date: 2018年3月31日 下午3:59:54
 */
public class DateUtils {
	public final static String TIMEZONE = "GMT+8";
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式(yyyy-MM) */
	public final static String DATE_MOTH_PATTERN = "yyyy-MM";
	
	public final static String DD_PATTERN = "dd";
	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * @param date 日期
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}
	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * @param date 日期
	 * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}
	/* 
     * 将时间转换为时间戳
     */  
	 public static String dateToStamp(){
        String res;
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
	/**
	 * 字符串转换成日期
	 * @param strDate 日期字符串
	 * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (StrUtil.isBlank(strDate)) {
			return null;
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseLocalDateTime(strDate).toDate();
	}
	/**
	 * 根据周数，获取开始日期、结束日期
	 * @param week 周期 0本周，-1上周，-2上上周，1下周，2下下周
	 * @return 返回date[0]开始日期、date[1]结束日期
	 */
	public static Date[] getWeekStartAndEnd(int week) {
		DateTime dateTime = new DateTime();
		LocalDate date = new LocalDate(dateTime.plusWeeks(week));
		date = date.dayOfWeek().withMinimumValue();
		Date beginDate = date.toDate();
		Date endDate = date.plusDays(6).toDate();
		return new Date[] { beginDate, endDate };
	}
	/**
	 * 对日期的【秒】进行加/减
	 * @param date 日期
	 * @param seconds 秒数，负数为减
	 * @return 加/减几秒后的日期
	 */
	public static Date addDateSeconds(Date date, int seconds) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusSeconds(seconds).toDate();
	}
	/**
	 * 对日期的【分钟】进行加/减
	 * @param date 日期
	 * @param minutes 分钟数，负数为减
	 * @return 加/减几分钟后的日期
	 */
	public static Date addDateMinutes(Date date, int minutes) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMinutes(minutes).toDate();
	}
	/**
	 * 对日期的【小时】进行加/减
	 * @param date 日期
	 * @param hours 小时数，负数为减
	 * @return 加/减几小时后的日期
	 */
	public static Date addDateHours(Date date, int hours) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusHours(hours).toDate();
	}
	/**
	 * 对日期的【天】进行加/减
	 * @param date 日期
	 * @param days 天数，负数为减
	 * @return 加/减几天后的日期
	 */
	public static Date addDateDays(Date date, int days) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusDays(days).toDate();
	}
	/**
	 * 对日期的【周】进行加/减
	 * @param date 日期
	 * @param weeks 周数，负数为减
	 * @return 加/减几周后的日期
	 */
	public static Date addDateWeeks(Date date, int weeks) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusWeeks(weeks).toDate();
	}
	/**
	 * 对日期的【月】进行加/减
	 * @param date 日期
	 * @param months 月数，负数为减
	 * @return 加/减几月后的日期
	 */
	public static Date addDateMonths(Date date, int months) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMonths(months).toDate();
	}
	/**
	 * 对日期的【年】进行加/减
	 * @param date 日期
	 * @param years 年数，负数为减
	 * @return 加/减几年后的日期
	 */
	public static Date addDateYears(Date date, int years) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusYears(years).toDate();
	}
	public static Date toSqlDate(Date date) {
		return new  java.util.Date(date.getTime());
	}
	public static final long ND = 1000 * 24 * 60 * 60;
	public static final long NH = 1000 * 60 * 60;
	public static final long NM = 1000 * 60;
	public static String getDatePoor(Date endDate, Date nowDate) {
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少小时
		long hour = diff % ND / NH;
		// 计算差多少分钟
		double min = diff % ND % NH / NM;
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(hour + min / 60);
	}
	/**
	 * @param 要转换的毫秒数
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
	 * @author fy.zhang
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + "天" + hours + "小时" + minutes + "分钟";
		// + seconds + "秒";
	}
	public static List<String> getAllDaysMonthByDate(Date d)// 根据传入的日期获取所在月份所有日期
	{
		List<String> lst = Lists.newArrayList();
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			lst.add(DateUtils.format(date, DD_PATTERN));
			date = getNext(date);
		}
		return lst;
	}
	/**
	 * 获取当前年月日字符串
	 * @param 
	 * @return
	 */
	public static String getDateStr() {
		Date date = new Date();
		String strdate = format(date, "yyyyMMdd");
		return strdate;
	}
	private static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}
	private static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}
	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	public static void main(String[] args) {
		Date exitTime = DateUtil.parse("17:53", "HH:mm");
		Date entryTime = DateUtil.parse("08:11", "HH:mm");
		System.out.println(getDatePoor(exitTime, entryTime));
		System.out.println(MathUtil.divide(30, 24, 1));
		System.out.println(DateUtils.getDateStr());
	}
}
