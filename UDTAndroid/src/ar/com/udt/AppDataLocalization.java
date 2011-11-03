package ar.com.udt;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class AppDataLocalization extends Application implements
		LocationListener {

	private Coordenadas coordenadas;
	private Coordenadas posiblesCoordenadas;
	private Coordenadas coordenadasAutomViejas;
	LocationManager locationManager;
	private boolean automatico;

	public Coordenadas getPosiblesCoordenadas() {
		return posiblesCoordenadas;
	}

	private void setPosiblesCoordenadas(Coordenadas coordenadas) {
		this.posiblesCoordenadas = coordenadas;
	}

	public Coordenadas getCoordenadas() {
		if (automatico) {
			/* actualizo las coordenadas */
			posibleConfiguracionAutomatica();
			confirmConfiguracionAutomatica();
		}
		return coordenadas;
	}

	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void onCreate() {
		super.onCreate();
		coordenadasAutomViejas = new Coordenadas(-34.603789, -58.381519);
		posibleConfiguracionAutomatica();
		confirmConfiguracionAutomatica();
	}


	public void posibleConfiguracionAutomatica() {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		/*
		 * You can use the getBetProvider method to define minimum requirements
		 * and let Android pick the best technique available. The key criteria
		 * is the accuracy; using the G1, this is going to decide between GPS
		 * and Network location.
		 */

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);

		// Location location =
		// locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
		if (location != null) {
			pudoConectarse(location);
		} else {
			obtenerDireccionDefault();
		}

	}

	private void pudoConectarse(Location location) {
		System.out
				.println("-------------[app]--------------se conectó ---------------------");

		setPosiblesCoordenadas(new Coordenadas(location.getLatitude(),
				location.getLongitude()));
		coordenadasAutomViejas = posiblesCoordenadas;

	}

	private void obtenerDireccionDefault() {
		System.out.println("-----------[app]----------------couldn't get provider [se centra en last known location]-----------------------");
		setPosiblesCoordenadas(this.coordenadasAutomViejas);

	}

	public void confirmConfiguracionAutomatica() {
		automatico = true;
		setCoordenadas(this.posiblesCoordenadas);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	protected void onResume() {
		if (locationManager != null) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 1000, 50, this);
		}
	}

	public void onLocationChanged(Location location) {
		if (automatico) {
			/* actualizo las coordenadas */
			posibleConfiguracionAutomatica();
			confirmConfiguracionAutomatica();
		}
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
