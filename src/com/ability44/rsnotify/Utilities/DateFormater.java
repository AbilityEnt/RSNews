package com.ability44.rsnotify.Utilities;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class DateFormater {

	@SuppressLint("SimpleDateFormat")
	public static String RsNewsDateFormater(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		SimpleDateFormat newDate = new SimpleDateFormat("MMM/d/yyyy");
		newDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		String formatedD = newDate.format(date.getTime());
		return formatedD;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String YoutubeDateFormater(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss +0000");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		SimpleDateFormat newDate = new SimpleDateFormat("MMM/d/yyyy");
		String formatedD = newDate.format(date.getTime());
		return formatedD;
	}
}
