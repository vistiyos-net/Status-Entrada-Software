/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.inferfaz.CierreCajaInterfaz;
import net.vistiyos.inferfaz.confirmacion;
import net.vistiyos.inferfaz.Ventana;

import thread.cerrarCaja;

/**
 *
 * @author Dell
 */
public class cierreCaja implements ActionListener{
    
    private CierreCajaInterfaz interfaz;
    private confirmacion cont;
    
    
    public cierreCaja(Ventana vnt,confirmacion cont){
        this.cont=cont;
        interfaz=new CierreCajaInterfaz(vnt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            this.cont.toggleView();
            cerrarCaja caja=new cerrarCaja(interfaz);
            caja.start();
    }
    
}
