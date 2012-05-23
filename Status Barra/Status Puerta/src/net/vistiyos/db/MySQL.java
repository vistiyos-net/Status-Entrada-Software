
package net.vistiyos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.vistiyos.config.Configuration;

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
    
    /*public static void increaseEntrance(int identrada,int idturno){
        getConexion();
        String sql="select count(*) from registro where identrada="+identrada+" and idturno="+idturno;
        ResultSet rs=SQL.executeQuery(sql, conexion);
        if(rs.next()){
            if(rs.getInt(1)==0){
                sql="insert into registro values ("+identrada+","+idturno+",1)";
                SQL.executeQuery(sql, conexion);
            }
            else{
                sql="update registro set numEntradas=numEntradas+1 where identrada="+identrada+" and idturno="+idturno;
                SQL.executeQuery(sql, conexion);
            }
        }
    }
    
    public static float getGains() throws SQLException, NoDriverFoundException, SQLSintaxError{
        getConexion();
        float ganancias=0;
        String sql="select identrada,idturno,numEntradas from registro";
        ResultSet rs=SQL.executeQuery(sql, conexion);
        ArrayList<Map<String,String>> res=SQL.parseSQLResultMap(rs);
        Iterator<Map<String,String>> it=res.iterator();
        while(it.hasNext()){
            Map<String,String> aux= it.next();
            int identrada=Integer.parseInt(aux.get("IDENTRADA"));
            int idturno=Integer.parseInt(aux.get("IDTURNO"));
            int numEntradas=Integer.parseInt(aux.get("NUMENTRADAS"));
            sql="select precio from turnosEntradas where identrada="+identrada+" and idturno="+idturno;
            rs=SQL.executeQuery(sql, conexion);
            if(rs.next()){
                float precio=rs.getFloat("PRECIO");
                ganancias+=(numEntradas*precio);
            }
        }
        return ganancias;
    }
    
    public static ArrayList<Map<String,String>> getEntradas() throws SQLException, NoDriverFoundException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select turnosEntradas.identrada,entradas.nombre from entradas,turnosEntradas where entradas.identrada=turnosEntradas.identrada and turnosEntradas.idturno="+getIdTurnoActual()+" order by turnosEntradas.identrada ASC", conexion);
        return SQL.parseSQLResultMap(rs);
    }
    
    public static int getIdTurnoActual() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="select valor from configuracion where parametro='turno'";
        ResultSet rs=SQL.executeQuery(sql,conexion);
        rs.next();
        return rs.getInt("VALOR");
    }
    
    public static ArrayList<Map<String,String>> getTurnos() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select idturnos,nombreturno from turnos", conexion);
        return SQL.parseSQLResultMap(rs);
    } 
    
    public static boolean actualizarTurno(int id) throws NoDriverFoundException{
        getConexion();
        try{
            SQL.executeQuery("update configuracion set valor='"+id+"' where parametro='turno'", conexion);
            return true;
        }catch(SQLSintaxError ex){
            return false;
        }catch(SQLException ex){
            return false;
        }
    }
    
    public static void resetearRegistro() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="delete from registro";
        SQL.executeQuery(sql, conexion);
        sql="delete from pagoTarjeta";
        SQL.executeQuery(sql, conexion);
        sql="delete from invitaciones";
        SQL.executeQuery(sql, conexion);
    }
    
    public static void anadirEntradaTarjeta(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        String sql="select count(*) from pagoTarjeta where identrada="+id+" and idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        if(rs.getInt(1)==0){
            sql="insert into pagoTarjeta values("+id+","+getIdTurnoActual()+",1)";
            SQL.executeQuery(sql, conexion);
        }
        else{
            sql="update pagoTarjeta set numEntradas=numEntradas+1 where identrada="+id+" and idturno="+getIdTurnoActual();
            SQL.executeQuery(sql, conexion);
        }
    }
    
    public static int getIdEntradaByName(String nombre) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="select identrada from entradas where nombre='"+nombre+"'";
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        return rs.getInt(1);
        
    }
    
    public static void addInvitacion(String nombre,int num) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="select count(*) from invitaciones where Nombre='"+nombre+"'";
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        if(rs.getInt(1)==0){
            sql="insert into invitaciones values ('"+nombre+"',"+num+")";
            SQL.executeQuery(sql, conexion);
        }
        else{
            sql="update invitacion set num=num+"+num+" where nombre='"+nombre+"'";
            SQL.executeQuery(sql, conexion);
        }   
    }
    
    public static int getNumInvitaciones() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="select count(*) from invitaciones";
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        return rs.getInt(1);
    }
    
    public static float getRegistro() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        float ganancias=0;
        String sql="select distinct registro.numEntradas,turnosEntradas.precio,registro.identrada from registro,turnosEntradas where registro.idturno=turnosEntradas.idturno and registro.identrada=turnosEntradas.identrada and registro.idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        while(rs.next()){
            ganancias+=rs.getInt(1)*rs.getFloat(2);
        }
        return ganancias;
    }

    public static float getTarjeta() throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        float ganancias=0;
        String sql="select distinct pagoTarjeta.numEntradas,turnosEntradas.precio,pagoTarjeta.identrada from pagoTarjeta,turnosEntradas where pagoTarjeta.idturno=turnosEntradas.idturno and pagoTarjeta.identrada=turnosEntradas.identrada and pagoTarjeta.idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        while(rs.next()){
            ganancias+=rs.getInt(1)*rs.getFloat(2);
        }
        return ganancias;
    }
    
    public static String getBebida(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        String sql="select nombreBebida from entradas where identrada="+id;
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        return rs.getString(1);
    }

    public static int getCopasNum(int id) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        String sql="select numBebidas from turnosEntradas where identrada="+id+" and idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        return rs.getInt(1);
    }
    
    public static Entry<String,String> getTicketPrecio(String nombre,int turno) throws NoDriverFoundException, SQLException, SQLSintaxError{
        int id=MySQL.getIdEntradaByName(nombre);
        String sql="select numbebidas,precio from turnosentradas where identrada="+id+" and idturno="+turno;
        ResultSet rs=SQL.executeQuery(sql, conexion);
        ArrayList<Map<String, String>> e=SQL.parseSQLResultMap(rs);
        Map<String,String> c=e.get(0);
        String ticket=c.get("NUMBEBIDAS");
        String precio=c.get("PRECIO");
        Map<String,String> aux=new HashMap<String, String>();
        aux.put(ticket, precio);
        return aux.entrySet().iterator().next();
        
    }
    
    public static ArrayList<Map<String,String>> getEntradas(int id) throws SQLException, NoDriverFoundException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select turnosEntradas.identrada,entradas.nombre from entradas,turnosEntradas where entradas.identrada=turnosEntradas.identrada and turnosEntradas.idturno="+id+" order by turnosEntradas.identrada ASC", conexion);
        return SQL.parseSQLResultMap(rs);
    }
    
    public static int getFontSize(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select tamano from impresiones where identrada="+id, conexion);
        rs.next();
        return rs.getInt(1);
    }
    
    public static int getXPosition(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select posx from impresiones where identrada="+id, conexion);
        rs.next();
        return rs.getInt(1);
    }
    
    public static int getYPosition(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        getConexion();
        ResultSet rs=SQL.executeQuery("select posy from impresiones where identrada="+id, conexion);
        rs.next();
        return rs.getInt(1);
    }

    public static float getCanjeo() throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        ResultSet rs=SQL.executeQuery("select valor from configuracion where parametro='canjeo'", conexion);
        rs.next();
        return rs.getFloat(1);
    }

    public static float getTalonario() throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        ResultSet rs=SQL.executeQuery("select valor from configuracion where parametro='talonario'", conexion);
        rs.next();
        return rs.getFloat(1);
    }

    public static int getIdTurnoByName(String nombre) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        String sql="select idturnos from turnos where nombreturno='"+nombre+"'";
        ResultSet rs=SQL.executeQuery(sql, conexion);
        rs.next();
        return rs.getInt(1);
    }

    public static void actualizarEntrada(int idTurno, int idEntrada, int ticket, float precio) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        String sql="update turnosentradas set precio="+precio+",numbebidas="+ticket+" where idturno="+idTurno+" and identrada="+idEntrada;
        SQL.executeQuery(sql, conexion);
    }

    public static void actualizarTalonario(float parseFloat) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        String sql="update configuracion set valor='"+parseFloat+"' where parametro='talonario'";
        SQL.executeQuery(sql, conexion);
    }

    public static void actualizarCanjeo(float parseFloat) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        String sql="update configuracion set valor='"+parseFloat+"' where parametro='canjeo'";
        SQL.executeQuery(sql, conexion);
    }

    public static int getCantidad(int id) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        int cantidad=0;
        String sql="select numentradas from registro where identrada="+id+" and idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        if(rs.next())
            cantidad+=rs.getInt(1);
        sql="select numentradas from pagoTarjeta where identrada="+id+" and idturno="+getIdTurnoActual();
        rs=SQL.executeQuery(sql, conexion);
        if(rs.next())
            cantidad+=rs.getInt(1);
        return cantidad;
    }
    
    public static int getTickets(int id) throws NoDriverFoundException, SQLException, SQLSintaxError {
    	getConexion();
        String sql="select numbebidas from turnosentradas where identrada="+id+" and idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }

    public static int getCantidadTotal() throws NoDriverFoundException, SQLException, SQLSintaxError {
       getConexion();
       int cantidad=0;
        String sql="select sum(numentradas) from registro where idturno="+getIdTurnoActual();
        ResultSet rs=SQL.executeQuery(sql, conexion);
        if(rs.next())
            cantidad+=rs.getInt(1); 
        sql="select sum(numentradas) from pagoTarjeta where idturno="+getIdTurnoActual();
        rs=SQL.executeQuery(sql, conexion);
        if(rs.next())
            cantidad+=rs.getInt(1);
        return cantidad;
    }

    public static String getInvitaciones() throws NoDriverFoundException, SQLException, SQLSintaxError {
       getConexion();
       String sql="select * from invitaciones";
       ResultSet rs=SQL.executeQuery(sql, conexion);
       StringBuilder sb=new StringBuilder("");
       while(rs.next()){
            sb.append(rs.getString("NOMBRE")).append(" -> ").append(rs.getString("NUMINVITACIONES")).append(" Invitaciones\n");
       }
        return sb.toString(); 
    }
    
    public static int getCopas() throws NoDriverFoundException, SQLException, SQLSintaxError{
       getConexion();
       int cantidad=0;
       String sql="select distinct identrada from registro where identrada in (select distinct identrada from entradas where nombrebebida='Copa') and idturno="+getIdTurnoActual();
       ResultSet rs=SQL.executeQuery(sql, conexion);
       while(rs.next()){
            int id=rs.getInt(1);
            sql="select numentradas from registro where identrada="+id+" and idturno="+getIdTurnoActual();
            ResultSet rs2=SQL.executeQuery(sql, conexion);
            if(rs2.next()){
                cantidad+=getTickets(id)*rs2.getInt(1);
            }
       }
       sql="select distinct identrada from pagoTarjeta where identrada in (select distinct identrada from entradas where nombrebebida='Copa') and idturno="+getIdTurnoActual();
       rs=SQL.executeQuery(sql, conexion);
       while(rs.next()){
            int id=rs.getInt(1);
            sql="select numentradas from pagoTarjeta where identrada="+id+" and idturno="+getIdTurnoActual();
            ResultSet rs2=SQL.executeQuery(sql, conexion);
            if(rs2.next()){
                cantidad+=getTickets(id)*rs2.getInt(1);
            }
       }
       return cantidad; 
    }
    
    public static int getCervezas() throws NoDriverFoundException, SQLException, SQLSintaxError{
       getConexion();
       int cantidad=0;
       String sql="select distinct identrada from registro where identrada in (select distinct identrada from entradas where nombrebebida='Cerveza') and idturno="+getIdTurnoActual();
       ResultSet rs=SQL.executeQuery(sql, conexion);
        while(rs.next()){
            int id=rs.getInt(1);
            sql="select numentradas from registro where identrada="+id+" and idturno="+getIdTurnoActual();
            ResultSet rs2=SQL.executeQuery(sql, conexion);
            if(rs2.next()){
                cantidad+=getTickets(id)*rs2.getInt(1);
            }
       }
       sql="select distinct identrada from pagoTarjeta where identrada in (select distinct identrada from entradas where nombrebebida='Cerveza') and idturno="+getIdTurnoActual();
       rs=SQL.executeQuery(sql, conexion);
       while(rs.next()){
             int id=rs.getInt(1);
             sql="select numentradas from pagoTarjeta where identrada="+id+" and idturno="+getIdTurnoActual();
             ResultSet rs2=SQL.executeQuery(sql, conexion);
             if(rs2.next()){
                 cantidad+=getTickets(id)*rs2.getInt(1);
             }
        }
       return cantidad; 
    }

    public static int getHeight(int id) throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        ResultSet rs=SQL.executeQuery("select height from impresiones where identrada="+id, conexion);
        rs.next();
        return rs.getInt(1);
    }

    public static int getEntradasCanjeo() throws NoDriverFoundException, SQLException, SQLSintaxError {
        getConexion();
        ResultSet rs=SQL.executeQuery("select valor from configuracion where parametro='numtickets'", conexion);
        rs.next();
        return rs.getInt(1);
    }


	public static String getPassword() throws NoDriverFoundException, SQLException, SQLSintaxError {
		getConexion();
        ResultSet rs=SQL.executeQuery("select valor from configuracion where parametro='password'", conexion);
        rs.next();
        return rs.getString(1);
	}


	public static void actualizarTicketCanjeo(int parseInt) throws NoDriverFoundException, SQLException, SQLSintaxError {
		getConexion();
        String sql="update configuracion set valor='"+parseInt+"' where parametro='numtickets'";
        SQL.executeQuery(sql, conexion);
		
	}
	
	public static float getPrecio(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
		getConexion();
		String sql="select precio FROM turnosentradas WHERE identrada="+id+" and idturno="+MySQL.getIdTurnoActual();
		System.out.println(sql);
		ResultSet rs=SQL.executeQuery(sql, conexion);
		if(rs.next()){
			return rs.getFloat(1);
		}
		return 0;
	}*/

}
