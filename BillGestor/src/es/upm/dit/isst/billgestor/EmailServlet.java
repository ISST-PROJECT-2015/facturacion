package es.upm.dit.isst.billgestor;
import java.io.IOException;

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
    private static String RECIPIENT1 = "a.fllamas@alumnos.upm.es";
	
    private static String RECIPIENT2 = "";
    private static String RECIPIENT3 = "tito.9.24@gmail.com";
    
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		String email = session.getAttribute("user").toString();
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		Empresa e = dao.getEnterprise(email);
		RECIPIENT2 = e.getEmail();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT1 , RECIPIENT2 , RECIPIENT3 }; // list of recipient email addresses
        String subject = "GEEFT: Request Limit Reached.";
        String body = "We inform you have reach the request limit. Please log into your user account and choose a new package plan. "
        		+ "Thank you.";

        EmailUtility.sendFromGMail(from, pass, to, subject, body);
		System.out.println("Correo enviado a:"+ RECIPIENT1 + " , " + RECIPIENT2 + " , " + RECIPIENT3);
		resp.sendRedirect("/dashboard");
	}
}