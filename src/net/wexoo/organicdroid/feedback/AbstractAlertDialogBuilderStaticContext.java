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

package net.wexoo.organicdroid.feedback;

import android.content.Context;

/**
 * AbstractAlertDialogBuilderStaticContext.java <br />
 * Must (!) override getDefaultContext()
 * 
 * @author wexoo
 */
public abstract class AbstractAlertDialogBuilderStaticContext extends AbstractAlertDialogBuilder {
	
	public AbstractAlertDialogBuilderStaticContext(final Integer messageKey) {
		super(getDefaultContext(), messageKey);
	}
	
	public AbstractAlertDialogBuilderStaticContext(final String message) {
		super(getDefaultContext(), message);
	}
	
	protected static Context getDefaultContext() {
		return null;
	}
	
	@Override
	protected abstract void positiveButtonAction(Context context);
	
	@Override
	protected void neutralButtonAction(final Context context) {
	}
	
	@Override
	protected abstract void negativeButtonAction(Context context);
}