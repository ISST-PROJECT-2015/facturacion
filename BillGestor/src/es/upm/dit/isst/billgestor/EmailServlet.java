package es.upm.dit.isst.billgestor;
import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;
import es.upm.dit.isst.billgestor.model.EmailUtility;
import es.upm.dit.isst.billgestor.model.Empresa;

public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String USER_NAME = "gestiondefacturas.isst";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "gestiondefacturas"; // GMail password
    private static String RECIPIENT2 = "";
    
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
throws IOException, ServletException {
		
		/*HttpSession session = req.getSession();
		String email = session.getAttribute("user").toString();
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		Empresa e = dao.getEnterprise(email);
		RECIPIENT2 = e.getEmail();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT2 }; // list of recipient email addresses
        String subject = "GEEFT: Request Limit Reached.";
        String body = "We inform you have reached the request limit. Please log into your user account and choose a new package plan. "
        		+ "Thank you.";

        EmailUtility.sendFromGMail(from, pass, to, subject, body);
		//System.out.println("Correo enviado a:"+ RECIPIENT1 + " , " + RECIPIENT2 + " , " + RECIPIENT3);
		resp.sendRedirect("/dashboard");*/
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "We inform you have reached the request limit. Please log into your user account and choose a new package plan.";
		
		Multipart mp = new MimeMultipart();

        
        

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("gestiondefacturas.isst@gmail.com", "GEEFT Admin"));
		    
		    HttpSession ses = req.getSession();
			String email = ses.getAttribute("user").toString();
			EmpresaDAO dao = EmpresaDAOImpl.getInstance();
			Empresa e = dao.getEnterprise(email);
			RECIPIENT2 = e.getEmail();
		    
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(RECIPIENT2, "User"));
		    msg.setSubject("Your Example.com account has been activated");
		   
		    MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(msgBody + "\n" + "<a href='http://1-dot-gestiondefacturas-isst.appspot.com'>Go to Website</a>", "text/html");
	        mp.addBodyPart(htmlPart);
	        msg.setContent(mp);
	        
	        Transport.send(msg);

		} catch (AddressException e) {
		    // ...
		} catch (MessagingException e) {
		    // ...
		}
		resp.sendRedirect("/configuration");
	}
}