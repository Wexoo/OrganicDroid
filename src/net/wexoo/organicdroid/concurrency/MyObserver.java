package net.wexoo.organicdroid.concurrency;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public class MyObserver extends ContentObserver {
	
	public MyObserver(Handler handler) {
		super(handler);
	}
	
	@Override
	public void onChange(boolean selfChange) {
		this.onChange(selfChange, null);
	}
	
	@Override
	public void onChange(boolean selfChange, Uri uri) {
		// do s.th. depending on the handler you might be on the UI thread, so be cautious!
		
		// URI to listen to, only listen to exact or sub-uris also, implementation of observer
		// getContentResolver().registerContentObserver(
		// SOME_URI, true, yourObserver);
	}
}