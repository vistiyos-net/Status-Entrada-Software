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
public class pagoTarjeta implements ActionListener{
    
    private Pagos tarj;
   
    public pagoTarjeta(Pagos tarj){
        this.tarj=tarj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch(tarj.getEntrada()){
                case -1://Canjeo
                	Indices.indiceCanjeoSIT++;
                    colaImpresion.imprimirCanjeo();
                  break;
                case -2://Talonario
                	Indices.indiceTalonarioT+=Integer.parseInt(tarj.getOpciones().get("talon"));
                    colaImpresion.imprimirTalonario(Integer.parseInt(tarj.getOpciones().get("talon")));
                    break;
                default:
                    MySQL.anadirEntradaTarjeta(tarj.getEntrada());
                    colaImpresion.imprimirEntrada(tarj.getEntrada());
                    break;
            }
            Ventana vent=(Ventana) tarj.getParent();
            float precio=MySQL.getPrecio(tarj.getEntrada());
            vent.setPrecio(precio);
            tarj.toggleView();
        } catch (NoDriverFoundException ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLSintaxError ex) {
            Logger.getLogger(pagoTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
