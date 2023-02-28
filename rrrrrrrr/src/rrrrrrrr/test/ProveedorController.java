package rrrrrrrr.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import rrrrrrrr.bean.ProveedorBean;
@ManagedBean
@SessionScoped
public class ProveedorController {
	
	private ProveedorDao proveedorDao;
	private List<ProveedorBean> proveedorBeans;
	private List<ProveedorBean> proveedorBeansb;
	private List<ProveedorBean> list;
	
	public ProveedorController() throws Exception {
		proveedorBeans = new ArrayList<>();
		
		proveedorDao = proveedorDao.getInstance();
	}
	
	public List<ProveedorBean> getProveedorBeans() {
		return proveedorBeans;
	}
	
	public List<ProveedorBean> getProveedorBeansb() {
		return proveedorBeansb;
	}
	
	public String agregarProveedor(ProveedorBean proveedor) {

		

		try {
			
			proveedorDao.agregarProveedor(proveedor);
			
		} catch (Exception exc) {
			

			return null;
		}
		
		return "listaProveedores?faces-redirect=true";
	}
	
	public void loadProveedores() {

		
		proveedorBeans.clear();

		try {
			
			proveedorBeans = proveedorDao.getProveedores();
			
		} catch (Exception exc) {
			
		}
	}
	
public String loadProveedor(int proveedorId) {
		
		
		
		try {
			ProveedorBean proveedor = proveedorDao.getProveedor(proveedorId);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("proveedorBean", proveedor);	
			
		} catch (Exception exc) {
			
			
			return null;
		}
				
		return "actualizarproveedor.xhtml";
	}	
	
	public String updateProveedor(ProveedorBean Proveedor) {

		
		try {
			
			proveedorDao.updateProveedor(Proveedor);
			
		} catch (Exception exc) {
		
			
			return null;
		}
		
		return "listaProveedores?faces-redirect=true";		
	}
	
	public String deleteProveedor(int proveedorId) {

		System.out.println(proveedorId);
		try {

			proveedorDao.deleteProveedor(proveedorId);
			
		} catch (Exception exc) {
			
			System.out.println(exc);
			
			return null;
		}
		
		return "listaProveedores";	
	}	
	
	public String buscarProveedor(String busqueda) {

		System.out.println(busqueda);
		try {

			List<ProveedorBean> lista=new ArrayList<>();
			for(ProveedorBean proveedor:proveedorBeans) {
			
				if(proveedor.getNombredelproveedor().contains(busqueda))
				{
					lista.add(proveedor);
				}
				
			}
			if(1<=lista.size() ) 
			proveedorBeansb=lista;
			
		} catch (Exception exc) {
			
			System.out.println(exc);
			
		}
		return "listaProveedoresbusqueda?faces-redirect=true";
			
	}	
}
