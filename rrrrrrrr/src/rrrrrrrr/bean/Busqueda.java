package rrrrrrrr.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean   // Using ManagedBean annotation  
@RequestScoped  // Using Scope annotation
public class Busqueda {
	

	private String palabra;

	
	public Busqueda() {
		super();
	}
	public Busqueda(String palabra) {
		super();
		this.palabra = palabra;
	}
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public String toString() {
		return "Busqueda [palabra=" + palabra + "]";
	}
	
	
}
