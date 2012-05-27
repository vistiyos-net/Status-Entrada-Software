
package net.vistiyos.impresion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Calendar;

import net.vistiyos.db.MySQL;

import com.onbarcode.barcode.EAN13;

/**
 *
 * @author Víctor Escobar
 */
public class impresionEntrada implements Printable{
    
    private int idEntrada;
    
    impresionEntrada(int id){
        this.idEntrada=id;
    }
    
    @Override
   public int print (Graphics g, PageFormat f, int pageIndex){
      switch (pageIndex){
         case 0 : 
		try {
			StringBuilder sb = new StringBuilder();
        	sb.append(MySQL.getIdTurnoActivo());
        	sb.append(MySQL.getIdBebida(idEntrada));
        	sb.append(idEntrada);
            Calendar c=Calendar.getInstance();
            if(c.get(Calendar.DAY_OF_MONTH) < 10){
            	sb.append(0);
            }
            sb.append(c.get(Calendar.DAY_OF_MONTH));
            if((c.get(Calendar.MONTH)+1) < 10){
            	sb.append(0);
            }
            sb.append((c.get(Calendar.MONTH)+1));
            sb.append(c.get(Calendar.YEAR));
            EAN13 barcode = new EAN13(); 
            barcode.setData(sb.toString());
            BufferedImage codigo_barras = barcode.drawBarcode();
            
		} catch (Exception e) {

			e.printStackTrace();
		}
            /*g.setFont(new Font("ARIAL",Font.BOLD,MySQL.getFontSize(idEntrada)));
            g.drawString(MySQL.getBebida(idEntrada),MySQL.getXPosition(idEntrada),MySQL.getYPosition(idEntrada));//Copa
            g.setFont(new Font("ARIAL",Font.BOLD,10));
            g.drawString(fecha, 147, 15);//Fecha
            g.setColor(Color.BLACK);
            g.drawRect(1, 1, 200, MySQL.getHeight(idEntrada));
            g.drawString("ENTRADA #"+Indices.indiceEntradas, 5, 15);//ID
            */
            return PAGE_EXISTS; 
         default: return NO_SUCH_PAGE;
      }
   }
}
