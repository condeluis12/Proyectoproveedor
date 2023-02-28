package rrrrrrrr.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import rrrrrrrr.bean.ProveedorBean;

public class ProveedorDao {
	private static ProveedorDao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/rrrrrrrr";
	public static ProveedorDao getInstance() throws Exception {
		if (instance == null) {
			instance = new ProveedorDao();
		}
		
		return instance;
	}
	
	private ProveedorDao() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	public List<ProveedorBean> getProveedores() throws Exception {

		List<ProveedorBean> proveedorBeans = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from proveedor order by nombredelproveedor";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				
				int id = myRs.getInt("numerodeidentificacion");
				String nombredelproveedor = myRs.getString("nombredelproveedor");
				String email = myRs.getString("correoelectronico");
				String direccion = myRs.getString("direccion");
				int cantidad = myRs.getInt("cantidadvehiculos");
				ProveedorBean tempProveedor = new ProveedorBean(id, cantidad, email,direccion,
						nombredelproveedor);

				proveedorBeans.add(tempProveedor);
			}
			
			return proveedorBeans;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public   void agregarProveedor(ProveedorBean proveedor) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into proveedor(Nombredelproveedor,direccion,correoelectronico,cantidadvehiculos) values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, proveedor.getNombredelproveedor());
			myStmt.setString(2, proveedor.getDireccion());
			myStmt.setString(3, proveedor.getCorreoelectronico());
			myStmt.setInt(4, proveedor.getCantidadvehiculos());
			System.out.println("resultadoooooooooooooooo"+proveedor.getDireccion());
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
	}
	public ProveedorBean getProveedor(int proveedorId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from proveedor where Numerodeidentificacion=?";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, proveedorId);
			
			myRs = myStmt.executeQuery();

			ProveedorBean proveedor = null;
			
			// retrieve data from result set row
			if (myRs.next()) {

				int id = myRs.getInt("numerodeidentificacion");
				String Nombredelproveedor = myRs.getString("Nombredelproveedor");
				String direccion = myRs.getString("direccion");
				String correoelectronico = myRs.getString("correoelectronico");
				int numerocarros =myRs.getInt("cantidadvehiculos");
				proveedor = new ProveedorBean(id, numerocarros,correoelectronico, direccion,
						Nombredelproveedor);
			}
			else {
				throw new Exception("Could not find student id: " + proveedorId);
			}

			return proveedor;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateProveedor(ProveedorBean proveedor) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();
			
			String sql = "update proveedor "
						+ " set Nombredelproveedor=?, correoelectronico=?, direccion=?,cantidadvehiculos=?"
						+ " where numerodeidentificacion=?";

			myStmt = myConn.prepareStatement(sql);

		
			myStmt.setString(1, proveedor.getNombredelproveedor());
			myStmt.setString(2, proveedor.getCorreoelectronico());
			myStmt.setString(3, proveedor.getDireccion());
			myStmt.setInt(4, proveedor.getCantidadvehiculos());
			myStmt.setInt(5, proveedor.getNumerodeidentificacion());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteProveedor(int proveedorId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from proveedor where numerodeidentificacion=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, proveedorId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
		
		private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

			try {
				if (theRs != null) {
					theRs.close();
				}

				if (theStmt != null) {
					theStmt.close();
				}

				if (theConn != null) {
					theConn.close();
				}
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}	
		
	}


