/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 *
 * @author Dell
 */
public class pulsacionBotonPassword implements ActionListener{
    
    private JPasswordField campoPassword;
    
    public pulsacionBotonPassword(JPasswordField campo){
        this.campoPassword=campo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b=(JButton) e.getSource();
        char [] password=this.campoPassword.getPassword();
        StringBuilder sb=new StringBuilder();
        sb.append(password).append(b.getText());
        this.campoPassword.setText(sb.toString());
    }
    
}
