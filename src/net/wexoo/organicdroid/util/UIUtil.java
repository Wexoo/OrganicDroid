/***
 * Copyright (C) 2011 wexoo
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

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author wexoo
 */
public class UIUtil {

	public static final String TAG = UIUtil.class.getSimpleName();

	/**
	 * Invoke "home" action, returning to {@link HomeActivity}.
	 */
	public static void goHome(final Context context, Class<?> homeClass) {
		final Intent intent = new Intent(context, homeClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	public static void showShortToast(final Context context, final String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void showShortToast(final Context context, final int resId) {
		showShortToast(context, context.getString(resId));
	}

	public static void showLongToast(final Context context, final String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void showLongToast(final Context context, final int resId) {
		showLongToast(context, context.getString(resId));
	}
}