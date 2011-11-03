package ar.com.udt;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
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

public class DondeEstoyActivity  extends MapActivity implements LocationListener{

	MapView mapView;
	MapController mapController;
	private LocationManager locationManager;
	GeoPoint myLocation = null;
	Timer timer;
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.dondeestoy);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		GeoPoint point =  new GeoPoint(-33632078,-58372829);
		mapController.setCenter(point);
		mapController.setZoom(18);
		timer =new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if(myLocation != null){
					mapController.animateTo(myLocation);
				}
				
			}
		}, 10000,5000);
		
	}
	
	
	
	protected void onResume() {
		super.onResume();
			if (locationManager != null) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 1000, 200, this);
			}
	}
	
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void onLocationChanged(Location location) {
		myLocation = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));	
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
	
	

}
