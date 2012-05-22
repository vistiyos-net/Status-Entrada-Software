/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.avisos;

import java.awt.Container;

import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Avisos {
    
    static public void mostrarError(Container padre,String title,String message,boolean close){
        JOptionPane.showMessageDialog(padre,message,title,JOptionPane.ERROR_MESSAGE);
        if(close){
            System.exit(1);
        }
    }
    
    static public void mostrarMensaje(Container padre,String title,String message){
        JOptionPane.showMessageDialog(padre, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
