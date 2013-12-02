/***
 * Copyright (C) 2011  wexoo
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

package net.wexoo.organicdroid.util;

/**
 * @author wexoo
 */
public class DALUtil {
	
	public static boolean getBooleanValueOfString(final String value) {
		try {
			return value != null && value.equals("1") ? true : false;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get the database version from API.
	 * 
	 * @param appVersionName
	 *           the version name from this app
	 * @param apiLevel
	 *           the api level
	 * @param apiVersion
	 *           the api version
	 * @return the db version as String
	 * @author wexoo
	 * @since 1.0.0 Jul 6, 2011
	 * @version 1.0.0 Jul 6, 2011
	 */
	// public static String getOnlineDatabaseVersion(final String appVersionName, final int apiLevel,
	// final String apiVersion) {
	// String dbVersion = Preferences.getStringPreference(R.string.local_database_version_key, null);
	//
	// final RestClient ri = new RestClient(HeurigenApp.getConfig().apiUri(), new String("version/current/dbversion/"
	// + dbVersion + "/appversion/" + appVersionName + "/device/android/apiversion/" + apiVersion
	// + "/apilevel/" + apiLevel));
	// try {
	// ri.callWebService(RestClient.GET);
	// } catch (final IllegalStateException e) {
	// e.printStackTrace();
	// } catch (final ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (final IOException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// if (ri.getObjectJson() != null) {
	// final JSONArray jArray = ri.getObjectJson();
	// for (int i = 0; i < jArray.length(); i++) {
	// final JSONObject json_data = jArray.getJSONObject(i);
	// dbVersion = json_data.getString("db_version");
	// }
	// }
	// } catch (final JSONException e) {
	// e.printStackTrace();
	// }
	// return dbVersion;
	// }
	
}