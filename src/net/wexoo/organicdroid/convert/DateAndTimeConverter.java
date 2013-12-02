package net.wexoo.organicdroid.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.wexoo.organicdroid.Log;

public class DateAndTimeConverter {
	
	public static final String TAG = DateAndTimeConverter.class.getSimpleName();
	
	public static final String DEFAULT_DATE_FORMAT = "dd.MMM yyyy HH:mm";
	public static final SimpleDateFormat DEFAULT_DATE_FORMATTER = format(DEFAULT_DATE_FORMAT);
	
	public static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
	public static final SimpleDateFormat GERMAN_DATE_FORMATTER = format(GERMAN_DATE_FORMAT);
	
	public static final String DATE_WHOLE_MONTH_FORMAT = "dd. MMMM yyyy";
	public static final SimpleDateFormat DATE_WHOLE_MONTH_FORMATTER = format(DATE_WHOLE_MONTH_FORMAT);
	
	public static final String GERMAN_SPELLED_DATE_WITH_TIME_FORMAT = "dd. MMMM yyyy HH:mm";
	public static final SimpleDateFormat GERMAN_SPELLED_DATE_WITH_TIME_FORMATTER = format(GERMAN_SPELLED_DATE_WITH_TIME_FORMAT);
	
	public static final String FILE_DATE_FORMAT = "yyyy-MM-dd";
	public static final SimpleDateFormat FILE_DATE_FORMATTER = format(FILE_DATE_FORMAT);
	
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	public static final SimpleDateFormat DEFAULT_TIME_FORMATTER = format(DEFAULT_TIME_FORMAT);
	
	public static final String TIME_FORMAT_WITHOUT_SECONDS = "HH:mm";
	public static final SimpleDateFormat TIME_FORMATTER_WITHOUT_SECONDS = format(TIME_FORMAT_WITHOUT_SECONDS);
	
	private static SimpleDateFormat format(String format) {
		return new SimpleDateFormat(format, Locale.getDefault());
	}
	
	public static Date getStringAsDate(final String dateString, final SimpleDateFormat format) {
		try {
			return format.parse(dateString);
		} catch (final ParseException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDateAsString(final Date date, final SimpleDateFormat format) {
		return date == null ? null : format.format(date);
	}
	
	public static String getDateAsString(final Date date) {
		return DEFAULT_DATE_FORMATTER.format(date);
	}
	
	public static Date getStringAsDate(final String dateString) {
		return getStringAsDate(dateString, DEFAULT_DATE_FORMATTER);
	}
	
	public static Date getFileDateValueOfString(final String value) {
		return getStringAsDate(value, FILE_DATE_FORMATTER);
	}
	
	public static String getFileStringValueOfDate(final Date value) {
		return getDateAsString(value, FILE_DATE_FORMATTER);
	}
	
	public static Date getTimeValueOfString(final String value) {
		return getStringAsDate(value, DEFAULT_TIME_FORMATTER);
	}
	
	public static String getGermanDateAsString(final Date date) {
		return GERMAN_DATE_FORMATTER.format(date);
	}
	
	public static String getWholeMonthDateAsString(final Date date) {
		return DATE_WHOLE_MONTH_FORMATTER.format(date);
	}
	
	public static Date getGermanSpelledDateStringAsDate(final String dateString) {
		try {
			return GERMAN_SPELLED_DATE_WITH_TIME_FORMATTER.parse(dateString);
		} catch (final ParseException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}