/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.interfaz;

import interfaz.eventos.metalico;
import interfaz.eventos.pagoTarjeta;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Pagos extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4958918651210471209L;
	private JButton caja;
    private JButton tarjeta;
    private JPanel panel;
    private int id;
    private Map<String,String> opciones;
    
    public Pagos(Ventana vnt,int id,Map<String,String> opciones){
        super(vnt,"SELECCIONA EL PAGO");
        this.id=id;
        this.opciones=opciones;
        panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cn=new GridBagConstraints();
        cn.fill=GridBagConstraints.BOTH;
        cn.weightx=1.0;
        cn.weighty=1.0;
        caja=new JButton("EN METÁLICO");
        caja.addActionListener(new metalico(this));
        caja.setFont(new Font("ARIAL",Font.BOLD,40));
        tarjeta=new JButton("CON TARJETA");
        tarjeta.addActionListener(new pagoTarjeta(this));
        tarjeta.setFont(new Font("ARIAL",Font.BOLD,40));
        cn.gridx=0;
        cn.gridheight=1;
        cn.gridwidth=1;
        cn.gridy=0;
        panel.add(caja,cn);
        cn.gridx=1;
        panel.add(tarjeta, cn);
        this.setContentPane(panel);
        this.setSize(700, 200);
        this.setBounds(200, 200, 700, 200);
    }

    public void toggleView(){
        this.setVisible(!this.isVisible());
    }

    public int getEntrada() {
        return this.id;
    }
    
    public Map<String,String> getOpciones(){
        return this.opciones;
    }
    
}
