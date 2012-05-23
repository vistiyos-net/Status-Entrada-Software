/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.db.MySQL;
import net.vistiyos.interfaz.Turnos;
import net.vistiyos.interfaz.Ventana;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

/**
 *
 * @author Dell
 */
public class botonTurno implements ActionListener{
    
    private Ventana vnt;
    public botonTurno(Ventana vnt){
        this.vnt=vnt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Turnos n=(Turnos) e.getSource();
        try {
            if(MySQL.actualizarTurno(n.getIdTurno())){
                try {
                    vnt.initBotones();
                } catch (SQLSintaxError ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (NoDriverFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
