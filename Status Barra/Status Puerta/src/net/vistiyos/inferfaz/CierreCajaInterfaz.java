/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Dell
 */
public class CierreCajaInterfaz extends JDialog{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8413292777174005477L;
	private JProgressBar barra;
    private JLabel mensaje;
    private JPanel panel;
    private Ventana vnt;
    
    public CierreCajaInterfaz(Ventana vnt){
        super(vnt,"CIERRE DE CAJA");
        this.vnt=vnt;
        barra=new JProgressBar(0,100);
        barra.setBorderPainted(true);
        mensaje=new JLabel();
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(mensaje);
        panel.add(barra);
        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
    }
    
    public void toogleView(){
        this.setVisible(!this.isVisible());
    }
    
    public void setPorcentaje(int n){
        Component[] componentes=panel.getComponents();
        for(Component c:componentes){
            if(c.getClass().toString().contains("JProgressBar")){
                JProgressBar aux=(JProgressBar) c;
                aux.setValue(n);
            }
        }
        this.pack();
    }
    
    public void setMensaje(String mensaje){
       Component[] componentes=panel.getComponents();
        for(Component c:componentes){
            if(c.getClass().toString().contains("JLabel")){
                JLabel aux=(JLabel) c;
                aux.setText(mensaje);
            }
        } 
        this.pack();
    }
    
    public void habilitarVolver(){
    		vnt.habilitarVolver();
    }
    
}
