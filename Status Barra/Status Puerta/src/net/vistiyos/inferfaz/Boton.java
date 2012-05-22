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
public class Boton extends JButton{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2505719715527237796L;
	private int identrada;
    private int idturno;
    
    Boton(int identrada,int idturno,String msg){
        super(msg);
        this.identrada=identrada;
        this.idturno=idturno;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setFont(new Font("ARIAL",Font.BOLD,40));
    }
    
    public float getPrice(){
        float res=0;
        return res;
    }
    
    public int getIdEntrada(){
        return this.identrada;
    }
    
    public int getIdTurno(){
        return this.idturno;
    }
}
