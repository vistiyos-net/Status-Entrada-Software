
package net.vistiyos.interfaz;

import java.awt.Color;
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
 * @author Víctor Escobar
 */
public class Pagos extends JDialog implements ActionListener{
 
	private static final long serialVersionUID = -4958918651210471209L;
	private JButton caja;
    private JButton tarjeta;
    private JPanel panel;
    
    public Pagos(Ventana vnt){
        super(vnt,"SELECCIONA EL PAGO");
        panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cn=new GridBagConstraints();
        cn.fill=GridBagConstraints.BOTH;
        cn.weightx=1.0;
        cn.weighty=1.0;
        caja=new JButton("En Metálico");
        caja.setName("metalico");
        caja.setOpaque(false);
		caja.setContentAreaFilled(false);
		caja.setForeground(Color.BLACK);
        caja.addActionListener(this);
        caja.setFont(new Font("ARIAL",Font.BOLD,25));
        tarjeta=new JButton("Con Tarjeta");
        tarjeta.setName("tarjeta");
        tarjeta.setOpaque(false);
		tarjeta.setContentAreaFilled(false);
		tarjeta.setForeground(Color.BLACK);
        tarjeta.addActionListener(this);
        tarjeta.setFont(new Font("ARIAL",Font.BOLD,25));
        cn.gridx=0;
        cn.gridheight=1;
        cn.gridwidth=1;
        cn.gridy=0;
        panel.add(caja,cn);
        cn.gridx=1;
        panel.add(tarjeta, cn);
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
	public void actionPerformed(ActionEvent event) {
		JButton accion = (JButton) event.getSource();
		if(accion.getName().equals("METALICO")){
		}
		else if(accion.getName().equals("TARJETA")){
			
		}
	}
    
}
