package ar.com.udt;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TrackFamilyActivity  extends MapActivity implements LocationListener{

	MapView mapView;
	MapController mapController;
	private LocationManager locationManager;
	GeoPoint myLocation = null;
	GeoPoint gPoint = null;

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.trackfamily);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		GeoPoint point =  new GeoPoint(-33632078,-58372829);
		mapController.setCenter(point);
		mapController.setZoom(18);

		
	}
	
	
	
	protected void onResume() {
		super.onResume();
			if (locationManager != null) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 1000, 50, this);
			}
	}
	
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	public void onLocationChanged(Location location) {
		AppDataLocalization appState = ((AppDataLocalization) getApplicationContext());
		GeoPoint myLocation = new GeoPoint((int) (appState.getCoordenadas()
				.getLatitud() * 1E6), (int) (appState.getCoordenadas()
				.getLongitud() * 1E6));
		mapController.animateTo(myLocation);
		gPoint = myLocation;
		MyLocationOverlay touch = new MyLocationOverlay();
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(touch);
	}
	
	
		


	
	class MyLocationOverlay extends com.google.android.maps.Overlay {
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {

			super.draw(canvas, mapView, shadow);
			Paint paint = new Paint();
			// Converts lat/lng-Point to OUR coordinates on the screen.
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(gPoint, myScreenCoords);
			paint.setStrokeWidth(1);
			paint.setARGB(255, 255, 255, 255);
			paint.setStyle(Paint.Style.STROKE);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.bluepushpin);
			canvas.drawBitmap(bmp, myScreenCoords.x -bmp.getWidth()/2, myScreenCoords.y - bmp.getHeight(), paint);
			
			return true;
		}

	}
	

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {

	}
	
	

}
