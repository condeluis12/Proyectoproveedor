package rrrrrrrr.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean  // Using ManagedBean annotation  
@RequestScoped  // Using Scope annotation  
public class ProveedorBean {
	
	private int numerodeidentificacion;

	private int cantidadvehiculos;
	
	private String correoelectronico;

	private String direccion;

	private String nombredelproveedor;

	
	public ProveedorBean() {
		super();
	}

	public ProveedorBean(int numerodeidentificacion, int cantidadvehiculos, String correoelectronico, String direccion,
			String nombredelproveedor) {
		super();
		this.numerodeidentificacion = numerodeidentificacion;
		this.cantidadvehiculos = cantidadvehiculos;
		this.correoelectronico = correoelectronico;
		this.direccion = direccion;
		this.nombredelproveedor = nombredelproveedor;
	}

	public int getNumerodeidentificacion() {
		return numerodeidentificacion;
	}

	public void setNumerodeidentificacion(int numerodeidentificacion) {
		this.numerodeidentificacion = numerodeidentificacion;
	}

	public int getCantidadvehiculos() {
		return cantidadvehiculos;
	}

	public void setCantidadvehiculos(int cantidadvehiculos) {
		this.cantidadvehiculos = cantidadvehiculos;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombredelproveedor() {
		return nombredelproveedor;
	}

	public void setNombredelproveedor(String nombredelproveedor) {
		this.nombredelproveedor = nombredelproveedor;
	}

	@Override
	public String toString() {
		return "ProveedorBean [numerodeidentificacion=" + numerodeidentificacion + ", cantidadvehiculos="
				+ cantidadvehiculos + ", correoelectronico=" + correoelectronico + ", direccion=" + direccion
				+ ", nombredelproveedor=" + nombredelproveedor + "]";
	}
	
	
}
