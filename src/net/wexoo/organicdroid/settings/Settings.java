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

package net.wexoo.organicdroid.settings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provide configuration elemets to the app. The only mandatory configuration item is the {@link #formUri()} parameter
 * which is the url to the API to receive data from the onlineservice
 * 
 * @author naikon
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Settings {
	
	/**
	 * The uri for the server API.
	 */
	String apiUri() default "";
	
	/**
	 * @return value in milliseconds for network operations timeout (default 15000ms).
	 */
	int socketTimeout() default 15000;
	
	/**
	 * @return socketBufferSize value (default 8192bytes).
	 */
	int socketBufferSize() default 8192;
	
	String apiValue() default "";
	
	String apiKey() default "";
	
	int databaseVersion() default 1;
	
	String databaseName() default "app-database";
	
	/**
	 * @return EnvironmentField Array listing the fields to be included in the environment reader.
	 */
	AppEnvironmentField[] customEnvironmentContent() default {};
	
	/**
	 * @return GPS update interval in ms (default 3000)
	 */
	int defaultGpsUpdateInterval() default 3000;
	
	/**
	 * @return GPS update distance meters
	 */
	int defaultGpsUpdatedistance() default 10;
}