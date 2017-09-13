package com.gscdn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GsUtil {
	public static String getToday() {
		String _today = null;
		Calendar _calendar = null;
		Date _date = null;

		_calendar = Calendar.getInstance();
		_date = _calendar.getTime();
		_today = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(_date);

		return _today;
	}
}
