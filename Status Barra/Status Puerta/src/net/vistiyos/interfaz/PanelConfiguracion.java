/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Map;
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
public class PanelConfiguracion extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8001073468123285063L;
	private JTextField ticket;
    private JTextField precio;
    private JLabel name;
    private int turno;
    
    PanelConfiguracion(String nombre,int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        this.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.insets=new Insets(0,7,3,0);
        name=new JLabel(nombre);
        name.setName(nombre);
        turno=id;
        ticket=new JTextField(6);
        ticket.setName("ticket");
        precio=new JTextField(10);
        precio.setName("precio");
        rellenarCampos();
        this.add(name,c);
        c.gridx=1;
        this.add(ticket,c);
        c.gridx=2;
        this.add(precio,c);
    }
    
    public int getTicket(){
        return Integer.parseInt(this.ticket.getText());
    }
    
    public float getPrecio(){
        return Float.parseFloat(this.precio.getText().replace(',', '.'));
    }
    
    private void rellenarCampos() throws NoDriverFoundException, SQLException, SQLSintaxError{
        Map.Entry<String,String> e=MySQL.getTicketPrecio(this.name.getText(), turno);
        this.ticket.setText(e.getKey());
        this.precio.setText(e.getValue());
    }
    
}
