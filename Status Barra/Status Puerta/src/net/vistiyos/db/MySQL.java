
package net.vistiyos.db;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.vistiyos.config.Configuration;
import net.vistiyos.util.Serialize;



public class MySQL {
    
    private static Connection conexion;
    
    private static void getConexion(){
    	if(conexion == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conexion = (Connection) DriverManager.getConnection("jdbc:mysql://"+Configuration.getValue("server")+"/"+Configuration.getValue("database"), Configuration.getValue("user") , Configuration.getValue("password"));
			} catch (Exception e){
				e.printStackTrace();
			}
		}
    }
    
    public static boolean isTurnoActivo(){
    	getConexion();
    	try{
    		String sql = "SELECT count(*) as contador FROM turnos WHERE activo=1";
    		ResultSet resultado = conexion.createStatement().executeQuery(sql);
    		if(resultado.next()){
    			if(resultado.getInt("contador") == 1){
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}catch(SQLException ex){
    		System.err.println(ex.getMessage());
    		return false;
    	}
    }
    
    public static int getIdTurnoActivo(){
    	getConexion();
    	try{
    		String sql = "SELECT idturno FROM turnos WHERE activo=1";
    		ResultSet resultado = conexion.createStatement().executeQuery(sql);
    		if(resultado.next()){
    			return resultado.getInt(1);
    		}
    		return 0;
    	}catch(SQLException ex){
    		System.err.println(ex.getMessage());
    		return 0;
    	}
    }
    
    public static ArrayList<String> getEntradas(){
    	getConexion();
		ArrayList<String> datos = new ArrayList<String>();
		try{
			String sql = "SELECT nombre FROM asignacion_entradas_turnos WHERE idturno="+getIdTurnoActivo();
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			while(resultado.next()){
				datos.add(resultado.getString(1));
			}
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return datos;
    }
    
    public static int insertarNuevaEntrada(String entrada){
    	getConexion();
		try{
			String sql = "SELECT idasignacion,precio FROM asignacion_entradas_turnos WHERE idturno="+getIdTurnoActivo()+" AND nombre LIKE '"+entrada+"'";
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			if(resultado.next()){
				sql = "INSERT INTO tickets (idasignacion) VALUES ("+resultado.getInt(1)+")";
				conexion.createStatement().execute(sql);
				sql = "SELECT idticket FROM tickets ORDER BY idticket DESC LIMIT 1";
				ResultSet resultado2 = conexion.createStatement().executeQuery(sql);
				if(resultado2.next()){
					int id = resultado2.getInt(1);
					sql = "INSERT INTO entradas_auxiliar (idticket,precio) VALUES ("+id+","+resultado.getFloat(2)+")";
					conexion.createStatement().execute(sql);
					return id;
				}
				return -1;
			}
			return -1;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return -1;
		}
    }
    
    public static float getPrecioEntrada(String entrada){
    	getConexion();
		try{
			String sql = "SELECT precio FROM asignacion_entradas_turnos WHERE idturno="+getIdTurnoActivo()+" AND nombre LIKE '"+entrada+"'";
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			if(resultado.next()){
				return resultado.getFloat(1);
			}
			return -1;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return -1;
		}
    }
    
    public static void inicializarBD(){
    	getConexion();
		try{
			String sql = "TRUNCATE pago_metalico";
			conexion.createStatement().execute(sql);
			sql = "TRUNCATE pago_tarjeta";
			conexion.createStatement().execute(sql);
			sql = "TRUNCATE invitaciones";
			conexion.createStatement().execute(sql);
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		} 
    }
    
    public static void insertarPagoTarjeta(){
    	getConexion();
		try{
			String sql = "SELECT * FROM entradas_auxiliar";
			ArrayList<String> entradas = new ArrayList<String>();
			float precio = 0;
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			while(resultado.next()){
				entradas.add(resultado.getString("idticket"));
				precio+=resultado.getFloat("precio");
			}
			byte[] array = Serialize.SerializeArrayList(entradas);
			sql = "INSERT INTO pago_tarjeta (lista_id,cantidad_pago) VALUES(?,?)";
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setBinaryStream(1,new ByteArrayInputStream(array),array.length);
			pstmt.setFloat(2, precio);
			pstmt.execute();
			pstmt.close();
			sql = "TRUNCATE entradas_auxiliar";
			conexion.createStatement().execute(sql);
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}   	
    }
    
    public static void insertarPagoMetalico(){
    	getConexion();
		try{
			String sql = "SELECT * FROM entradas_auxiliar";
			ArrayList<String> entradas = new ArrayList<String>();
			float precio = 0;
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			while(resultado.next()){
				entradas.add(resultado.getString("idticket"));
				precio+=resultado.getFloat("precio");
			}
			byte[] array = Serialize.SerializeArrayList(entradas);
			sql = "INSERT INTO pago_metalico (lista_id,cantidad_pago) VALUES(?,?)";
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setBinaryStream(1,new ByteArrayInputStream(array),array.length);
			pstmt.setFloat(2, precio);
			pstmt.execute();
			pstmt.close();
			sql = "TRUNCATE entradas_auxiliar";
			conexion.createStatement().execute(sql);
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}   	
    }

	public static int getIdBebida(int idEntrada) {
		getConexion();
		try{
			String sql = "SELECT asignacion_entradas_turnos.idbebida FROM tickets,asignacion_entradas_turnos WHERE asignacion_entradas_turnos.idasignacion=tickets.idasignacion AND tickets.idticket="+idEntrada;
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			if(resultado.next()){
				return resultado.getInt(1);
			}
			return -1;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return -1;
		}
	}

	public static int getMaxTalonario() {
		getConexion();
		try{
			String sql = "SELECT valor FROM configuracion WHERE parametro='Maximo Talonario'";
			ResultSet resultado = conexion.createStatement().executeQuery(sql);
			if(resultado.next()){
				return resultado.getInt(1);
			}
			return -1;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
			return -1;
		}
	}
	
	public static boolean isInvitacion(){
		getConexion();
    	try{
    		String sql = "SELECT invitaciones FROM turnos WHERE activo=1";
    		ResultSet resultado = conexion.createStatement().executeQuery(sql);
    		if(resultado.next()){
    			if(resultado.getInt(1) == 1){
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}catch(SQLException ex){
    		System.err.println(ex.getMessage());
    		return false;
    	}
	}
	
	public static boolean isTalonario(){
		getConexion();
    	try{
    		String sql = "SELECT talonario FROM turnos WHERE activo=1";
    		ResultSet resultado = conexion.createStatement().executeQuery(sql);
    		if(resultado.next()){
    			if(resultado.getInt(1) == 1){
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}catch(SQLException ex){
    		System.err.println(ex.getMessage());
    		return false;
    	}
	}
	
	public static boolean isCanjeo(){
		getConexion();
    	try{
    		String sql = "SELECT canjeo FROM turnos WHERE activo=1";
    		ResultSet resultado = conexion.createStatement().executeQuery(sql);
    		if(resultado.next()){
    			if(resultado.getInt(1) == 1){
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}catch(SQLException ex){
    		System.err.println(ex.getMessage());
    		return false;
    	}
	}
}
