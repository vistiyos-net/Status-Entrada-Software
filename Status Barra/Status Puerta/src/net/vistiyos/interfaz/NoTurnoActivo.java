package net.vistiyos.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NoTurnoActivo extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextArea texto;
	private JButton recargar;
	private ImageIcon icon;
	private JLabel image;
	private GridBagConstraints estilo;
	private Ventana padre;
	
	NoTurnoActivo(Ventana padre){
		super(padre);
		this.padre = padre;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		estilo = new GridBagConstraints();
		this.texto = new JTextArea("No se ha activado Turno. Consulte con el Gerente");
		this.texto.setEditable(false);
		this.texto.setWrapStyleWord(true);
		this.texto.setLineWrap(true);
		this.texto.setOpaque(false);
		recargar = new JButton("Volver a Comprobar");
		recargar.requestFocus();
		recargar.addActionListener(this);
		estilo.gridheight = 1;
		estilo.gridwidth = 1;
		estilo.gridx = 0;
		estilo.gridy = 0;
		icon = new ImageIcon(System.getProperty("user.dir")+"/icons/error.png");
		this.setTitle("No Se Ha Activado Turno");
		image = new JLabel();
		image.setIcon(icon);
		estilo.insets = new Insets(15,15,15,15);
		panel.add(image,estilo);
		estilo.gridx = 1;
		estilo.gridwidth = 2;
		estilo.weightx = 1.0;
		estilo.weighty = 1.0;
		panel.add(this.texto, estilo);
		estilo.gridx = 2;
		estilo.gridy = 1;
		estilo.gridwidth = 1;
		estilo.insets = new Insets(0,0,15,0);
		panel.add(recargar, estilo);
		this.setContentPane(panel);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setSize(250, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.setVisible(false);
		this.padre.refresh();
	}

}
