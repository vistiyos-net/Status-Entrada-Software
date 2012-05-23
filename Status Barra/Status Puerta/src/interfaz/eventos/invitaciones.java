/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;

import interfaz.avisos.Avisos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.vistiyos.db.MySQL;
import net.vistiyos.impresion.colaImpresion;
import net.vistiyos.interfaz.Invitacion;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


/**
 *
 * @author Dell
 */
public class invitaciones implements ActionListener{
    
    private Invitacion inv;
    
    public invitaciones(Invitacion inv){
        this.inv=inv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre=inv.getNombre();
        int numero=inv.getNumEntradas();
        try {
        	if(nombre.isEmpty()){
        		Avisos.mostrarError(inv, "ERROR", "El nombre no puede estar vacío.", false);
        	}
        	else if(numero<0 || numero>10){
        		Avisos.mostrarError(inv, "ERROR", "El número debe estar entre 1 y 10.", false);
        	}
        	else{
        		MySQL.addInvitacion(nombre, numero);
        		inv.resetear();
        		inv.toogleView();
        		colaImpresion.imprimirInvitacion(numero);
        	}
        } catch (NoDriverFoundException ex) {
            Logger.getLogger(invitaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(invitaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLSintaxError ex) {
            Logger.getLogger(invitaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
