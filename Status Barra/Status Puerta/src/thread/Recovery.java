package thread;

import interfaz.avisos.Avisos;

import java.sql.SQLException;

import net.vistiyos.db.MySQL;
import net.vistiyos.interfaz.Ventana;
import net.vistiyos.util.EnviarMail;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


public class Recovery extends Thread {
	
	private Ventana vnt;
	
	public Recovery(Ventana vnt){
		this.vnt=vnt;
	}
	
	public void run(){
		try {
			String password=MySQL.getPassword();
			EnviarMail.enviarPassword(password);
			Avisos.mostrarMensaje(vnt, "Mensaje enviado", "Se ha enviado un correo electronico con la contraseña.");
		} catch (NoDriverFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLSintaxError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
