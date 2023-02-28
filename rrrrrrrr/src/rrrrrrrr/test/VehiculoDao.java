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
import rrrrrrrr.bean.VehiculoBean;

public class VehiculoDao {
	private static VehiculoDao instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/rrrrrrrr";
	public static VehiculoDao getInstance() throws Exception {
		if (instance == null) {
			instance = new VehiculoDao();
		}
		
		return instance;
	}
	
	private VehiculoDao() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	public List<VehiculoBean> getVehiculos() throws Exception {

		List<VehiculoBean> vehiculoBeans = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from vehiculo order by nombredelconductor";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("numeroidentificacionelconductor");
				String marca = myRs.getString("marca");
				String modelo = myRs.getString("modelo");
				String nombredelconductor = myRs.getString("nombredelconductor");
				String placadelvehiculo = myRs.getString("placadelvehiculo");
				int Idproveedor = myRs.getInt("Idproveedor");
				int estado = myRs.getInt("estado");
				// create new student object
				
				VehiculoBean tempVehiculo = new VehiculoBean(id,marca,modelo,nombredelconductor,
						placadelvehiculo,Idproveedor,estado);

				vehiculoBeans.add(tempVehiculo);
			}
			
			return vehiculoBeans;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public   void agregarVehiculo(VehiculoBean vehiculo) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into vehiculo(marca,modelo,nombredelconductor,placadelvehiculo,Idproveedor,estado) values (?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, vehiculo.getMarca());
			myStmt.setString(2, vehiculo.getModelo());
			myStmt.setString(3, vehiculo.getNombredelconductor());
			myStmt.setString(4, vehiculo.getPlacadelvehiculo());
			myStmt.setInt(5, vehiculo.getIdProveedor());
			myStmt.setInt(6, vehiculo.getEstado());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
	}
	public VehiculoBean getVehiculo(int vehiculoId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from vehiculo where numeroidentificacionelconductor=?";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, vehiculoId);
			
			myRs = myStmt.executeQuery();

			VehiculoBean vehiculo = null;
			
			if (myRs.next()) {

				int id = myRs.getInt("numeroidentificacionelconductor");
				String marca = myRs.getString("marca");
				String modelo = myRs.getString("modelo");
				String nombredelconductor = myRs.getString("nombredelconductor");
				String placadelvehiculo = myRs.getString("placadelvehiculo");
				int idproveedor = myRs.getInt("Idproveedor");
				int estado = myRs.getInt("estado");
				
				// create new student object
				
				vehiculo = new VehiculoBean(id,marca,modelo,nombredelconductor,
						placadelvehiculo,idproveedor,estado);
			}
			else {
				throw new Exception("Could not find student id: " + vehiculoId);
			}

			return vehiculo;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateVehiculo(VehiculoBean vehiculo) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update vehiculo "
						+ " set marca=?, modelo=?,nombredelconductor=?,placadelvehiculo=?,Idproveedor=?,estado=? where numeroidentificacionelconductor=?";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, vehiculo.getMarca());
			myStmt.setString(2, vehiculo.getModelo());
			myStmt.setString(3, vehiculo.getNombredelconductor());
			myStmt.setString(4, vehiculo.getPlacadelvehiculo());
			myStmt.setInt(5, vehiculo.getIdProveedor());
			myStmt.setInt(6, vehiculo.getEstado());
			myStmt.setInt(7, vehiculo.getNumeroidentificacionelconductor());
			
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteVehiculo(int vehiculoId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from vehiculo where numeroidentificacionelconductor=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, vehiculoId);
			
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
