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
import javax.swing.JPasswordField;

import net.vistiyos.db.MySQL;
import net.vistiyos.inferfaz.Configuracion;
import net.vistiyos.inferfaz.Password;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

/**
 *
 * @author Dell
 */
public class botonAceptar implements ActionListener{
    
    private Password pass;
    private JPasswordField password;
    
    public botonAceptar(Password pass,JPasswordField password){
        this.pass=pass;
        this.password=password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb=new StringBuilder();
        sb.append(this.password.getPassword());
        if(MySQL.checkPassword(sb.toString())){
            try {
                pass.toggleView();
                Configuracion c=new Configuracion(pass.getPadre());
                c.toggleView();
            } catch (NoDriverFoundException ex) {
                Logger.getLogger(botonAceptar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(botonAceptar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLSintaxError ex) {
                Logger.getLogger(botonAceptar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Avisos.mostrarError(pass, "CONTRASEÑA INCORRECTA", "Ha introducido una clave incorrecta", false);
            this.password.setText("");
        }
    }
    
}
