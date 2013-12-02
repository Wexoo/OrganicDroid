package net.wexoo.organicdroid.data;

import android.database.Cursor;

public interface IAutoCompleteCursorProxy {
	
	/** returns cursor with terms to be used in AutoCompleteTextView */
	public abstract Cursor getAutoCompleteCursor(String constraint);
}
