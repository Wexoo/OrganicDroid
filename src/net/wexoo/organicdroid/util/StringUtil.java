package net.wexoo.organicdroid.util;

public class StringUtil {
	
	public static boolean checkStringForNull(final String value) {
		return value == null || value.equals("") || value.equals("null");
	}
}
