package interfaz.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import net.vistiyos.db.MySQL;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


public class Salir implements ActionListener {

	
	public Salir(){
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
        try {
			MySQL.resetearRegistro();
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
        try { 
            Runtime.getRuntime().exec("shutdown -s -t 5"); 
        }  
        catch (IOException e) { 
            System.out.println("Failed");         
        }
        System.exit(0);
	}

}
