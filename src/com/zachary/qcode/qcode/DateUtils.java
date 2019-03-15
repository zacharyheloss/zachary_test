package com.zachary.qcode;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author zhangcheng
 * @describe 日期处理类
 *
 */
public class DateUtils {

	/**
	 * 年月日：yyyy-MM-dd
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 年月日时分秒： yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String SPLIT_FORMAT = "yyyyMMddHHmmss";

	//私有构造函数
	private DateUtils() {}

	/**
	 * 获取年 yyyy
	 * @return
	 */
	public static String getYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	/**
	 * 获取年月日 yyyy-MM-dd
	 * @return
	 */
	public static String getDay() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 获取年月日 yyyyMMdd
	 * @return
	 */
	public static String getDays() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	/**
	 * 获取年月日时分秒 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 获取年月日时分秒 yyyyMMddHHmmss
	 * @return
	 */
	public static String getTimes() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 格式化日期
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String formatStr) {

		if (date == null)
			return null;

		if (StringUtils.isBlank(formatStr))
			formatStr = DATE_FORMAT;

		final DateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	/**
	 * 格式化日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, SimpleDateFormat format) {
		
		if (date == null){
			return null;
		}
		if (null == format){
			format = new SimpleDateFormat(TIMESTAMP_FORMAT);
		}
		return format.format(date);
	}

	/**
	 * 字符串转日期
	 * @param str
	 * @param dateFormat
	 * @return
	 */
	public static Date parse(final String str, String dateFormat) {

		if(StringUtils.isBlank(str)){
			return null;
		}
		
		if (StringUtils.isBlank(dateFormat))
			dateFormat = DATE_FORMAT;

		final DateFormat format = new SimpleDateFormat(dateFormat);
		try {
			return format.parse(str);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 年份增删
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYear(Date date, int years) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	/**
	 * 日 增删
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 小时 增删
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHour(Date date, int hours) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	/**
	 * 分钟 增删
	 * @param date
	 * @param mins
	 * @return
	 */
	public static Date addMin(Date date, int mins) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, mins);
		return c.getTime();
	}

	/**
	 * 秒  增删
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSecond(Date date, int seconds) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}

	/**
	 * 比较时间大小
	 * @param preDate
	 * @param date
	 * @return
	 */
	public static int daysDifference(Date preDate, Date date) {
		final long milliSeconds = date.getTime() - preDate.getTime();
		return new Long(milliSeconds / 1000 / 3600 / 24).intValue();
	}
	
	/**
	 * 得到修改日期,同时修改 时 分 秒 毫秒数
	 * @param date 时间
	 * @param days 天数
	 * @param hours 小时数
	 * @param mins 分钟数
	 * @param seconds 秒数
	 * @param milliSeconds 毫秒数
	 */
	public static Date addDay(Date date, int days,int hours,int mins,int seconds,int milliSeconds){
        Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, days);
		ca.set(Calendar.HOUR_OF_DAY, hours);
		ca.set(Calendar.MINUTE, mins);
		ca.set(Calendar.SECOND, seconds);  
		ca.set(Calendar.MILLISECOND, milliSeconds);
		return ca.getTime();
	}
	
}
