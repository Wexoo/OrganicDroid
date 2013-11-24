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
package net.wexoo.organicdroid.feedback;

import net.wexoo.organicdroid.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author wexoo
 */
public abstract class AbstractAlertDialogBuilder extends Builder {

	public AbstractAlertDialogBuilder(final Context context, final Integer messageKey) {
		this(context, messageKey, R.string.alert_yes, null, R.string.alert_no);
	}

	public AbstractAlertDialogBuilder(final Context context, final Integer messageKey, final Integer posButtonKey,
			final Integer neuButtonKey, final Integer negButtonKey) {
		super(context);

		this.setMessage(context.getString(messageKey));
		setCancelable(false);
		this.setPositiveButton(context.getString(posButtonKey),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						AbstractAlertDialogBuilder.this.positiveButtonAction(context);
					}
				});

		if (neuButtonKey != null) {
			this.setNeutralButton(context.getString(posButtonKey),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(final DialogInterface dialog, final int id) {
							AbstractAlertDialogBuilder.this.neutralButtonAction(context);
						}
					});
		}
		this.setNegativeButton(context.getString(negButtonKey),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						AbstractAlertDialogBuilder.this.negativeButtonAction(context);
						dialog.cancel();
					}
				});
	}

	public void showAlertDialog() {
		final AlertDialog alert = create();
		alert.show();
	}

	protected abstract void positiveButtonAction(Context context);

	protected void neutralButtonAction(final Context context) {
	}

	protected abstract void negativeButtonAction(Context context);
}