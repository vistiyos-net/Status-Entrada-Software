/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vistiyos.util;

import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class EnviarMail {
    
    static public void enviarEMail(){
        try
        {
          // se obtiene el objeto Session. La configuraciÃ³n es para
          // una cuenta de gmail.
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "discotecastatus@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText("");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            Calendar c=Calendar.getInstance();
            String file="FACTURA-"+c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR)+".pdf";
            adjunto.setDataHandler(new DataHandler(new FileDataSource("FACTURAS\\"+file)));
            adjunto.setFileName(file);

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("discotecastatus@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("discotecastatus@gmail.com"));
            message.setSubject("Factura");
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect("discotecastatus@gmail.com", "arquiag1989");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	public static void enviarPassword(String password) {
		try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "discotecastatus@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("discotecastatus@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("discotecastatus@gmail.com"));
            message.setSubject("Recuperacion de Clave");
            message.setText(
                "La contraseña de tu software de gestión es: "+password);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("discotecastatus@gmail.com", "arquiag1989");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	
	}
    
}
