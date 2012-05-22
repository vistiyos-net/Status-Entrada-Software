/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package status;


import java.sql.SQLException;

import net.vistiyos.inferfaz.Ventana;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

/**
 *
 * @author Dell
 */
public class Status {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoDriverFoundException, SQLSintaxError, SQLException {
            Ventana vnt=new Ventana();
            vnt.setVisible();
    }
}
