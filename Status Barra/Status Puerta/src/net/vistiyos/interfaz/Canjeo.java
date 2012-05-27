
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

import net.vistiyos.impresion.colaImpresion;

/**
 *
 * @author Víctor Escobar
 */
public class Canjeo extends JDialog implements ActionListener{
    
	private static final long serialVersionUID = -1316015017833650915L;
	private JPanel panel;
    private JButton pago;
    private JButton canjeo;
    
    Canjeo(Ventana vnt){
        super(vnt,"SELECCIONE MÉTODO DE COMPRA");
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
        pago=new JButton("Pagar");
        pago.setName("pagar");
        pago.setOpaque(false);
		pago.setContentAreaFilled(false);
		pago.setForeground(Color.BLACK);
        pago.setFont(new Font("ARIAL",Font.BOLD,25));
        pago.addActionListener(this);
        canjeo=new JButton("Canjear");
        canjeo.setName("canjear");
        canjeo.setOpaque(false);
		canjeo.setContentAreaFilled(false);
		canjeo.setForeground(Color.BLACK);
        canjeo.addActionListener(this);
        canjeo.setFont(new Font("ARIAL",Font.BOLD,25));
        panel.add(pago, cn);
        cn.gridx++;
        panel.add(canjeo, cn);
        this.setContentPane(panel);
        this.setSize(500, 150);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton accion = (JButton) event.getSource();
		if(accion.getName().equals("canjear")){
			colaImpresion.imprimirCanjeo();
			this.setVisible(false);
		}
		else if(accion.getName().equals("pagar")){
			new Pagos((Ventana)this.getParent()).toggleView();
			this.setVisible(false);
		}
		
	}
    
    
}
