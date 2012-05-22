
package net.vistiyos.inferfaz;

import interfaz.eventos.Salir;
import interfaz.eventos.botonTurno;
import interfaz.eventos.inviAccion;
import interfaz.eventos.mostrarTeclado;
import interfaz.eventos.recuperar;
import interfaz.eventos.registroEntradas;
import interfaz.eventos.volver;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.vistiyos.db.MySQL;


/**
 *
 * @author Dell
 */
public class Ventana extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    private JPanel panelPrincipal;
    private int columnas;
    private JButton[] botonesEntradas;
    
    public Ventana(){
        super("GESTIÓN DE ENTRADAS STATUS");
        initEntradas();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.toFront();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
    
    private void initEntradas() {
		ArrayList<String> entradas = MySQL.getEntradas();
		botonesEntradas = new JButton [entradas.size()];
		Iterator<String> it = entradas.iterator();
		int i = 0;
		while(it.hasNext()){
			JButton boton = new JButton(it.next());
			botonesEntradas[i] = boton;
			i++;
		}
	}
    
    public void habilitarVolver(){
    	this.volver.setEnabled(true);
    	this.salir.setEnabled(true);
    }
    
    public void inhabilitarVolver(){
    	this.volver.setEnabled(false);
    	this.salir.setEnabled(false);
    }
    
    public void setPrecio(float precio){
    	NumberFormat nf = NumberFormat.getInstance();
    	this.marcador.setText(nf.format(precio)+" €");
    	this.repaint();
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    	
}
