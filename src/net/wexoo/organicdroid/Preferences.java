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

package net.wexoo.organicdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Preferences.java
 * 
 * @author wexoo
 */
public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	private static final String TAG = Preferences.class.getSimpleName();

	private static String LP_ACCESS_MODE_KEY;
	private static String P_EXPORT_DB_KEY;

	private ListPreference accessModePreference;

	private static SharedPreferences sharedPrefs;

	private Preference exportDbPreference;

	/**
	 * Gets the shared prefs.
	 * 
	 * @return the shared prefs
	 */
	public static SharedPreferences getSharedPrefs() {
		if (Preferences.sharedPrefs == null) {
			// Preferences.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		}
		return Preferences.sharedPrefs;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// addPreferencesFromResource(R.xml.preferences);

		// Preferences.LP_ACCESS_MODE_KEY = this.getString(R.string.lp_access_mode_key);
		// accessModePreference = (ListPreference) findPreference(Preferences.LP_ACCESS_MODE_KEY);
		//
		// Preferences.P_EXPORT_DB_KEY = this.getString(R.string.p_export_db_to_sd_key);
		// exportDbPreference = findPreference(Preferences.P_EXPORT_DB_KEY);
		// exportDbPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		//
		// @Override
		// public boolean onPreferenceClick(final Preference preference) {
		// Preferences.this.exportDatabase();
		// return true;
		// }
		// });
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();

		updateSummary(Preferences.LP_ACCESS_MODE_KEY);

		Preferences.getSharedPrefs().registerOnSharedPreferenceChangeListener(this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();

		Preferences.getSharedPrefs().unregisterOnSharedPreferenceChangeListener(this);
	}

	// /**
	// * Gets the string preference.
	// *
	// * @param keyCode the key code
	// * @param key the key
	// * @return the string preference
	// */
	// public static String getStringPreference(final Integer keyCode, final String key) {
	// return Preferences.getStringPreference(HeurigenApp.mainContext, keyCode, key);
	// }

	/**
	 * Gets the string preference.
	 * 
	 * @param appContext
	 *          the app context
	 * @param keyCode
	 *          the key code
	 * @param key
	 *          the key
	 * @return the string preference
	 */
	public static String getStringPreference(final Context appContext, final Integer keyCode, String key) {
		String value = "";

		try {
			if (keyCode != null) {
				key = appContext.getString(keyCode);
			}

			value = Preferences.getSharedPrefs().getString(key, "");
		} catch (final Exception e) {
			Log.e(Preferences.TAG, "Couldn't fetch string preference for keys: " + keyCode + " / " + key);
			return "";
		}
		return value;
	}

	// /**
	// * Sets the string preference.
	// *
	// * @param keyCode the key code
	// * @param key the key
	// * @param value the value
	// */
	// public static void setStringPreference(final Integer keyCode, final String key, final String value) {
	// Preferences.setStringPreference(HeurigenApp.mainContext, keyCode, key, value);
	// }

	/**
	 * Sets the string preference.
	 * 
	 * @param appContext
	 *          the app context
	 * @param keyCode
	 *          the key code
	 * @param key
	 *          the key
	 * @param value
	 *          the value
	 */
	public static void setStringPreference(final Context appContext, final Integer keyCode, String key,
			final String value) {
		if (keyCode != null) {
			key = appContext.getString(keyCode);
		}
		final Editor e = Preferences.getSharedPrefs().edit();
		e.putString(key, value);
		e.commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.SharedPreferences,
	 *      java.lang.String)
	 */
	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		// if (key.equals(Preferences.LP_ACCESS_MODE_KEY)) {
		// Log.d(Preferences.TAG, "access mode changed - onSharedPreferenceChanged");
		// ProxyFactory.ACCESS_MODE_CHANGED = true;
		// updateSummary(Preferences.LP_ACCESS_MODE_KEY);
		// }
	}

	private void updateSummary(final String key) {
		// if (key.equals(Preferences.LP_ACCESS_MODE_KEY)) {
		// accessModePreference.setSummary(getResources().getString(R.string.lp_access_mode_sum)
		// + " " + accessModePreference.getEntry().toString());
		// }
	}

	/**
	 * Export database.
	 */
	protected void exportDatabase() {
		// if (((HeurigenApp) getApplication()).isExternalStorageAvailable()) {
		// new ExportDatabaseFileTask().execute();
		// } else {
		// UIUtil.showShortToast("External storage is not available, unable to export data.");
		// }
	}

	@Override
	public void onBackPressed() {
		// startActivity(new Intent(this, HomeActivity.class));
	}
}