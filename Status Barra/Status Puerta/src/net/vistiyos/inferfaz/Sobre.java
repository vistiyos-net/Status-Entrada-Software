package net.vistiyos.inferfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 792487735390899602L;
	
	
	public Sobre(Ventana padre){
		super(padre,"Sobre Gestión de Entradas");
		JPanel panel=new JPanel();
		JPanel panel2=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		JPanel panel3=new JPanel();
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"\\imagenes\\logo.png");
		JLabel icono=new JLabel(icon);
		JLabel texto1=new JLabel("Todos los derechos reservados por la autoría de este software");
		JLabel texto2=new JLabel("y cada uno de los módulos a EscubraSoft y a su equipo");
		JLabel texto3=new JLabel("de desarrolladores.");
		panel.add(icono);
		panel3.add(texto1);
		panel3.add(texto2);
		panel3.add(texto3);
		panel.add(panel3);
		panel2.add(panel);
		JButton aceptar=new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sobre.this.toggleView();
			}
			
		});
		panel2.add(aceptar);
		this.setContentPane(panel2);
		this.pack();
	}
	
	public void toggleView(){
		this.setVisible(!this.isVisible());
	}

}
