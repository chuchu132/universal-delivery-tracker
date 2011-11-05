package ar.com.udt.utils;

import java.util.ArrayList;

import ar.com.udt.Coordenadas;
import ar.com.udt.model.Persona;

public class DataHelper {

	private static DataHelper INSTANCE = null;
	
	private DataHelper(){
		
	};
	
	static public DataHelper getInstance(){
		if(INSTANCE == null){
			INSTANCE =  new DataHelper();
		}
		return
		INSTANCE;
	}
	
	public  ArrayList<Persona> getFamiliaresByClentId(String id){
		ArrayList<Persona> pipol =  new ArrayList<Persona>();
		Persona tmp = new Persona(1,"chuchu132", new Coordenadas(-34.584747,-58.400059), "http://graph.facebook.com/chuchu132/picture?type=square", "Soy Ale");
		pipol.add(tmp);
		tmp = new Persona(7,"clauchis", new Coordenadas(-34.586337,-58.401947), "http://graph.facebook.com/nimda/picture?type=square", "Soy Clauchis");
		pipol.add(tmp);
		tmp = new Persona(3,"clau", new Coordenadas(-34.585418,-58.416109), "http://graph.facebook.com/claudia.korzeniewski/picture?type=square", "Soy Clau");
		pipol.add(tmp);
		tmp = new Persona(6,"leo", new Coordenadas(-34.593897,-58.414521), "http://graph.facebook.com/leo.decaria/picture?type=square", "Soy Leito de los wachiturros");
		pipol.add(tmp);
		return pipol;
	}
	
}
