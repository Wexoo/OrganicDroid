/***
 * Copyright (C) 2013  wexoo
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

package net.wexoo.organicdroid.concurrency;

import android.os.AsyncTask;

/**
 * AbstractAsyncTask.java
 * Convenience class that saves the declarations of generic types on AsyncTask - open for further additions in the future
 * 
 * @author wexoo
 */
public abstract class AbstractAsyncTask extends AsyncTask<Void, Integer, Void> {

   /**
    * @see android.os.AsyncTask#doInBackground(Params[])
    */
   @Override
   protected abstract Void doInBackground(Void... params);
}