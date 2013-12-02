package net.wexoo.organicdroid.convert;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.wexoo.organicdroid.util.StringUtil;

public class NumberConverter {
	
	public static final String NUMBER_FORMAT_2_DECIMAL_PLACES = "#.##";
	
	public static Long getLongValueOfString(final String value) {
		try {
			return StringUtil.checkStringForNull(value) ? 0L : Long.valueOf(value.replaceAll("[^\\d]", "").replaceAll(" ",
						""));
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer getIntegerValueOfString(final String value) {
		try {
			return StringUtil.checkStringForNull(value) ? 0 : Integer.valueOf(value);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Double getDoubleValueOfString(final String value) {
		try {
			return StringUtil.checkStringForNull(value) ? 0.0 : Double.valueOf(value);
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatDoubleToTwoDecimalPlacesString(Double d) {
		DecimalFormat twoDForm = new DecimalFormat(NUMBER_FORMAT_2_DECIMAL_PLACES);
		return twoDForm.format(d);
	}
	
	public static double roundTwoDecimals(double d) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(2, BigDecimal.ROUND_UP);
		d = bd.doubleValue();
		return d;
	}
	
	public static String addLeadingZeroToLong(final Long value) {
		return value < 10 ? "0" + value : value.toString();
	}
}
