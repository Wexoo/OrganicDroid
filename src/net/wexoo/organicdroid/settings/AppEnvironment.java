/***
 * Copyright (C) 2013 wexoo
 * p.weixlbaumer@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.wexoo.organicdroid.settings;

import static net.wexoo.organicdroid.settings.AppEnvironmentField.APP_VERSION_CODE;
import static net.wexoo.organicdroid.settings.AppEnvironmentField.APP_VERSION_NAME;
import static net.wexoo.organicdroid.settings.AppEnvironmentField.PACKAGE_NAME;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import net.wexoo.organicdroid.Log;
import net.wexoo.organicdroid.base.BaseApplication;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * AppEnvironment.java - Utility class containing system specific data
 * 
 * @author wexoo
 */
public class AppEnvironment {
	
	private static final String TAG = AppEnvironment.class.getSimpleName();
	
	/**
	 * Retrieves Android SDK API level using the best possible method.
	 * 
	 * @return android SDK API level as int
	 */
	public int getAPILevel() {
		int apiLevel;
		try {
			final Field SDK_INT = Build.VERSION.class.getField("SDK_INT");
			
			apiLevel = SDK_INT.getInt(null);
		} catch (final SecurityException e) {
			apiLevel = Build.VERSION.SDK_INT;
		} catch (final NoSuchFieldException e) {
			apiLevel = Build.VERSION.SDK_INT;
		} catch (final IllegalArgumentException e) {
			apiLevel = Build.VERSION.SDK_INT;
		} catch (final IllegalAccessException e) {
			apiLevel = Build.VERSION.SDK_INT;
		}
		return apiLevel;
	}
	
	/**
	 * @return android version code (e.g. '2.7' or '4.3') as String
	 */
	public String getAndroidVersion() {
		final String androidVersion = android.os.Build.VERSION.RELEASE;
		return androidVersion;
	}
	
	/**
	 * Reads the environment properties for the app.
	 */
	public AppEnvironmentData getPackageInfo(final Context context) {
		
		// This is where we collect environment data
		final AppEnvironmentData mEnvironmentProperties = new AppEnvironmentData();
		
		try {
			final PackageManager pm = context.getPackageManager();
			final PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			
			final Settings config = BaseApplication.getSettings();
			AppEnvironmentField[] fields = config.customEnvironmentContent();
			
			if (fields.length == 0) fields = BaseApplication.DEFAULT_ENVIRONMENT_FIELDS;
			
			final List<AppEnvironmentField> fieldsList = Arrays.asList(fields);
			
			if (pi != null) {
				// Application Version
				if (fieldsList.contains(APP_VERSION_CODE))
					mEnvironmentProperties.put(APP_VERSION_CODE, Integer.toString(pi.versionCode));
				if (fieldsList.contains(APP_VERSION_NAME))
					mEnvironmentProperties.put(APP_VERSION_NAME, pi.versionName != null ? pi.versionName : "not set");
			} else
				// Could not retrieve package info...
				mEnvironmentProperties.put(APP_VERSION_NAME, "Package info unavailable");
			
			// Application Package name
			if (fieldsList.contains(PACKAGE_NAME)) mEnvironmentProperties.put(PACKAGE_NAME, context.getPackageName());
			
		} catch (final Exception e) {
			e.printStackTrace();
			Log.e(AppEnvironment.TAG, e.getMessage());
		}
		return mEnvironmentProperties;
	}
}