package com.tw.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String yyyyMMdd_HH = "yyyyMMdd-HH";

	public static final String yyyyMMdd = "yyyyMMdd";

	public static String formatDate(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.parse(strDate);
	}

	public static String formatDate(Date date, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date parse(String strDate, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(strDate);
	}

}
