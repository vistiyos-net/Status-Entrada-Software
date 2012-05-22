package net.vistiyos.inferfaz;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class ExploradorFacturas extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5900174582228524243L;
	private JComboBox<String> lista;
	private JButton abrir;
	private File file;
	
	ExploradorFacturas(Ventana vnt){
		super(vnt,"Escoja Factura");
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints cn=new GridBagConstraints();
	    cn.fill=GridBagConstraints.BOTH;
        cn.weightx=1.0;
        cn.weighty=1.0;
        cn.gridx=0;
        cn.gridy=0;
		file=new File(System.getProperty("user.dir")+"\\FACTURAS\\");
		String[] facturas=file.list();
		Arrays.sort(facturas);
		lista=new JComboBox<String>(facturas);
		lista.setFont(new Font("ARIAL",Font.BOLD,40));
		abrir=new JButton("Abrir Factura");
		abrir.setFont(new Font("ARIAL",Font.BOLD,40));
		abrir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(ExploradorFacturas.this.file.getAbsolutePath()+"\\"+lista.getSelectedItem()));
					ExploradorFacturas.this.toggleView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			
		});
		panel.add(lista,cn);
		cn.gridy=1;
		panel.add(abrir,cn);
		this.setContentPane(panel);
        this.setSize(700, 200);
        this.setBounds(200, 200, 700, 200);
	}
	
	public void toggleView(){
		this.setVisible(!this.isVisible());
	}

}
