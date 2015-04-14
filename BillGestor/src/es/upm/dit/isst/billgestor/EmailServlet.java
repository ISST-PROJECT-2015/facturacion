package es.upm.dit.isst.billgestor;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
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
import es.upm.dit.isst.factura.dao.FacturaDAO;
import es.upm.dit.isst.factura.dao.FacturaDAOImpl;
import es.upm.dit.isst.factura.model.Factura;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAO;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAOImpl;

public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String USER_NAME = "gestiondefacturas.isst";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "gestiondefacturas"; // GMail password
    private static String RECIPIENT = "";
    
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
throws IOException, ServletException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		//check session
	    HttpSession ses = req.getSession();
		if(ses.getAttribute("user") == null ){
			resp.sendRedirect("/");
			return;
		}
		String msgBody = "Your report has been sent";
		
		Multipart mp = new MimeMultipart();        
		
		LocalizacionDAO localizacionDAO = LocalizacionDAOImpl.getInstance();
		FacturaDAO facturaDAO = FacturaDAOImpl.getInstance();
		

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("gestiondefacturas.isst@gmail.com", "GEEFT Admin"));
		    
			String email = ses.getAttribute("user").toString();
			EmpresaDAO dao = EmpresaDAOImpl.getInstance();
			Empresa e = dao.getEnterprise(email);
			RECIPIENT = e.getEmail();
			String name = e.getName();
			
			//Creamos el email rellenando los campos		    
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress(RECIPIENT, name));
		    msg.setSubject("GEEFT: User Report");
		   
		    MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(msgBody + "\n" + "<a href='http://1-dot-gestiondefacturas-isst.appspot.com'>Go to Website</a>", "text/html");
	        mp.addBodyPart(htmlPart);
	        
	        //CSV File Design
	        StringBuffer buffer = new StringBuffer();
	        buffer.append("Country, Total sales, Total sales with taxes included\n");
	        for(String countryOfList : localizacionDAO.getCountriesNames()){
				if(facturaDAO.tieneFacturaPais(name,countryOfList)){
					Factura f = facturaDAO.getFactura(name);
					String country = f.getPais();
					String total = f.getTotal();
					String totalIva = f.getTotalIva();
					buffer.append(country+", "+total+", "+totalIva+"\n");	
			}
	        }

	        byte[] bytes = buffer.toString().getBytes();

	        // This will suggest a filename for the browser to use
	        resp.addHeader("Content-Disposition", "attachment; filename=\"myFile.csv\"");
	        resp.getOutputStream().write(bytes, 0, bytes.length);
	                
	        MimeBodyPart attachment = new MimeBodyPart();
	        attachment.setFileName(name + "-SalesReport.csv");
	        DataSource src = new ByteArrayDataSource(bytes, "text/comma-separated-values"); 
            attachment.setDataHandler(new DataHandler(src));	        
            mp.addBodyPart(attachment);

	        msg.setContent(mp);
	        
	        Transport.send(msg);

		} catch (AddressException e) {
		    // ...
		} catch (MessagingException e) {
		    // ...
		}
		resp.sendRedirect("/dashboard");
	}
}