/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;

import interfaz.eventos.invitaciones;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class Invitacion extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1999103455329517004L;
	private JTextField nombre;
    private JTextField num;
    private Ventana vnt;
    
    public Invitacion(Ventana vnt){
        super(vnt,"INVITACIONES");
        this.vnt=vnt;
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel nomb=new JPanel();
        nomb.setLayout(new BoxLayout(nomb,BoxLayout.X_AXIS));
        JPanel numer=new JPanel();
        numer.setLayout(new BoxLayout(numer,BoxLayout.X_AXIS));
        JLabel nom=new JLabel("Nombre:");
        nomb.add(nom);
        nombre=new JTextField(50);
        nomb.add(nombre);
        JLabel nume=new JLabel("Número de Invitaciones (MAX 10):");
        numer.add(nume);
        num=new JTextField(5);
        numer.add(this.num);
        panel.add(nomb);
        panel.add(numer);
        JButton imprimir=new JButton("Imprimir");
        invitaciones invi=new invitaciones(this);
        imprimir.addActionListener(invi);
        panel.add(imprimir);
        this.setContentPane(panel);
        this.pack();
    }
    
    public void toogleView(){
    	vnt.inhabilitarVolver();
        this.setVisible(!this.isVisible());
    }
    
    public String getNombre(){
        return this.nombre.getText();
    }
    
    public int getNumEntradas(){
    	try{
    		return Integer.parseInt(this.num.getText());
    	}catch(Exception ex){
    		return -1;
    	}
    }

    public void resetear() {
        this.nombre.setText("");
        this.num.setText("");
    }
    
}
