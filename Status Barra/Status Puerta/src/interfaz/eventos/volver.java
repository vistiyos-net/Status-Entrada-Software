/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import net.vistiyos.db.MySQL;
import net.vistiyos.interfaz.Ventana;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

import registros.Indices.Indices;

/**
 *
 * @author Dell
 */
public class volver implements ActionListener{
    
    private Ventana vnt;
    
    public volver(Ventana vnt){
        this.vnt=vnt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        	MySQL.resetearRegistro();
            Indices.initRecords();
            vnt.initTurnos();
        } catch (NoDriverFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLSintaxError ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
