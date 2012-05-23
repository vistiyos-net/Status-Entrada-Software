/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.interfaz.Invitacion;
import net.vistiyos.interfaz.Ventana;

/**
 *
 * @author Dell
 */
public class inviAccion implements ActionListener{
    
    private Invitacion inv;
    
    public inviAccion(Ventana vnt){
        this.inv=new Invitacion(vnt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inv.toogleView();
    }
    
}
