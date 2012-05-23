/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.vistiyos.db.MySQL;
import net.vistiyos.impresion.colaImpresion;
import net.vistiyos.interfaz.Pagos;
import net.vistiyos.interfaz.Ventana;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

import registros.Indices.Indices;

/**
 *
 * @author Dell
 */
public class metalico implements ActionListener{
    
    private Pagos vnt;
    
    public metalico(Pagos vnt){
       this.vnt=vnt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch(vnt.getEntrada()){
                case -1://Canjeo
                	Indices.indiceCanjeoSIM++;
                    colaImpresion.imprimirCanjeo();
                    break;
                case -2://Talonario
                	Indices.indiceTalonarioM+=Integer.parseInt(vnt.getOpciones().get("talon"));
                    colaImpresion.imprimirTalonario(Integer.parseInt(vnt.getOpciones().get("talon")));
                    break;
                default:
                    MySQL.increaseEntrance(vnt.getEntrada(),MySQL.getIdTurnoActual());
                    colaImpresion.imprimirEntrada(vnt.getEntrada());
                    break;
            } 
            Ventana vent=(Ventana) vnt.getParent();
            float precio=MySQL.getPrecio(vnt.getEntrada());
            vent.setPrecio(precio);
            vnt.toggleView();
        } catch (NoDriverFoundException ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLSintaxError ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
