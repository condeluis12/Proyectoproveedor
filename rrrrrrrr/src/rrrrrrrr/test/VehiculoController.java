package rrrrrrrr.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import rrrrrrrr.bean.VehiculoBean;
@ManagedBean
@SessionScoped
public class VehiculoController {
	private VehiculoDao vehiculoDao;
	private List<VehiculoBean> vehiculoBeans;
	private List<VehiculoBean> vehiculoBeansb;
	private List<VehiculoBean> list;
	
	public VehiculoController() throws Exception {
		vehiculoBeans = new ArrayList<>();
		
		vehiculoDao = vehiculoDao.getInstance();
	}
	
	public List<VehiculoBean> getVehiculoBeans() {
		return vehiculoBeans;
	}
	
	public List<VehiculoBean> getVehiculoBeansb() {
		return vehiculoBeansb;
	}
	
	public String agregarVehiculo(VehiculoBean vehiculo) {

		

		try {
			
		
			vehiculoDao.agregarVehiculo(vehiculo);
			
		} catch (Exception exc) {
		

			return null;
		}
		
		return "listaVehiculos?faces-redirect=true";
	}
	
	public void loadVehiculos() {

		
		
		vehiculoBeans.clear();

		try {
			
			vehiculoBeans = vehiculoDao.getVehiculos();
			
		} catch (Exception exc) {
			
		}
	}
	
public String loadVehiculo(int vehiculoId) {
		
		
	
		try {
			VehiculoBean vehiculo = vehiculoDao.getVehiculo(vehiculoId);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("vehiculoBean", vehiculo);	
			
		} catch (Exception exc) {
			
			
			return null;
		}
				
		return "actualizarvehiculo.xhtml";
	}	
	
	public String updateVehiculo(VehiculoBean vehiculo) {

		
		try {
			
			vehiculoDao.updateVehiculo(vehiculo);
			System.out.println("exito");
			VehiculoBean vehiculoVacio=null;
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("vehiculoBean", vehiculoVacio);	
		} catch (Exception exc) {
			
			return null;
		}
		
		return "listaVehiculos?faces-redirect=true";		
	}
	
	public String deleteVehiculo(int vehiculoId) {
		try {

			vehiculoDao.deleteVehiculo(vehiculoId);
			
		} catch (Exception exc) {
			
			System.out.println(exc);
			
			
			return null;
		}
		
		return "listaVehiculos";	
	}	
	
	public String buscarVehiculo(String busqueda) {
		
		System.out.println(busqueda);
		try {

			List<VehiculoBean> lista=new ArrayList<>();
			for(VehiculoBean vehiculo:vehiculoBeans) {
			boolean contiene=vehiculo.getPlacadelvehiculo().contains(busqueda);
				if(contiene==true)
				{
					System.out.println(contiene);
					lista.add(vehiculo);
				}
				
			}
			if(1<=lista.size() ) 
			vehiculoBeansb=lista;
			/*if(1<=lista.size() ) {
				System.out.println("si");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("vehiculoBeansb", lista);	
			}*/
		} catch (Exception exc) {
			
			System.out.println(exc);
			
		
		}
		return "listaVehiculosbusqueda?faces-redirect=true";
			
	}	
}
