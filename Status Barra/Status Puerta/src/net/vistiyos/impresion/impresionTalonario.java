
package net.vistiyos.impresion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Calendar;

/**
 *
 * @author V�ctor Escobar
 */
public class impresionTalonario implements Printable{

    @Override
   public int print (Graphics g, PageFormat f, int pageIndex)
   {
      switch (pageIndex)
      {
         case 0 : 
             Calendar c=Calendar.getInstance();
             String fecha=c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR);
             g.setFont(new Font("ARIAL",Font.BOLD,20));
             g.drawString("TALONARIO VIP",22,90);//Copa
             g.setFont(new Font("ARIAL",Font.BOLD,10));
             g.drawString(fecha, 147, 15);//Fecha
             g.setColor(Color.BLACK);
             g.drawRect(1, 1, 200, 165);
             g.drawString("TAL�N #"+Indices.indiceTalonario, 5, 15);//ID
             return PAGE_EXISTS; 
         default: 
             return NO_SUCH_PAGE;        
      }
   }
}
