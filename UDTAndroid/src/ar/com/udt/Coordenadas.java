package ar.com.udt;


public class Coordenadas {

	private double longitud;
	private double latitud;
	
	public Coordenadas(double lati, double longi){
		this.latitud = lati;
		this.longitud = longi;
	}

	Coordenadas(){
		this.latitud = 0;
		this.longitud = 0;
	}
	
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLatitud() {
		return latitud;
	}
	
	public String toString(){
		return new String("Latitud:"+latitud+" Longitud:"+longitud);
	}
}
