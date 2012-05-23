
package net.vistiyos.interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.vistiyos.db.MySQL;


/**
 *
 * @author Víctor Escobar
 */
public class Ventana extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    private JPanel panelPrincipal;
    private int columnas;
    private Font fuente;
    
    public Ventana(){
        super("GESTIÓN DE ENTRADAS STATUS");
        fuente=new Font("TimesRoman", Font.BOLD, 25);
        initEntradas();
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.toFront();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
    
    private void initEntradas() {
    	panelPrincipal = new JPanel();
    	panelPrincipal.setLayout(new GridBagLayout());
    	GridBagConstraints estilo = new GridBagConstraints();
    	estilo.gridheight = 1;
    	estilo.gridwidth = 1;
    	estilo.gridx = 0;
    	estilo.gridy = 0;
    	estilo.weightx = 1.0;
    	estilo.weighty = 1.0;
    	estilo.insets = new Insets(15,15,15,15);
    	estilo.ipadx = 100;
    	estilo.ipady = 100;
    	if(MySQL.isTurnoActivo()){
    		ArrayList<String> entradas = MySQL.getEntradas();
    		columnas = (int) Math.sqrt(entradas.size())+1;
    		Iterator<String> it = entradas.iterator();
    		while(it.hasNext()){
    			JButton boton = new JButton(it.next());
    			boton.setFont(fuente);
    			panelPrincipal.add(boton, estilo);
    			estilo.gridx++;
    			if(estilo.gridx == columnas){
    				estilo.gridy++;
    				estilo.gridx = 0;
    			}
    		}
    	}
    	else{
    		new NoTurnoActivo(this);
    	}
		this.setContentPane(panelPrincipal);
	}
    
    /*public void setPrecio(float precio){
    	NumberFormat nf = NumberFormat.getInstance();
    	this.marcador.setText(nf.format(precio)+" €");
    	this.repaint();
    }*/


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}


	public void refresh() {
		initEntradas();
	}
    
    
    	
}
