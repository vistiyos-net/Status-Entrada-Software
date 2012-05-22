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
import java.util.Calendar;

import registros.Indices.Indices;

/**
 *
 * @author Dell
 */
public class impresionCanjeo implements Printable{

    @Override
   public int print (Graphics g, PageFormat f, int pageIndex)
   {
      switch (pageIndex)
      {
         case 0 : //P�gina 1: Dibujamos sobre g y luego lo pasamos a g2
             Calendar c=Calendar.getInstance();
             String fecha=c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR);
             g.setFont(new Font("ARIAL",Font.BOLD,20));
             g.drawString("FIESTA ESPECIAL",15,90);//Copa
             g.setFont(new Font("ARIAL",Font.BOLD,10));
             g.drawString(fecha, 147, 15);//Fecha
             g.setColor(Color.BLACK);
             g.drawRect(1, 1, 200, 165);
             g.drawString("FIESTA ESPECIAL #"+Indices.indiceCanjeo, 5, 15);//ID
             return PAGE_EXISTS; //La página 1 existe y se imprimirá
         default: 
             return NO_SUCH_PAGE;        //No se imprimirán más páginas
      }
   }
}
