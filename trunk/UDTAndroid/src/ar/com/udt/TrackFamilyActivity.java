package ar.com.udt;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import ar.com.udt.components.FamiliaresSpinnerAdapter;
import ar.com.udt.model.Persona;
import ar.com.udt.utils.DataHelper;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TrackFamilyActivity  extends MapActivity {

	MapView mapView;
	MapController mapController;
	ArrayList<Persona> familiares=null;
	ArrayList<Persona> visibles = null;
	Spinner spinnerFamilia;
	Overlay pinOverlay = null;

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.trackfamily);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		GeoPoint point =  new GeoPoint(-33632078,-58372829);
		mapController.setCenter(point);
		mapController.setZoom(18);
		
		cargarDatosFamilia();
		
	}

	private void cargarDatosFamilia(){
		familiares = DataHelper.getInstance().getFamiliaresByClentId("Sarasa");
		spinnerFamilia = (Spinner) findViewById(R.id.spinnerFamilia);
		FamiliaresSpinnerAdapter adapter = new FamiliaresSpinnerAdapter(this,familiares);
		spinnerFamilia.setAdapter(adapter);
		spinnerFamilia.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	Persona p = (Persona)spinnerFamilia.getSelectedItem();
				if(p != null){
					visibles = new ArrayList<Persona>();
					visibles.add(p);
					pintarMapa();
				}
		    }

		    public void onNothingSelected(AdapterView<?> parentView) {

		    }

		});
	}
	
	private void pintarMapa(){
		if(visibles != null){
			pinOverlay = new MyLocationOverlay();
			List<Overlay> overlays = mapView.getOverlays();
			overlays.clear();
			overlays.add(pinOverlay);
			Coordenadas c = visibles.get(0).getPosicion();
			GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6), (int) (c.getLongitud() * 1E6));
			mapController.animateTo(point);
		}
		
	} 
	
	
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	class MyLocationOverlay extends com.google.android.maps.Overlay {
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {

			super.draw(canvas, mapView, shadow);
			Paint paint = new Paint();
			paint.setStrokeWidth(1);
			paint.setARGB(255, 255, 255, 255);
			paint.setStyle(Paint.Style.STROKE);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.bluepushpin);
			if(TrackFamilyActivity.this.visibles != null){
				for(Persona p : TrackFamilyActivity.this.visibles){
					Coordenadas c = p.getPosicion();
					GeoPoint tmpLocation = new GeoPoint((int) (c.getLatitud() * 1E6), (int) (c.getLongitud() * 1E6));
					Point tmpScreenCoords = new Point();
					mapView.getProjection().toPixels(tmpLocation,tmpScreenCoords);
					canvas.drawBitmap(bmp, tmpScreenCoords.x -bmp.getWidth()/2, tmpScreenCoords.y - bmp.getHeight(), paint);
				}
			}
			
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
