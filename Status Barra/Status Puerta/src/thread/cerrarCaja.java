/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;


import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.vistiyos.interfaz.CierreCajaInterfaz;
import net.vistiyos.util.EnviarMail;
import net.vistiyos.util.PDF;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


import com.itextpdf.text.BadElementException;

/**
 *
 * @author Dell
 */
public class cerrarCaja extends Thread{
    
    private CierreCajaInterfaz interfaz;
    private boolean fin=false;

    public cerrarCaja(CierreCajaInterfaz interfaz) {
        this.interfaz=interfaz;
    }
    
    
    @Override
    public void run(){
        try {
            interfaz.toogleView();
            interfaz.setMensaje("Generando fichero PDF");
            interfaz.setPorcentaje(0);
            try {
                PDF.generatePDF();
                interfaz.setMensaje("Fichero PDF Generado");
                for(int i=1;i<51;i++){
                    interfaz.setPorcentaje(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                interfaz.setMensaje("Enviando correo,espere...");
                EnviarMail.enviarEMail();
                for(int i=51;i<101;i++){
                        interfaz.setPorcentaje(i);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                interfaz.setMensaje("Correo enviado.La ventana se cerrará automaticamente.");
                interfaz.habilitarVolver();
                fin=true;
            } catch (NoDriverFoundException ex) {
                Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLSintaxError ex) {
                Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (BadElementException ex) {
            Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(cerrarCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.toogleView();
    }
    
    public boolean fin(){
    	return fin;
    }
}
