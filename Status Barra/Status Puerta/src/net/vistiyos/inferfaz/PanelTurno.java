/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.vistiyos.db.MySQL;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

/**
 *
 * @author Dell
 */
public class PanelTurno extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4907227777716724484L;
	private PanelConfiguracion[] entradas;
    private int id;
    private JLabel turno;
    
    PanelTurno(String nombre,int id) throws SQLException, NoDriverFoundException, SQLSintaxError{
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        this.id=id;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.insets=new Insets(0,7,3,0);
        turno=new JLabel(nombre);
        JLabel ticket=new JLabel("Nº de Tickets");
        JLabel precio=new JLabel("Precio");
        panel.add(turno,c);
        c.gridx=1;
        panel.add(ticket,c);
        c.gridx=2;
        panel.add(precio,c);
        this.add(panel);
        initEntradas();
        if(id==2){
            JPanel panel_1=new JPanel();
            panel_1.setLayout(new BoxLayout(panel_1,BoxLayout.X_AXIS));
            JLabel precio_1=new JLabel("Precio Fiestas Especiales:");
            JTextField campo_1=new JTextField(Float.toString(MySQL.getCanjeo()));
            campo_1.setName("canjeo");
            panel_1.add(precio_1);
            panel_1.add(campo_1);
            JPanel panel_2=new JPanel();
            panel_2.setLayout(new BoxLayout(panel_2,BoxLayout.X_AXIS));
            JLabel precio_2=new JLabel("Precio Talonario/Copa:");
            JTextField campo_2=new JTextField(Float.toString(MySQL.getTalonario()));
            campo_2.setName("talonario");
            panel_2.add(precio_2);
            panel_2.add(campo_2);
            JPanel panel_3=new JPanel();
            panel_3.setLayout(new BoxLayout(panel_3,BoxLayout.X_AXIS));
            JLabel precio_3=new JLabel("Numero de Copas Fiestas Especiales:");
            JTextField campo_3=new JTextField(Integer.toString(MySQL.getEntradasCanjeo()));
            campo_3.setName("numtickets");
            panel_3.add(precio_3);
            panel_3.add(campo_3);
            this.add(panel_1);
            this.add(panel_3);
            this.add(panel_2);
        }
    }

    private void initEntradas() throws SQLException, NoDriverFoundException, SQLSintaxError {
        ArrayList<Map<String,String>> aux=MySQL.getEntradas(id);
        this.entradas=new PanelConfiguracion[aux.size()];
        for(int i=0;i<aux.size();i++){
            Map<String,String> map=aux.get(i);
            String nombre=map.get("NOMBRE");
            this.entradas[i]=new PanelConfiguracion(nombre,id);
            this.add(entradas[i]);
        }
    }
    
}