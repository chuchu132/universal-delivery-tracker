package ar.com.udt.model;

import ar.com.udt.Coordenadas;

public class Persona {

	private long id ;
	private String username = "";
	private Coordenadas posicion ;
	private String descripcion= "";
	private String urlImage = "";
	
	public Persona(long i, String un,Coordenadas p,String url,String desc) {
		id = i;
		username = un;
		posicion = p;
		urlImage = url;
		descripcion = desc;		
	}
	
	public String toString(){
		return username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Coordenadas getPosicion() {
		return posicion;
	}

	public void setPosicion(Coordenadas posicion) {
		this.posicion = posicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public long getId() {
		return id;
	}

}
