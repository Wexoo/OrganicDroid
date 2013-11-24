package net.wexoo.organicdroid.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.wexoo.organicdroid.Log;

public class DateAndTimeConverter {

	public static final String TAG = DateAndTimeConverter.class.getSimpleName();

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.GERMAN);

	public static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
	public static final SimpleDateFormat GERMAN_DATE_FORMATTER = new SimpleDateFormat(GERMAN_DATE_FORMAT, Locale.GERMAN);

	public static final String GERMAN_SPELLED_DATE_FORMAT = "dd. MMMM yyyy";
	public static final SimpleDateFormat GERMAN_SPELLED_DATE_FORMATTER =
			new SimpleDateFormat(GERMAN_SPELLED_DATE_FORMAT, Locale.GERMAN);

	public static final String GERMAN_SPELLED_DATE_WITH_TIME_FORMAT = "dd. MMMM yyyy HH:mm";
	public static final SimpleDateFormat GERMAN_SPELLED_DATE_WITH_TIME_FORMATTER =
			new SimpleDateFormat(GERMAN_SPELLED_DATE_WITH_TIME_FORMAT, Locale.GERMAN);

	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	public static final SimpleDateFormat DEFAULT_TIME_FORMATTER = new SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.GERMAN);

	public static final String TIME_FORMAT_WITHOUT_SECONDS = "HH:mm";
	public static final SimpleDateFormat TIME_FORMATTER_WITHOUT_SECONDS =
			new SimpleDateFormat(TIME_FORMAT_WITHOUT_SECONDS, Locale.GERMAN);

	public static Date getDefaultDateValueOfString(final String value) {
		try {
			return value.equals("") ? null : DEFAULT_DATE_FORMATTER.parse(value);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStringValueOfDefaultDate(final Date value) {
		return value == null ? null : DEFAULT_DATE_FORMATTER.format(value);
	}

	public static Date getTimeValueOfString(final String value) {
		try {
			return value.equals("") ? null : DEFAULT_TIME_FORMATTER.parse(value);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGermanDateAsString(final Date date) {
		return GERMAN_DATE_FORMATTER.format(date);
	}

	public static String getGermanSpelledDateAsString(final Date date) {
		return GERMAN_SPELLED_DATE_FORMATTER.format(date);
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