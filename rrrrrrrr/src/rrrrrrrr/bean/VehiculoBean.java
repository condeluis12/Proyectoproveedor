package rrrrrrrr.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean   // Using ManagedBean annotation  
@RequestScoped  // Using Scope annotation
public class VehiculoBean {
	private int numeroidentificacionelconductor;
	
	private String marca;

	private String modelo;

	private String nombredelconductor;

	private String placadelvehiculo;

	private int idProveedor;
	
	private int estado;
	
	public VehiculoBean() {
		super();
	}

	public VehiculoBean(int numeroidentificacionelconductor, String marca, String modelo, String nombredelconductor,
			String placadelvehiculo, int idProveedor, int estado) {
		super();
		this.numeroidentificacionelconductor = numeroidentificacionelconductor;
		this.marca = marca;
		this.modelo = modelo;
		this.nombredelconductor = nombredelconductor;
		this.placadelvehiculo = placadelvehiculo;
		this.idProveedor = idProveedor;
		this.estado = estado;
	}

	public int getNumeroidentificacionelconductor() {
		return numeroidentificacionelconductor;
	}

	public void setNumeroidentificacionelconductor(int numeroidentificacionelconductor) {
		this.numeroidentificacionelconductor = numeroidentificacionelconductor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombredelconductor() {
		return nombredelconductor;
	}

	public void setNombredelconductor(String nombredelconductor) {
		this.nombredelconductor = nombredelconductor;
	}

	public String getPlacadelvehiculo() {
		return placadelvehiculo;
	}

	public void setPlacadelvehiculo(String placadelvehiculo) {
		this.placadelvehiculo = placadelvehiculo;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
}
