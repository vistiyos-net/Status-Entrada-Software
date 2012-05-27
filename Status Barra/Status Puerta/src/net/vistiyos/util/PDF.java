
package net.vistiyos.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import net.vistiyos.db.MySQL;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Víctor Escobar
 */
public class PDF {
    
    static private ArrayList<String> datosNom=new ArrayList<String>();
    static private ArrayList<String> datos=new ArrayList<String>();
    
    public static void generatePDF() throws BadElementException, MalformedURLException, IOException, SQLException{
    	datosNom.clear();
    	datos.clear();
        Calendar c=Calendar.getInstance();
        String fecha="";
        fecha=c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR);
        String encabezado="INFORME DE GANANCIAS DE "+fecha+"\n\n\n";
        Font fuente=new Font(Font.getFamily("ARIAL"),22,Font.BOLD);
        Image imagen=Image.getInstance("imagenes\\logoPDF.png");
        imagen.scalePercent(25);
        imagen.setAlignment(Image.LEFT | Image.TEXTWRAP);
        try{
            Paragraph linea=new Paragraph(encabezado,fuente);
            linea.setAlignment(Paragraph.ALIGN_JUSTIFIED_ALL);
            PdfPTable tabla=new PdfPTable(2);
            tabla.setWidthPercentage(100);
            Document documento = new Document(PageSize.A4);
            String file="FACTURA-"+fecha+".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream("FACTURAS\\"+file));
            PdfPCell celda1 =new PdfPCell (new Paragraph("Concepto",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
            PdfPCell celda2 =new PdfPCell (new Paragraph("Valor",FontFactory.getFont("arial",10,Font.BOLD,BaseColor.RED)));
            documento.open(); 
            documento.add(imagen); 
            documento.add(linea);  
            tabla.addCell(celda1); 
            tabla.addCell(celda2);
            getCabeceras();
            getDatos();
            for(int i=0;i<datosNom.size();i++){ 
                tabla.addCell(datosNom.get(i)); 
                tabla.addCell(datos.get(i)); 
            } 
            documento.add(tabla);
            documento.close();
        }catch(DocumentException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE); 
            return;
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE); 
            return;
        }
    }
    
    private static void getCabeceras() throws SQLException{
        datosNom.add("Suma Real en Caja");
        datosNom.add("Suma Pago de Tarjeta");
        datosNom.add("Suma teórica de Caja");
        ArrayList<Map<String,String>> entradas=MySQL.getEntradas();
        Iterator<Map<String, String>> it=entradas.iterator();
        while(it.hasNext()){
            Map<String,String> e=it.next();
            datosNom.add(e.get("NOMBRE"));
        }
        datosNom.add("Número total de entradas");
        datosNom.add("Número total de tickets de copas");
        datosNom.add("Número total de tickets de cervezas");
        datosNom.add("Número de Talonario VIP y su importe");
        datosNom.add("Fiestas Especiales");
        datosNom.add("Invitaciones");
        datosNom.add("Caja refrescos");
        datosNom.add("Caja cervezas");
        datosNom.add("Botellas alcohol");
    }
    
    private static void getDatos() throws SQLException{
        float caja=MySQL.getRegistro()+(MySQL.getCanjeo()*Indices.indiceCanjeoSIM)+(MySQL.getTalonario()*Indices.indiceTalonarioM);
        datos.add(Float.toString(caja)+"€");
        float tarjeta=MySQL.getTarjeta()+(MySQL.getCanjeo()*Indices.indiceCanjeoSIT+(MySQL.getTalonario()*Indices.indiceTalonarioT));
        datos.add(Float.toString(tarjeta)+"€");
        datos.add(Float.toString(caja+tarjeta)+"€");
        ArrayList<Map<String,String>> dat=MySQL.getEntradas();
        Iterator<Map<String, String>> it=dat.iterator();
        while(it.hasNext()){
            Map<String,String> map=it.next();
            int id=Integer.parseInt(map.get("IDENTRADA"));
            datos.add(Integer.toString(MySQL.getCantidad(id)));
        }
        datos.add(Integer.toString(MySQL.getCantidadTotal()));
        datos.add(Integer.toString(MySQL.getCopas()+Indices.indiceTalonario-1+MySQL.getEntradasCanjeo()*(Indices.indiceCanjeoSI-1+Indices.indiceCanjeoNO-1)));
        datos.add(Integer.toString(MySQL.getCervezas()));
        datos.add(Integer.toString(Indices.indiceTalonario-1)+" Talones sumando un total de "+Float.toString((Indices.indiceTalonario-1)*MySQL.getTalonario()));
        datos.add("Canjeadas:"+(Indices.indiceCanjeoNO-1)+" -- Pagadas:"+(Indices.indiceCanjeoSI-1));
        datos.add(MySQL.getInvitaciones());
        datos.add(Float.toString(((MySQL.getCopas()+Indices.indiceTalonario-1)/24)));
        datos.add(Float.toString(MySQL.getCervezas()/24));
        datos.add(Float.toString(((MySQL.getCopas()+Indices.indiceTalonario-1)/12)));
    }
}
