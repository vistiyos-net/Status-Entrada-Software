/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.impresion;

import interfaz.avisos.Avisos;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;

import net.vistiyos.db.MySQL;

import org.sql.apachederbylib.exception.NoDriverFoundException;
import org.sql.apachederbylib.exception.SQLSintaxError;

import registros.Indices.Indices;

/*
 *
 * @author Dell
 */
public class colaImpresion {
    
    static private PrinterJob cola=null;
    static private PageFormat pf;
    
    private static void setPrinterQueue(){
        cola = PrinterJob.getPrinterJob();
        pf = cola.defaultPage();
        Paper paper = new Paper();
        double margin = 0;
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pf.setPaper(paper);
    }
    
    static public void imprimirEntrada(int id) throws NoDriverFoundException, SQLException, SQLSintaxError{
        try{
            setPrinterQueue();
            impresionEntrada impresion=new impresionEntrada(id);
            cola.setPrintable(impresion,pf);
            for(int i=0;i<MySQL.getCopasNum(id);i++){
                cola.print();
            }
            Indices.indiceEntradas++;
        }catch(PrinterException ex){
           Avisos.mostrarError(null,"No se ha podido realizar la impresión.Inténtelo de nuevo más tarde.", "ERROR AL IMPRIMIR", false);
        }
    }
    
    static public void imprimirInvitacion(int invitaciones) throws NoDriverFoundException, SQLException, SQLSintaxError{
        try{
            setPrinterQueue();
            impresionInvitacion impresion=new impresionInvitacion();
            cola.setPrintable(impresion,pf);
            for(int i=0;i<invitaciones;i++){
                cola.print();
                Indices.indiceInvitaciones++;
            }
        }catch(PrinterException ex){
           System.out.println(ex.getMessage());
           Avisos.mostrarError(null,"No se ha podido realizar la impresión.Inténtelo de nuevo más tarde.", "ERROR AL IMPRIMIR", false);
        }
    }
    
    static public void imprimirTalonario(int num) throws NoDriverFoundException, SQLException, SQLSintaxError{
        try{
            setPrinterQueue();
            impresionTalonario impresion=new impresionTalonario();
            cola.setPrintable(impresion,pf);
            for(int i=0;i<num;i++){
                cola.print();
                Indices.indiceTalonario++;
            }
        }catch(PrinterException ex){
           Avisos.mostrarError(null,"No se ha podido realizar la impresión.Inténtelo de nuevo más tarde.", "ERROR AL IMPRIMIR", false);
        }
    }
    
     static public void imprimirCanjeo() throws NoDriverFoundException, SQLException, SQLSintaxError{
        try{
            setPrinterQueue();
            impresionCanjeo impresion=new impresionCanjeo();
            cola.setPrintable(impresion,pf);
            for(int i=0;i<MySQL.getEntradasCanjeo();i++){
                cola.print();
            }
            Indices.indiceCanjeo++;
        }catch(PrinterException ex){
           Avisos.mostrarError(null,"No se ha podido realizar la impresión.Inténtelo de nuevo más tarde.", "ERROR AL IMPRIMIR", false);
        }
    }
    
}
