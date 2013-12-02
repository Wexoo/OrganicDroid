package net.wexoo.organicdroid.ui.map;

import java.util.ArrayList;

import net.wexoo.organicdroid.R;
import android.content.Context;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * MapsItemizedOverlay.java
 * 
 * @author wexoo
 */
public class MapsItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	private final ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	private final Context mContext;
	
	public MapsItemizedOverlay(final Context context) {
		super(ItemizedOverlay.boundCenterBottom(context.getResources().getDrawable(R.drawable.marker)));
		mContext = context;
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
	protected boolean onTap(final int index) {
		final OverlayItem item = mOverlays.get(index);
		
		// Intent i = new Intent(Intent.ACTION_VIEW,
		// Uri.parse("google.navigation:q=" + item.getPoint().getLatitudeE6() /
		// 1E6 + ","
		// + item.getPoint().getLongitudeE6() / 1E6));
		// mContext.startActivity(i);
		
		Toast.makeText(mContext, item.getTitle() + "\n" + item.getSnippet(), Toast.LENGTH_SHORT).show();
		
		// AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		// dialog.setTitle(item.getTitle());
		// dialog.setMessage(item.getSnippet());
		// dialog.show();
		return true;
	}
	
	/**
	 * Provides Google Maps Information in a Toast Message when finger is lifted from touch screen
	 */
	//
	// public boolean onTouchEvent(MotionEvent event, MapView mapView) {
	// // ---when user lifts his finger---
	// if (event.getAction() == MotionEvent.ACTION_UP) {
	// GeoPoint p = mapView.getProjection().fromPixels(
	// (int) event.getX(),
	// (int) event.getY());
	//
	// Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
	// try {
	// List<Address> addresses = geoCoder.getFromLocation(
	// p.getLatitudeE6() / 1E6,
	// p.getLongitudeE6() / 1E6, 1);
	//
	// String add = "";
	// if (addresses.size() > 0) {
	// for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++) {
	// add += addresses.get(0).getAddressLine(i) + "\n";
	// }
	// }
	//
	// // Toast.makeText(mContext, add, Toast.LENGTH_SHORT).show();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return true;
	// } else {
	// return false;
	// }
	// }
}