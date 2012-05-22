/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.inferfaz.Boton;
import net.vistiyos.inferfaz.Pagos;
import net.vistiyos.inferfaz.Ventana;

/**
 *
 * @author Dell
 */
public class registroEntradas implements ActionListener{
    
    private Ventana vnt;

    public registroEntradas(Ventana aThis) {
        vnt=aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    		vnt.inhabilitarVolver();
            Boton boton=(Boton) e.getSource();
            int identrada=boton.getIdEntrada();
            new Pagos(vnt,identrada,null).toggleView();
    }
    
}
