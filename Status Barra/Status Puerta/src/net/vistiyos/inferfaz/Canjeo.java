/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.vistiyos.impresion.colaImpresion;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

import registros.Indices.Indices;

/**
 *
 * @author Dell
 */
public class Canjeo extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1316015017833650915L;
	private JPanel panel;
    private JButton pago;
    private JButton canjeo;
    
    Canjeo(Ventana vnt){
        super(vnt,"SELECCIONE MÉTODO DE COMPRA");
        panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cn=new GridBagConstraints();
        cn.gridheight=1;
        cn.gridwidth=1;
        cn.gridx=0;
        cn.gridy=0;
        cn.fill=GridBagConstraints.BOTH;
        cn.weightx=1.0;
        cn.weighty=1.0;
        pago=new JButton("PAGAR");
        pago.setFont(new Font("ARIAL",Font.BOLD,40));
        pago.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Indices.indiceCanjeoSI++;
                new Pagos((Ventana)net.vistiyos.inferfaz.Canjeo.this.getParent(),-1,null).toggleView();
                net.vistiyos.inferfaz.Canjeo.this.toggleView();
            }
        });
        canjeo=new JButton("CANJEAR");
        canjeo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Almacenar registro de Canjeados
            	try {
            		Indices.indiceCanjeoNO++;
					colaImpresion.imprimirCanjeo();
				} catch (NoDriverFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLSintaxError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                net.vistiyos.inferfaz.Canjeo.this.toggleView();
            }
        });
        canjeo.setFont(new Font("ARIAL",Font.BOLD,40));
        panel.add(pago, cn);
        cn.gridx++;
        panel.add(canjeo, cn);
        this.setContentPane(panel);
        this.setSize(700, 200);
        this.setBounds(200, 200, 700, 200);
    }
    
    public void toggleView(){
        this.setVisible(!this.isVisible());
    }
    
    
}
