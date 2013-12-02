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

package net.wexoo.organicdroid.base;

import static net.wexoo.organicdroid.settings.AppEnvironmentField.APP_VERSION_CODE;
import static net.wexoo.organicdroid.settings.AppEnvironmentField.APP_VERSION_NAME;
import static net.wexoo.organicdroid.settings.AppEnvironmentField.PACKAGE_NAME;
import net.wexoo.organicdroid.settings.AppEnvironmentField;
import net.wexoo.organicdroid.settings.Settings;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * BaseApplication.java Sets also specific settings as annotations for the app
 * 
 * @author wexoo
 */
@Settings(apiUri = "http://insert.api.url/here", apiValue = "apiValue", apiKey = "apiKey", databaseName = "choose-local-db-name")
public class BaseApplication extends Application {
	
	/**
	 * Default list of {@link AppEnvironmentField}s to be read from the environment. You can set your own list with
	 * {@link net.wexoo.organicdroid.settings.Settings#customEnvironmentContent()}.
	 */
	public static final AppEnvironmentField[] DEFAULT_ENVIRONMENT_FIELDS = {APP_VERSION_CODE, APP_VERSION_NAME,
				PACKAGE_NAME};
	
	public static Context mainContext;
	private static Settings appSettings;
	
	public static boolean ACCESS_MODE_ONLINE = false;
	
	@Override
	public void onCreate() {
		appSettings = getClass().getAnnotation(Settings.class);
		
		super.onCreate();
	}
	
	public static Settings getSettings() {
		return appSettings;
	}
	
	public static String getDatabaseName() {
		return appSettings.databaseName();
	}
	
	public static int getDatabaseVersion() {
		return appSettings.databaseVersion();
	}
	
	// SYSTEM CHECKS
	public boolean isOnline() {
		final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting())
			return true;
		return false;
	}
	
	public boolean isExternalStorageAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
}