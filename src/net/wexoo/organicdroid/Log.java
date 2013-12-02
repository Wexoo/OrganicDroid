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

/**
 * Log.java - a deactivatable logger class who appears to work the same as the android.util.Log logger
 * 
 * @author wexoo
 */
public class Log {
	
	/**
	 * enable/disable logging of app
	 */
	private static final boolean LOG = true;
	
	public static void i(final String tag, final String string) {
		if (Log.LOG) android.util.Log.i(tag, string);
	}
	
	public static void e(final String tag, final String string) {
		if (Log.LOG) android.util.Log.e(tag, string);
	}
	
	public static void d(final String tag, final String string) {
		if (Log.LOG) android.util.Log.d(tag, string);
	}
	
	public static void v(final String tag, final String string) {
		if (Log.LOG) android.util.Log.v(tag, string);
	}
	
	public static void w(final String tag, final String string) {
		if (Log.LOG) android.util.Log.w(tag, string);
	}
}