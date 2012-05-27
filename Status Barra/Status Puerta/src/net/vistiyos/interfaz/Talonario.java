
package net.vistiyos.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.vistiyos.db.MySQL;

/**
 *
 * @author Víctor Escobar
 */
public class Talonario extends JDialog implements ActionListener{
    
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
        String[] cantidad=new String[MySQL.getMaxTalonario()];
        for(int i=0;i<cantidad.length;i++){
            cantidad[i]=Integer.toString(i+1);
        }
        lista=new JComboBox<String>(cantidad);
        lista.setFont(new Font("ARIAL",Font.BOLD,25));
        panel.add(lista,cn);
        cn.gridwidth=1;
        cn.gridx=0;
        cn.gridy=1;
        this.aceptar=new JButton("Aceptar");
        aceptar.addActionListener(this);
        aceptar.setOpaque(false);
		aceptar.setContentAreaFilled(false);
		aceptar.setForeground(Color.BLACK);
        aceptar.setFont(new Font("ARIAL",Font.BOLD,25));
        panel.add(aceptar,cn);
        this.setContentPane(panel);
        this.setSize(500, 150);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void toggleView(){
        this.setVisible(!this.isVisible());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		new PagosTalonario((Ventana)this.getParent(),Integer.parseInt(String.valueOf(lista.getSelectedItem()))).toggleView();
        this.toggleView();
	}
    
}
