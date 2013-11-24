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

package net.wexoo.organicdroid.feedback;

import net.wexoo.organicdroid.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * @author wexoo
 */
public class IntentAlertDialogBuilder extends Builder {

	private final Intent okIntent;
	private final Intent neutralIntent;
	private final Intent cancelIntent;

	public IntentAlertDialogBuilder(final Context context, final Intent okIntent, final Intent neutralIntent,
			final Intent cancelIntent, final Integer message_key) {
		this(context, okIntent, neutralIntent, cancelIntent, message_key, R.string.alert_yes, R.string.alert_neutral,
				R.string.alert_no, neutralIntent != null);
	}

	public IntentAlertDialogBuilder(final Context context, final Intent okIntent, final Intent neutralIntent,
			final Intent cancelIntent,
			final Integer message_key, final boolean showNeutralButton) {
		this(context, okIntent, neutralIntent, cancelIntent, message_key, R.string.alert_yes,
				R.string.alert_neutral, R.string.alert_no, showNeutralButton);
	}

	public IntentAlertDialogBuilder(final Context context, final Intent okIntent, final Intent neutralIntent,
			final Intent cancelIntent,
			final Integer message_key, final Integer neutral_pos_key) {
		this(context, okIntent, neutralIntent, cancelIntent, message_key, R.string.alert_yes,
				neutral_pos_key, R.string.alert_no, true);
	}

	public IntentAlertDialogBuilder(final Context context, final Intent okIntent, final Intent neutralIntent,
			final Intent cancelIntent, final Integer message_key, final Integer pos_button_key,
			final Integer neutral_button_key, final Integer neg_button_key, final boolean showNeutralButton) {

		super(context);
		this.okIntent = okIntent;
		this.cancelIntent = cancelIntent;
		this.neutralIntent = neutralIntent;

		this.setMessage(context.getString(message_key));
		setCancelable(false);
		this.setPositiveButton(context.getString(pos_button_key),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						IntentAlertDialogBuilder.this.positiveButtonAction(context);
					}
				});
		if (showNeutralButton) {
			this.setNeutralButton(context.getString(neutral_button_key),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(final DialogInterface dialog, final int id) {
							IntentAlertDialogBuilder.this.neutralButtonAction(context);
						}
					});
		}
		this.setNegativeButton(context.getString(neg_button_key),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						IntentAlertDialogBuilder.this.negativeButtonAction(dialog, context);
					}
				});
	}

	protected void positiveButtonAction(final Context context) {
		if (okIntent != null) {
			context.startActivity(okIntent);
		}
	}

	protected void neutralButtonAction(final Context context) {
		if (neutralIntent != null) {
			context.startActivity(neutralIntent);
		}
	}

	protected void negativeButtonAction(final DialogInterface dialog, final Context context) {
		if (cancelIntent != null) {
			context.startActivity(cancelIntent);
		}
		dialog.cancel();
	}

	public void showAlertDialog() {
		final AlertDialog alert = create();
		alert.show();
	}
}