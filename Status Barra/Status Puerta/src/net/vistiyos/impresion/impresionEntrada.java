/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.impresion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.vistiyos.db.MySQL;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

import registros.Indices.Indices;

/**
 *
 * @author Dell
 */
public class impresionEntrada implements Printable{
    
    private int idEntrada;
    
    impresionEntrada(int id){
        this.idEntrada=id;
    }
    
    @Override
   public int print (Graphics g, PageFormat f, int pageIndex)
   {
      switch (pageIndex)
      {
         case 0 : //Página 1: Dibujamos sobre g y luego lo pasamos a g2
        try {
            Calendar c=Calendar.getInstance();
            String fecha=c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR);
            g.setFont(new Font("ARIAL",Font.BOLD,MySQL.getFontSize(idEntrada)));
            g.drawString(MySQL.getBebida(idEntrada),MySQL.getXPosition(idEntrada),MySQL.getYPosition(idEntrada));//Copa
            g.setFont(new Font("ARIAL",Font.BOLD,10));
            g.drawString(fecha, 147, 15);//Fecha
            g.setColor(Color.BLACK);
            g.drawRect(1, 1, 200, MySQL.getHeight(idEntrada));
            g.drawString("ENTRADA #"+Indices.indiceEntradas, 5, 15);//ID
        } catch (NoDriverFoundException ex) {
            Logger.getLogger(impresionEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(impresionEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLSintaxError ex) {
            Logger.getLogger(impresionEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PAGE_EXISTS; //La página 1 existe y se imprimirá
         default: return NO_SUCH_PAGE;        //No se imprimirán más páginas
      }
   }
}
