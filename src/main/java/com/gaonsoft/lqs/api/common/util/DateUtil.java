package com.gaonsoft.lqs.api.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static Date calendarToDate(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		cal.setTimeInMillis(time);
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date stringToDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}
	
	public static String timestampToDateString(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static Date addFieldFromDate(int field, int amount, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	public static Date addMinuteFieldFromDate(int amount, Date date) {
		return addFieldFromDate(Calendar.MINUTE, amount, date);
	}

	public static Date addHourFieldFromDate(int amount, Date date) {
		return addFieldFromDate(Calendar.HOUR, amount, date);
	}
	
	public static Date addHourOfDayFieldFromDate(int amount, Date date) {
		return addFieldFromDate(Calendar.HOUR_OF_DAY, amount, date);
	}
	
	public static Date addDateFieldFromDate(int amount, Date date) {
		return addFieldFromDate(Calendar.DATE, amount, date);
	}
}
