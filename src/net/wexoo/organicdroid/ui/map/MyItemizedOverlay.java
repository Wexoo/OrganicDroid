package net.wexoo.organicdroid.ui.map;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * MyItemizedOverlay.java
 * 
 * @author wexoo
 */
public abstract class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {
	
	protected final Context c;
	private final ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public MyItemizedOverlay(final Drawable defaultMarker, final MapView mapView) {
		super(ItemizedOverlay.boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
	}
	
	public void addOverlay(final OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(final int i) {
		return mOverlays.get(i);
	}
	
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onBalloonTap(final int index, final OverlayItem item) {
		showContextMenu(index);
		return true;
	}
	
	protected abstract void showContextMenu(int index);
}