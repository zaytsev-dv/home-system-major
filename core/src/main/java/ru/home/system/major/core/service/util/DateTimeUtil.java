package ru.home.system.major.core.service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil
{
	public static final String YYYY_MM_DD_T_HH_MM = "yyyy-MM-dd'T'HH:mm";

	private DateTimeUtil()
	{
	}

	public static String dateTimeToString(LocalDateTime localDateTime, String pattern)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	public static LocalDateTime stringToDateTime(String str, String pattern)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(str, formatter);
	}
}
