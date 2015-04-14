package es.upm.dit.isst.billgestor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.billgestor.dao.*;
import es.upm.dit.isst.billgestor.model.*;
import es.upm.dit.isst.factura.dao.FacturaDAO;
import es.upm.dit.isst.factura.dao.FacturaDAOImpl;
import es.upm.dit.isst.factura.model.Factura;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAO;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAOImpl;

public class EmailAgenciaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private static String RECIPIENT = "gestiondefacturas.isst@gmail.com";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		LocalizacionDAO localizacionDAO = LocalizacionDAOImpl.getInstance();
		FacturaDAO facturaDAO = FacturaDAOImpl.getInstance();
		
	    String msgBody = "The trimestral report has been sent";
		Multipart mp = new MimeMultipart();        

			try {
			    Message msg = new MimeMessage(session);
			    msg.setFrom(new InternetAddress("gestiondefacturas.isst@gmail.com", "GEEFT Admin"));
			    
			    
			    //Creamos el email rellenando los campos		    
			    msg.addRecipient(Message.RecipientType.TO,
			     new InternetAddress(RECIPIENT, "Agencia Tributaria"));
			    msg.setSubject("GEEFT: Trimestral Report");
			   
			    MimeBodyPart htmlPart = new MimeBodyPart();
		        htmlPart.setContent(msgBody + "\n" + "<a href='http://1-dot-gestiondefacturas-isst.appspot.com'>Go to Website</a>", "text/html");
		        mp.addBodyPart(htmlPart);
		        
		        //CSV File Design
		        StringBuffer buffer = new StringBuffer();
		        buffer.append("Country, Total sales, Total sales with taxes included\n");
		        
		        //Change how to generate CSV for all enterprises
		       /* for(String countryOfList : localizacionDAO.getCountriesNames()){
					if(facturaDAO.tieneFacturaPais("Antonio",countryOfList)){
						Factura f = facturaDAO.getFactura("Antonio");
						String country = f.getPais();
						String total = f.getTotal();
						String totalIva = f.getTotalIva();
						buffer.append(country+", "+total+", "+totalIva+"\n");	
				}
		        }*/

		        byte[] bytes = buffer.toString().getBytes();

		        // This will suggest a filename for the browser to use
		                
		        MimeBodyPart attachment = new MimeBodyPart();
		        attachment.setFileName("Agencia" + "-SalesReport.csv");
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
	}
}
