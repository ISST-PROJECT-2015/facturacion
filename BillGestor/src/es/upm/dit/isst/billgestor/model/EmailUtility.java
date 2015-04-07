 package es.upm.dit.isst.billgestor.model;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;

public class EmailUtility {
	public static void sendFromGMail(String from, String to, String subject, String body) {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Multipart mp = new MimeMultipart();     
        try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from, "GEEFT Admin"));
		    
		    
		    
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(to, "User"));
		    msg.setSubject(subject);
		   
		    MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(body + "\n" + "<a href='http://1-dot-gestiondefacturas-isst.appspot.com'>Go to Website</a>", "text/html");
	        mp.addBodyPart(htmlPart);
	        msg.setContent(mp);
	        
	        Transport.send(msg);

		} catch (AddressException e) {
		    // ...
		} catch (MessagingException e) {
		    // ...
		} catch (UnsupportedEncodingException e) {
			// 
			e.printStackTrace();
		}

	}
}
