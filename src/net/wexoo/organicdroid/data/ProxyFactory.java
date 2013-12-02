/***
 * Copyright (C) 2011  naikon, wexoo
 * android@geekosphere.org
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

package net.wexoo.organicdroid.data;

import java.util.HashMap;
import java.util.Map;

import net.wexoo.organicdroid.Log;

/**
 * Factory for creating BaseProxy objects
 * 
 * @author wexoo
 */
public class ProxyFactory {
	
	private static final String TAG = ProxyFactory.class.getSimpleName();
	
	/** Dynamic configuration value - set to false to force initialization of new BaseProxy */
	public static boolean ACCESS_MODE_CHANGED = false;
	
	private static Map<Class<?>, BaseProxy<?>> proxyMap = new HashMap<Class<?>, BaseProxy<?>>();
	
	/**
	 * Factory class is not meant to be initiated via constructor
	 */
	private ProxyFactory() {
	}
	
	public synchronized static <T> BaseProxy<T> getProxy(final Class<T> beanClass) {
		if (!proxyMap.containsKey(beanClass) || ProxyFactory.ACCESS_MODE_CHANGED) {
			// if (BaseApplication.ACCESS_MODE_ONLINE) {
			// Log.d(ProxyFactory.TAG, "New online proxy created for " + beanClass.getSimpleName() + "!");
			// proxyMap.put(beanClass, newProxy(beanClass));
			// } else {
			Log.d(ProxyFactory.TAG, "New offline proxy created for " + beanClass.getSimpleName() + "!");
			proxyMap.put(beanClass, newProxy(beanClass));
			// }
			ProxyFactory.ACCESS_MODE_CHANGED = false;
		}
		Log.d(ProxyFactory.TAG, "Accessing proxy of " + beanClass.getSimpleName() + "!");
		return (BaseProxy<T>) proxyMap.get(beanClass);
	}
	
	public synchronized static <T> BaseProxy<T> newProxy(final Class<T> beanClass) {
		return new BaseProxy<T>() {
			
			@Override
			protected Class<T> getEntityClass() {
				return beanClass;
			}
		};
	}
}