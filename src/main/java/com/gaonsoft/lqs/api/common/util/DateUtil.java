package com.gaonsoft.lqs.api.common.util;

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
}
