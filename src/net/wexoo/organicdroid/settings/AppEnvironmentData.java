package net.wexoo.organicdroid.settings;

import java.util.EnumMap;
import java.util.Properties;

/**
 * Stores a environment data with {@link AppEnvironmentField} enum values as keys. This is basically the source of
 * {@link Properties} adapted to extend an EnumMap instead of Hashtable and with a few tweaks to avoid losing crazy
 * amounts of android time in the generation of a date comment when storing to file.
 */
public class AppEnvironmentData extends EnumMap<AppEnvironmentField, String> {
	
	private static final long serialVersionUID = 1L;
	
	public AppEnvironmentData() {
		super(AppEnvironmentField.class);
	}
	
}
