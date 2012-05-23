/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

import net.vistiyos.interfaz.Password;

/**
 *
 * @author Dell
 */
public class botonCancelar implements ActionListener{
    
    private Password pass;
    private JPasswordField password;
    
    public botonCancelar(Password pass,JPasswordField password){
        this.pass=pass;
        this.password=password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(password.getPassword().length==0){
            pass.setVisible(false);
        }
        else{
            password.setText("");
        }
    }
    
}
