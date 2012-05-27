
package net.vistiyos.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class Invitacion extends JDialog implements ActionListener{
    
	private static final long serialVersionUID = -1999103455329517004L;
	private JTextField nombre;
    private JTextField num;
    
    public Invitacion(Ventana vnt){
        super(vnt,"INVITACIONES");
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints estilo = new GridBagConstraints();
        estilo.gridx = 0;
        estilo.gridy = 0;
        estilo.gridheight = 1;
        estilo.gridwidth = 1;
        estilo.weightx = 1.0;
        estilo.weighty = 1.0;
        estilo.fill = GridBagConstraints.BOTH;
        JLabel nom=new JLabel("Nombre:");
        panel.add(nom,estilo);
        nombre=new JTextField(50);
        estilo.gridx++;
        panel.add(nombre,estilo);
        JLabel nume=new JLabel("Número de Invitaciones (MAX 10):");
        estilo.gridx = 0;
        estilo.gridy++;
        panel.add(nume,estilo);
        num=new JTextField(5);
        estilo.gridx++;
        panel.add(this.num,estilo);
        JButton imprimir=new JButton("Imprimir");
        imprimir.addActionListener(this);
        estilo.gridx = 0;
        estilo.gridy++;
        estilo.gridwidth = 2;
        panel.add(imprimir,estilo);
        this.setContentPane(panel);
        this.setSize(500, 100);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void toogleView(){	
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

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
    
}
