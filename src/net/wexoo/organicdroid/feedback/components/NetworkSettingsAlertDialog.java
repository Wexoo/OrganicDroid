package net.wexoo.organicdroid.feedback.components;

import net.wexoo.organicdroid.R;
import net.wexoo.organicdroid.feedback.IntentAlertDialogBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NetworkSettingsAlertDialog extends IntentAlertDialogBuilder {
	
	public NetworkSettingsAlertDialog(Context context) {
		this(context, R.string.alert_no_local_data);
	}
	
	public NetworkSettingsAlertDialog(Context context, final Integer message_key) {
		super(context, createMobileNetworkIntent(), createWiFiIntent(), null, message_key,
					R.string.alert_mobile_network_settings, R.string.alert_wifi_settings, R.string.alert_return, true);
	}
	
	private static Intent createMobileNetworkIntent() {
		Intent mobileNetworkIntent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
		if (Build.VERSION.SDK_INT < 16)
			mobileNetworkIntent.setClassName("com.android.phone", "com.android.phone.Settings");
		return mobileNetworkIntent;
	}
	
	private static Intent createWiFiIntent() {
		return new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
	}
}
