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

import net.wexoo.organicdroid.Log;
import net.wexoo.organicdroid.PreferencesActivity;
import net.wexoo.organicdroid.R;
import net.wexoo.organicdroid.data.ProxyFactory;
import net.wexoo.organicdroid.feedback.components.NetworkSettingsAlertDialog;
import net.wexoo.organicdroid.slidemenu.BaseSlideMenuView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.OnActionModeFinishedListener;
import com.actionbarsherlock.ActionBarSherlock.OnActionModeStartedListener;
import com.actionbarsherlock.ActionBarSherlock.OnCreatePanelMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnMenuItemSelectedListener;
import com.actionbarsherlock.ActionBarSherlock.OnPreparePanelListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * BaseActivity.java
 * 
 * @author wexoo
 */
@SuppressLint("InlinedApi")
public class BaseActivity extends Activity implements OnCreatePanelMenuListener, OnPreparePanelListener,
			OnMenuItemSelectedListener, OnActionModeStartedListener, OnActionModeFinishedListener {
	
	private static final String TAG = BaseActivity.class.getSimpleName();
	protected SlidingMenu slidingMenu;
	
	protected static boolean newFavorite;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		// ABS Features - must be requested before onCreate call
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	protected void onResume() {
		Log.d(TAG, "on Resume called!");
		
		super.onResume();
	}
	
	protected void buildSlidingMenu() {
		slidingMenu = new SlidingMenu(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_behind_offset);
		slidingMenu.setMenu(createSlideMenuView());
	}
	
	protected BaseSlideMenuView createSlideMenuView() {
		return new BaseSlideMenuView(this);
	}
	
	/**
	 * possible body -> UIUtil.goToActivityAndClearTop(this, INSERT_HOME_ACTIVITY.class);
	 */
	public void onHomeClick(final View v) {
	}
	
	/**
	 * possible body -> UIUtil.goToActivityAndClearTop(this, INSERT_SEARCH_ACTIVITY.class);
	 */
	public void onQuickSearchClick(final View v) {
	}
	
	/**
	 * Call actions from onClick attribute in xml layout.
	 * 
	 * @param view GUI element which is calling the action
	 */
	public void startAction(final View view) {
		view.getTag();
		doAction(view.getId());
	}
	
	/**
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(final android.view.MenuItem item) {
		item.getMenuInfo();
		
		doAction(item.getItemId());
		return super.onContextItemSelected(item);
	}
	
	/**
	 * Start different actions - override in Project
	 * 
	 * @param idOfElement id of GUI element which is calling the action
	 */
	protected void doAction(final Integer idOfElement) {
	}
	
	/**
	 * Convenience method to start website intent
	 */
	protected void visitWebsite(final String website) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(website)));
	}
	
	/**
	 * Convenience method to start email intent
	 */
	protected void sendMail(final String mailAddress) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + mailAddress)));
	}
	
	/**
	 * Navigate to different menus - also used from sliding menu
	 */
	public void onIconClick(final View v) {
		// switch (Integer.parseInt(v.getTag().toString())) {
		//
		// case 1:
		// Log.d(TAG, "Case 1 selected!");
		// break;
		//
		// case 2:
		// if (checkIfOnline()) {
		// Log.d(TAG, "Case 2 selected!");
		// } else {
		// showMobileNetworkIntent();
		// }
		// break;
		// }
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.toggle();
		}
	}
	
	protected void showMobileNetworkIntent() {
		new NetworkSettingsAlertDialog(this).showAlertDialog();
	}
	
	/**
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
	 *      android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	protected void checkOnlineStatusAndSwitchModeIfNecessary() {
		if (checkIfOnline()) {
			switchAccessModeToOnlineIfIsnt();
		} else {
			switchAccessModeToOfflineIfIsnt();
		}
	}
	
	protected boolean checkIfWebServiceIsAvailable() {
		switchAccessModeToOnlineIfIsnt();
		return getTestResultOfWebService();
	}
	
	protected boolean getTestResultOfWebService() {
		return false;
	}
	
	protected boolean checkIfOnline() {
		return ((BaseApplication) getApplication()).isOnline();
	}
	
	protected boolean isAccessModeOnline() {
		return PreferencesActivity.getStringPreference(this, R.string.lp_access_mode_key, null).equals("online");
	}
	
	protected boolean isAccessModeOffline() {
		return PreferencesActivity.getStringPreference(this, R.string.lp_access_mode_key, null).equals("offline");
	}
	
	protected void switchAccessModeToOnlineIfIsnt() {
		if (!isAccessModeOnline()) {
			Log.d(TAG, "ConnectionInfo -> connection exists -> switch to online mode after onlinecheck");
			PreferencesActivity.setStringPreference(this, R.string.lp_access_mode_key, null, "online");
			// ProxyFactory.ACCESS_MODE_CHANGED = true;
		}
	}
	
	protected void switchAccessModeToOfflineIfIsnt() {
		if (!isAccessModeOffline()) {
			Log.d(TAG, "ConnectionInfo -> no connection -> switch to offline mode");
			PreferencesActivity.setStringPreference(this, R.string.lp_access_mode_key, null, "offline");
			ProxyFactory.ACCESS_MODE_CHANGED = true;
		}
	}
	
	/**
	 * Override in sub-activity to cancel all possible running tasks
	 */
	protected void cancelAllAsyncTasksOfActivity() {
	}
	
	@Override
	public void onBackPressed() {
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.toggle();
		} else {
			cancelAllAsyncTasksOfActivity();
			super.onBackPressed();
		}
	}
	
	protected void cancelAllAsynTasks(AsyncTask<?, ?, ?>... asyncTasks) {
		for (AsyncTask<?, ?, ?> asyncTask : asyncTasks) {
			cancelAsyncTask(asyncTask);
		}
	}
	
	protected void cancelAsyncTask(AsyncTask<?, ?, ?> asyncTask) {
		if (asyncTask != null && !asyncTask.isCancelled()) {
			asyncTask.cancel(true);
		}
	}
	
	protected <T> void setSelectedEntity(Class<T> beanClass) {
		try {
			ProxyFactory.getProxy(beanClass).setSelectedEntity(beanClass.newInstance());
		} catch (InstantiationException e) {
			Log.e(TAG, "Creating new instance of " + beanClass.getSimpleName() + " failed! InstantiationException");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Log.e(TAG, "Creating new instance of " + beanClass.getSimpleName() + " failed! IllegalAccessException!");
			e.printStackTrace();
		}
	}
	
	protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
	}
	
	/**
	 * ACTION BAR SHERLOCK ACTIVITY - Code
	 */
	private ActionBarSherlock mSherlock;
	
	protected final ActionBarSherlock getSherlock() {
		if (mSherlock == null) {
			mSherlock = ActionBarSherlock.wrap(this, ActionBarSherlock.FLAG_DELEGATE);
		}
		return mSherlock;
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// Action bar and mode
	// /////////////////////////////////////////////////////////////////////////
	
	public ActionBar getSupportActionBar() {
		return getSherlock().getActionBar();
	}
	
	public ActionMode startActionMode(ActionMode.Callback callback) {
		return getSherlock().startActionMode(callback);
	}
	
	@Override
	public void onActionModeStarted(ActionMode mode) {
	}
	
	@Override
	public void onActionModeFinished(ActionMode mode) {
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// General lifecycle/callback dispatching
	// /////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		getSherlock().dispatchConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onPostResume() {
		super.onPostResume();
		getSherlock().dispatchPostResume();
	}
	
	@Override
	protected void onPause() {
		getSherlock().dispatchPause();
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		cancelAllAsyncTasksOfActivity();
		getSherlock().dispatchStop();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		getSherlock().dispatchDestroy();
		super.onDestroy();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		getSherlock().dispatchPostCreate(savedInstanceState);
		super.onPostCreate(savedInstanceState);
	}
	
	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		getSherlock().dispatchTitleChanged(title, color);
		super.onTitleChanged(title, color);
	}
	
	@Override
	public final boolean onMenuOpened(int featureId, android.view.Menu menu) {
		if (getSherlock().dispatchMenuOpened(featureId, menu))
			return true;
		return super.onMenuOpened(featureId, menu);
	}
	
	@Override
	public void onPanelClosed(int featureId, android.view.Menu menu) {
		getSherlock().dispatchPanelClosed(featureId, menu);
		super.onPanelClosed(featureId, menu);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (getSherlock().dispatchKeyEvent(event))
			return true;
		return super.dispatchKeyEvent(event);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSherlock().dispatchSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		getSherlock().dispatchRestoreInstanceState(savedInstanceState);
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// Native menu handling
	// /////////////////////////////////////////////////////////////////////////
	
	public MenuInflater getSupportMenuInflater() {
		return getSherlock().getMenuInflater();
	}
	
	@Override
	public void invalidateOptionsMenu() {
		getSherlock().dispatchInvalidateOptionsMenu();
	}
	
	// public void supportInvalidateOptionsMenu() {
	// invalidateOptionsMenu();
	// }
	
	@Override
	public final boolean onCreateOptionsMenu(android.view.Menu menu) {
		return getSherlock().dispatchCreateOptionsMenu(menu);
	}
	
	@Override
	public final boolean onPrepareOptionsMenu(android.view.Menu menu) {
		return getSherlock().dispatchPrepareOptionsMenu(menu);
	}
	
	@Override
	public final boolean onOptionsItemSelected(android.view.MenuItem item) {
		return getSherlock().dispatchOptionsItemSelected(item);
	}
	
	@Override
	public void openOptionsMenu() {
		if (!getSherlock().dispatchOpenOptionsMenu()) {
			super.openOptionsMenu();
		}
	}
	
	@Override
	public void closeOptionsMenu() {
		if (!getSherlock().dispatchCloseOptionsMenu()) {
			super.closeOptionsMenu();
		}
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// Sherlock menu handling
	// /////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		if (featureId == android.view.Window.FEATURE_OPTIONS_PANEL)
			return onCreateOptionsMenu(menu);
		return false;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_bar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onPreparePanel(int featureId, View view, Menu menu) {
		if (featureId == android.view.Window.FEATURE_OPTIONS_PANEL)
			return onPrepareOptionsMenu(menu);
		return false;
	}
	
	public boolean onPrepareOptionsMenu(Menu menu) {
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (featureId == android.view.Window.FEATURE_OPTIONS_PANEL)
			return onOptionsItemSelected(item);
		return false;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		
		if (itemId == android.R.id.home) {
			// app icon in action bar clicked
			slidingMenu.toggle();
			
		} else if (itemId == R.id.mi_info) {
			Intent intent = new Intent(this, PreferencesActivity.class);
			intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, PreferencesActivity.AppInfoFragment.class.getName());
			intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
			startActivity(intent);
			
		} else if (itemId == R.id.mi_preferences) {
			startActivity(new Intent(this, PreferencesActivity.class));
		}
		return true;
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// Content
	// /////////////////////////////////////////////////////////////////////////
	
	@Override
	public void addContentView(View view, LayoutParams params) {
		getSherlock().addContentView(view, params);
	}
	
	@Override
	public void setContentView(int layoutResId) {
		setContentView(layoutResId, true);
	}
	
	public void setContentView(int layoutResId, boolean addSlidingMenu) {
		getSherlock().setContentView(layoutResId);
		
		buildSlidingMenu();
	}
	
	@Override
	public void setContentView(View view, LayoutParams params) {
		getSherlock().setContentView(view, params);
	}
	
	@Override
	public void setContentView(View view) {
		getSherlock().setContentView(view);
	}
	
	public void requestWindowFeature(long featureId) {
		getSherlock().requestFeature((int) featureId);
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// Progress Indication
	// /////////////////////////////////////////////////////////////////////////
	
	public void setSupportProgress(int progress) {
		getSherlock().setProgress(progress);
	}
	
	public void setSupportProgressBarIndeterminate(boolean indeterminate) {
		getSherlock().setProgressBarIndeterminate(indeterminate);
	}
	
	public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
		getSherlock().setProgressBarIndeterminateVisibility(visible);
	}
	
	public void setSupportProgressBarVisibility(boolean visible) {
		getSherlock().setProgressBarVisibility(visible);
	}
	
	public void setSupportSecondaryProgress(int secondaryProgress) {
		getSherlock().setSecondaryProgress(secondaryProgress);
	}
}
