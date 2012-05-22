/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;

import interfaz.eventos.saveConfiguration;

import java.sql.SQLException;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.vistiyos.db.MySQL;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;


/**
 *
 * @author Dell
 */
public class Configuracion extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7551218602845812767L;
	private PanelTurno[] turnos;
    private JPanel panel;
    private JButton guardar;

    public Configuracion(Ventana padre) throws NoDriverFoundException,SQLException, SQLSintaxError{
        super(padre,"CONFIGURACIÓN");
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        turnos=new PanelTurno[MySQL.getTurnos().size()];
        for(int i=0;i<MySQL.getTurnos().size();i++){
            Map<String,String> e=MySQL.getTurnos().get(i);
            turnos[i]=new PanelTurno(e.get("NOMBRETURNO"),Integer.parseInt(e.get("IDTURNOS")));
            panel.add(turnos[i]);
        }
        guardar=new JButton("Guardar Configuración");
        guardar.addActionListener(new saveConfiguration(this));
        panel.add(guardar);
        this.setContentPane(panel);
        this.setSize(400, 300);
    }
    
    public void toggleView(){
        this.setVisible(!this.isVisible());
    }
    
    
}
