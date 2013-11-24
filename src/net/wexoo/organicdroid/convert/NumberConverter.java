package net.wexoo.organicdroid.convert;

import java.text.DecimalFormat;

public class NumberConverter {

	public static final String NUMBER_FORMAT_2_DECIMAL_PLACES = "#.##";

	public static Long getLongValueOfString(final String value) {
		try {
			return value.equals("") ? null : Long.valueOf(value.replaceAll("[^\\d]", "").replaceAll(" ", ""));
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer getIntegerValueOfString(final String value) {
		if (value != "null") {
			try {
				return value.equals("") ? null : Integer.valueOf(value);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Double getDoubleValueOfString(final String value) {
		try {
			return value.equals("") ? null : Double.valueOf(value);
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDoubleToTwoDecimalPlacesString(Double d) {
		DecimalFormat twoDForm = new DecimalFormat(NUMBER_FORMAT_2_DECIMAL_PLACES);
		return twoDForm.format(d);
	}

	public static String addLeadingZeroToLong(final Long value) {
		return value < 10 ? "0" + value : value.toString();
	}
}
