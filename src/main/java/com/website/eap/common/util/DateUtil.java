package com.website.eap.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 操作日期的工具类
 */
public class DateUtil {
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATEFORMAT1 = "yyyy/MM/ddHH:mm:ss";
	public static final String DATEFORMAT2 = "yyyy/MM/dd";
	public static final String DATEFORMAT3 = "yyyyMMdd";
	public static final String DATEFORMAT4 = "yyyy-MM-dd";
	
	
	
	public static String  getDateMonth(Date date,int month){
		  if (date==null){
			  return null;
		  }
		  Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
			return dateFormat.format(cal.getTime());
	}

	/**
	 * 取到指定日期的开始小时,并可以小时前后相对位移
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date getDateTimeBeginHour(Date date, int hours) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	public static Date getDateTimeBeginHourMinute(Date date, int hours,
			int minute) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	public static Date getDateTimeBeginMinute(Date date, int minute) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, 0);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	public static Date getDateTimeEndMinute(Date date, int minute) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, 59);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	/**
	 * 取到指定日期的结束小时,并可以小时前后相对位移
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date getDateTimeEndHour(Date date, int hours) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	public static Date StringToDateTime(Date date, String hour, String minute) {
		String day_of_week = DateUtil.dateTimetoString(date);
		String[] day_in_year = day_of_week.split(" ");
		StringBuilder buffer = new StringBuilder();
		buffer.append(day_in_year[0] + " ");
		buffer.append(hour + ":");
		buffer.append(minute + ":");
		buffer.append("00");
		String dateTime = buffer.toString();
		date = DateUtil.parseDateTime(dateTime);
		return date;
	}

	/**
	 * 日期大小比较
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static int compareDate(String beginDate, String endDate)
			throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date dt1 = df.parse(beginDate);
			Date dt2 = df.parse(endDate);
			if (dt1.getTime() > dt2.getTime()) {
				// begingDate在endDate前
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// begingDate在endDate后
				return -1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			throw new Exception("日期格式化异常");
		}

	}

	/**
	 * 获取当前日期前一个月的开始时间
	 * 
	 * @param fullDate
	 * @return
	 */
	public static Date getBeforeMonth(Date fullDate) {
		if (null == fullDate) {
			return (Date) null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fullDate);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前日期前一周的开始时间(yyyy-MM-dd 00:00:00:000)
	 * 
	 * @param fullDate
	 * @return
	 */
	public static Date getBeforeWeek(Date fullDate) {
		if (null == fullDate) {
			return (Date) null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fullDate);
		cal.add(Calendar.DAY_OF_WEEK, -7);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得指定日期前一天的最后一秒 (yyyy-MM-dd 23:59:59:000)
	 * 
	 * @param fullDate
	 *            日期
	 * @return
	 */
	public static Date getLastSecondOfLastDay(Date fullDate) {
		if (null == fullDate) {
			return (Date) null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fullDate);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取今天的开始时间
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取今天的结束时间
	 * 
	 * @return
	 */
	public static Date getTodayEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定格式的日期+时间
	 * 
	 * @param dateTime
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date parseDateTime(String dateTime) {
		try {
			return parse(dateTime, YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取指定月份的第一天。并可以前后动月份
	 * @param fullDate
	 * @param month
	 * @return
	 */
	public static String getFirstDayMonthString(Date fullDate ,int month){
		if (null == fullDate) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fullDate);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 获取指定格式的日期
	 *
	 * @param dateTime
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date parseDate(String dateTime) throws ParseException {
		return parse(dateTime, YYYY_MM_DD);
	}

	/**
	 * 将日期字符串解析成指定格式的Date对象
	 *
	 * @param dateTime
	 *            日期字符串
	 * @param format
	 *            指定格式
	 * @return （正确格式）日期对象
	 * @throws java.text.ParseException
	 */
	public static Date parse(String dateTime, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			if (dateTime == null || dateTime.length() <= 0)
				return null;
			String DateTime = ((dateTime.indexOf('.') > 0)) ? dateTime
					.substring(0, dateTime.indexOf('.')) : dateTime;
			date = dateFormat.parse(DateTime);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式化异常:" + e.getMessage());
		}
		return date;
	}
    /**
	 * 将日期字符串解析成指定格式的Date对象
	 *
	 * @param dateTime
	 *            日期字符串
	 * @param format
	 *            指定格式
	 * @return （正确格式）日期对象
	 * @throws java.text.ParseException
	 */
	public static Date parseTo(String dateTime, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			if (dateTime == null || dateTime.length() <= 0)
				return null;
			date = dateFormat.parse(dateTime);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式化异常:" + e.getMessage());
		}
		return date;
	}

	/**
	 * 将日期类解析成指定格式的日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String datetoString(Date date) {
		if (date == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
		return dateFormat.format(date);
	}

	/**
	 * 取得当前日期
	 *
	 * @return
	 */
	public static String getTodayString() {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
		return dateFormat.format(new Date());
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static String dateTimetoString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}

	public static String dateTimeBegintoString(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 按指定日期,指定分返回时间 timestamp
	 *
	 * @param date
	 * @param minute
	 * @return 返回时间String
	 */
	public static String dateTimeBegintoString(Date date, int minute) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 00);
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(cal.getTime());
	}

	public static String dateTimeEndtoString(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(cal.getTime());
	}

	public static String dateTimetoString(long time) {
		Date date = new Date(time);
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}


	public static Date getBeginCurrentHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();

	}

	public static Date getEndCurrentHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 根据指定日期的00:00移动前后若干天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMorningDate(Date date, int days) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 根据指定日期的23:59:59移动前后若干天
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNightDate(Date date, int days) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static String currentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(new Date());
	}

	public static int compareDate(Date d1, Date d2) {

		if (d1.getTime() > d2.getTime()) {
			return 1;
		}

		else if (d1.getTime() < d2.getTime()) {
			return -1;
		}

		else {
			return 0;
		}
	}

	public static String formatLongToTimeStr(long second, String format) {

		long day = second / (24 * 60 * 60);
		long hour = (second - (day * 24 * 60 * 60)) / (60 * 60);
		long minute = (second - (day * 24 * 60 * 60) - hour * 60 * 60) / 60;
		long s = second - (day * 24 * 60 * 60) - hour * 60 * 60 - minute * 60;
		String str = day + "天" + hour + "小时" + minute + "分钟" + s + "秒";
		return str;
	}

	public static Long formatTimeToLong(String time) {

		String hour = "0";
		String minute = "0";
		String second = "0";
		if (time.contains("时")) {
			hour = time.substring(0, time.indexOf("时"));
		}
		if (time.contains("时") && time.contains("分")) {
			minute = time.substring(time.indexOf("时") + 1, time.indexOf("分"));
		} else if (time.contains("分")) {
			minute = time.substring(0, time.indexOf("分"));
		}

		if (time.contains("分") && time.contains("秒")) {
			second = time.substring(time.indexOf("分") + 1, time.indexOf("秒"));
		} else if( time.contains("秒")) {
			second = time.substring(0, time.indexOf("秒"));
		}
		long s = 0;
		s = Integer.parseInt(hour) * 3600; // 小时
		s += Integer.parseInt(minute) * 60; // 分钟
		s += Integer.parseInt(second);// 秒
		return s;
	}

	/**
	 * 00:00
	 * @param time
	 * @return
	 */
	public static Long formatTimeToLong2(String time) {
		String[] strs = time.split(":");
		String h ="00";
		String m= "00";
		String s= "00";
		if(strs.length==3){
			s = strs[2];
			m =strs[1];
			h =strs[0];
		}
		else if(strs.length==2){
			s = strs[1];
			m = strs[0];
		}
		Long sTemp = Long.parseLong(s);
		Long mTemp = Long.parseLong(m);
		Long hTemp = Long.parseLong(h);
		Long sum = hTemp*3600 + mTemp*60 + sTemp;
		return sum;
	}

	public static String formatLongToTimeStr(Date date) {
		long between = (date.getTime() - new Date().getTime()) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between % 60 / 60;
		String str = day + "天" + hour + "小时" + minute + "分钟" + second + "秒";
		return str;
	}

	public static Date getAfterDate(Date date, int days) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static String formatLongToTime(Date date, int days) {
		Date endTime = DateUtil.getAfterDate(date, days);
		long between = (endTime.getTime() - new Date().getTime()) / 1000;// 除以1000是为了转换成秒
		if (between < 0) {
			return "过期";
		}
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between % 60 / 60;
		String str = day + "天" + hour + "小时" + minute + "分钟" + second + "秒";

		return str;
	}

	public static Long getDays(Date beforeDate, Date afterDate) {
		long between = (afterDate.getTime() - beforeDate.getTime()) / 1000;// 除以1000是为了转换成秒
		if (between < 0) {
			return 0L;
		}
		long day = between / (24 * 3600);
		return day;
	}

	/**
	 * 取当前时间与传入时间的差值
	 *
	 * @param date
	 * @return
	 */
	public static Long getTims(Date date) {
		long second = (new Date().getTime() - date.getTime()) / (60 * 1000);
		return second;
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static String filetoDate(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return dateFormat.format(date);
	}

	/**
	 * 将特定格式的字符串转换为date格式
	 */
	public static Date dateFormat1ToDate(String date){
		DateFormat dateFormat = new SimpleDateFormat(DATEFORMAT1);
		Date d=null;
		try {
			d = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
		return d;
	}
	/**
	 * 将特定格式的字符串转换为date格式
	 */
	public static Date dateFormat2ToDate(String date){
		DateFormat dateFormat = new SimpleDateFormat(DATEFORMAT2);
		Date d=null;
		try {
			d = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
		return d;
	}
	/**
	 * 将特定格式的字符串转换为date格式
	 */
	public static Date dateFormat3ToDate(String date){
		DateFormat dateFormat = new SimpleDateFormat(DATEFORMAT3);
		Date d=null;
		try {
			d = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
		return d;
	}
	/**
	 * 将特定格式的字符串转换为date格式
	 */
	public static Date dateFormat4ToDate(String date){
		DateFormat dateFormat = new SimpleDateFormat(DATEFORMAT4);
		Date d=null;
		try {
			d = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 当前月份的结束时间
	 * @param moth
	 * @return
	 */
	public static String lastDayOfMonth(Long month) {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
		cal.setTime(date);
		cal.set(Calendar.MONTH,month.intValue());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().toString();
    }
}
