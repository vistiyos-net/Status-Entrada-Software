/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTextField;

import net.vistiyos.db.MySQL;
import net.vistiyos.interfaz.Configuracion;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


/**
 *
 * @author Dell
 */
public class saveConfiguration implements ActionListener{
    
    private Configuracion conf;
    private int idEntrada;
    private int idTurno;
    private int ticket;
    private float precio;
    
    public saveConfiguration(Configuracion conft){
        this.conf=conft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            getClassComponents(conf);
            this.conf.toggleView();
        } catch (NoDriverFoundException ex) {
            Logger.getLogger(saveConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(saveConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLSintaxError ex) {
            Logger.getLogger(saveConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getClassComponents(Container i) throws NoDriverFoundException, SQLException, SQLSintaxError{
        if(i.getComponentCount()==0){
            if(i.getClass().toString().contains("JLabel")){
                JLabel label=(JLabel) i;
                if(label.getText().contains("Entrada")){
                    this.idEntrada=MySQL.getIdEntradaByName(label.getText());
                }
                else if(label.getText().contains("Turno de")){
                    this.idTurno=MySQL.getIdTurnoByName(label.getText());
                }
            }
            else if(i.getClass().toString().contains("JTextField")){
                JTextField field=(JTextField) i; 
                if(field.getName().equalsIgnoreCase("precio")){
                    this.precio=Float.parseFloat(field.getText().replace(",", "."));
                    MySQL.actualizarEntrada(this.idTurno,this.idEntrada,ticket,precio);
                }
                else if(field.getName().equalsIgnoreCase("ticket")){
                    this.ticket=Integer.parseInt(field.getText());
                }
                else if(field.getName().equalsIgnoreCase("talonario")){
                    MySQL.actualizarTalonario(Float.parseFloat(field.getText().replace(",", ".")));
                }
                else if(field.getName().equalsIgnoreCase("canjeo")){
                    MySQL.actualizarCanjeo(Float.parseFloat(field.getText().replace(",", ".")));
                }
                else if(field.getName().equalsIgnoreCase("numtickets")){
                    MySQL.actualizarTicketCanjeo(Integer.parseInt(field.getText()));
                }
            }
        }
        else{
            for(int j=0;j<i.getComponentCount();j++){
                this.getClassComponents((Container)i.getComponent(j));
            }
        }
    }
    
}
