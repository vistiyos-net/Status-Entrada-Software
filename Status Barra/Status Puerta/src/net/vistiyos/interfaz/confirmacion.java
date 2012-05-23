/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.interfaz;

import interfaz.eventos.cierreCaja;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class confirmacion extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4458461313510399206L;

	confirmacion(Ventana vnt){
        super(vnt,"CONFIRMACIÓN");
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cn=new GridBagConstraints();
        cn.gridheight=1;
        cn.gridwidth=1;
        cn.gridx=0;
        cn.gridy=0;
        cn.fill=GridBagConstraints.BOTH;
        cn.weightx=1.0;
        cn.weighty=1.0;
        JButton si=new JButton("SI");
        si.setFont(new Font("ARIAL",Font.BOLD,40));
        si.addActionListener(new cierreCaja((Ventana)this.getParent(),this));
        JButton no=new JButton("NO");
        no.setFont(new Font("ARIAL",Font.BOLD,40));
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                net.vistiyos.interfaz.confirmacion.this.toggleView();
            }
        });
        panel.add(si, cn);
        cn.gridx=1;
        panel.add(no,cn);
        this.setContentPane(panel);
        this.setSize(700, 200);
        this.setBounds(200, 200, 700, 200);
    }
    
    public void toggleView(){
        this.setVisible(!this.isVisible());
    }
    
}
