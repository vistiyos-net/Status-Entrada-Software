/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.inferfaz;

import java.awt.Font;

import javax.swing.JButton;

/**
 *
 * @author Dell
 */
public class Turnos extends JButton{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2693174049493639106L;
	private int idturno;
    
    Turnos(int idturno,String turno){
        super(turno);
        this.idturno=idturno;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setFont(new Font("ARIAL",Font.BOLD,40));
    }
    
    public int getIdTurno(){
        return idturno;
    }
    
}
