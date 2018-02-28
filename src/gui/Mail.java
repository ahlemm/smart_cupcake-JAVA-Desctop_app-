/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author haifa ftouh
 */
public class Mail {
    public void sendMail(String userMail,String pass,String sujet,String contenu) throws MessagingException{
    
        
        String to = "allalah.elkateb@esprit.tn";
        String host = "smtp.gmail.com";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put( "mail.smtp.host", host );
        prop.put("mail.smtp.user", userMail);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");//587
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userMail));
         
        InternetAddress toAdresse = new InternetAddress(to);
                
        msg.setRecipient(Message.RecipientType.TO, toAdresse);
        msg.setSubject(sujet);
        msg.setContent(contenu,"text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        System.out.println(userMail);
        System.out.println(pass);
        transport.connect(host, userMail, pass);//transport.connect(host, userMail, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    
          
        
        
    }
    
}
    

