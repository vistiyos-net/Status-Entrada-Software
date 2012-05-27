
package net.vistiyos.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    private JLabel contador;
    
    public Ventana(){
        super("GESTIÓN DE ENTRADAS STATUS");
        MySQL.inicializarBD();	
        fuente=new Font("TimesRoman", Font.BOLD, 25);
        initEntradas();
       // this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.toFront();
        //this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
    
    private void initEntradas(){
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
    	estilo.fill = GridBagConstraints.BOTH;
    	if(MySQL.isTurnoActivo()){
    		ArrayList<String> entradas = MySQL.getEntradas();
    		columnas = (int) Math.sqrt(entradas.size())+1;
    		Iterator<String> it = entradas.iterator();
    		while(it.hasNext()){
    			JButton boton = new JButton(it.next());
    			boton.addActionListener(this);
    			boton.setOpaque(false);
        		boton.setContentAreaFilled(false);
    			boton.setFont(fuente);
    			boton.setForeground(Color.BLACK);
    			panelPrincipal.add(boton, estilo);
    			estilo.gridx++;
    			if(estilo.gridx == columnas){
    				estilo.gridy++;
    				estilo.gridx = 0;
    			}
    		}
    		if(MySQL.isCanjeo()){
    			JButton canjeo = new JButton("Canjeo");
    			canjeo.setName("canjeo");
    			canjeo.addActionListener(this);
    			canjeo.setOpaque(false);
    			canjeo.setContentAreaFilled(false);
    			canjeo.setFont(fuente);
    			canjeo.setForeground(Color.BLACK);
    			panelPrincipal.add(canjeo, estilo);
    			estilo.gridx++;
    			if(estilo.gridx == columnas){
    				estilo.gridy++;
    				estilo.gridx = 0;
    			}
    		}
    		if(MySQL.isInvitacion()){
    			JButton invitacion = new JButton("Invitación");
    			invitacion.setName("invitacion");
    			invitacion.addActionListener(this);
    			invitacion.setOpaque(false);
    			invitacion.setContentAreaFilled(false);
    			invitacion.setFont(fuente);
    			invitacion.setForeground(Color.BLACK);
    			panelPrincipal.add(invitacion, estilo);
    			estilo.gridx++;
    			if(estilo.gridx == columnas){
    				estilo.gridy++;
    				estilo.gridx = 0;
    			}
    		}
    		if(MySQL.isTalonario()){
    			JButton talonario = new JButton("Talonario");
    			talonario.setName("talonario");
    			talonario.addActionListener(this);
    			talonario.setOpaque(false);
    			talonario.setContentAreaFilled(false);
    			talonario.setFont(fuente);
    			talonario.setForeground(Color.BLACK);
    			panelPrincipal.add(talonario, estilo);
    			estilo.gridx++;
    			if(estilo.gridx == columnas){
    				estilo.gridy++;
    				estilo.gridx = 0;
    			}
    		}
    		contador = new JLabel("Precio: 0 €");
    		contador.setFont(fuente);
    		panelPrincipal.add(contador, estilo);
    		JButton pagar = new JButton("Pagar");
    		pagar.setName("pagar");
    		pagar.addActionListener(this);
    		pagar.setOpaque(false);
    		pagar.setContentAreaFilled(false);
    		pagar.setFont(fuente);
    		pagar.setForeground(Color.BLACK);
    		estilo.gridx++;
    		panelPrincipal.add(pagar, estilo);
    		JButton cerrarCaja = new JButton("Cerrar Caja");
    		cerrarCaja.setName("cerrar");
    		cerrarCaja.addActionListener(this);
    		cerrarCaja.setOpaque(false);
    		cerrarCaja.setContentAreaFilled(false);
    		cerrarCaja.setFont(fuente);
    		cerrarCaja.setForeground(Color.BLACK);
    		estilo.gridx++;
    		panelPrincipal.add(cerrarCaja, estilo);
    	}
    	else{
    		new NoTurnoActivo(this);
    	}
		this.setContentPane(new JScrollPane(panelPrincipal));
	}
    
    public void setPrecio(float precio){
    	NumberFormat nf = NumberFormat.getInstance();
    	String[] datos = contador.getText().split(" ");
    	this.contador.setText("Precio: "+nf.format(precio+Float.parseFloat(datos[1]))+" €");
    	this.repaint();
    }


	@Override
	public void actionPerformed(ActionEvent event) {
		JButton accion = (JButton) event.getSource();
		if(accion.getName() == null || accion.getName().equals("")){
			MySQL.insertarNuevaEntrada(accion.getText());
			setPrecio(MySQL.getPrecioEntrada(accion.getText()));
		}
		else if(accion.getName().equals("canjeo")){
			new Canjeo(this);
		}
		else if(accion.getName().equals("invitacion")){
			new Invitacion(this);
		}
		else if(accion.getName().equals("talonario")){
			new Talonario(this);
		}
		else if(accion.getName().equals("pagar")){
			new Pagos(this);
		}
		else if(accion.getName().equals("cerrar")){
		}
	}


	public void refresh(){
		initEntradas();
	}
    
    
    	
}
