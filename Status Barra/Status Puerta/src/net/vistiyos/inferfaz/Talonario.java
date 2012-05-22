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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Talonario extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 320526049733494893L;
	private JComboBox<String> lista;
    private JButton aceptar;
    private JPanel panel;
    
    Talonario(Ventana vnt){
        super(vnt,"SELECCIONE CANTIDAD");
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
        String[] cantidad=new String[10];
        for(int i=0;i<cantidad.length;i++){
            cantidad[i]=Integer.toString(i+1);
        }
        lista=new JComboBox<String>(cantidad);
        lista.setFont(new Font("ARIAL",Font.BOLD,40));
        panel.add(lista,cn);
        cn.gridwidth=1;
        cn.gridx=0;
        cn.gridy=1;
        this.aceptar=new JButton("ACEPTAR");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String,String> map=new HashMap<String, String>();
                map.put("talon", String.valueOf(lista.getSelectedItem()));
                new Pagos((Ventana)net.vistiyos.inferfaz.Talonario.this.getParent(),-2,map).toggleView();
                net.vistiyos.inferfaz.Talonario.this.toggleView();
            }
        });
        aceptar.setFont(new Font("ARIAL",Font.BOLD,40));
        panel.add(aceptar,cn);
        this.setContentPane(panel);
        this.setSize(700, 200);
        this.setBounds(200, 200, 700, 200);
    }
    
    public void toggleView(){
        this.setVisible(!this.isVisible());
    }
    
}
