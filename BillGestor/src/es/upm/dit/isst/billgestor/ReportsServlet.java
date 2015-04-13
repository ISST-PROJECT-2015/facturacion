package es.upm.dit.isst.billgestor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;
import es.upm.dit.isst.billgestor.model.Empresa;
import es.upm.dit.isst.factura.dao.FacturaDAO;
import es.upm.dit.isst.factura.dao.FacturaDAOImpl;
import es.upm.dit.isst.factura.model.Factura;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAO;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAOImpl;
import es.upm.dit.isst.localizacion.modelo.Country;


public class ReportsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		String email = session.getAttribute("user").toString();
	
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		Empresa e = dao.getEnterprise(email);
		
		int nreq= e.getRemainingRequest();
		
		req.getSession().setAttribute("nreq", nreq);
		
		String logout = checkNull(req.getParameter("logout"));
		
		/*
		 * Compruebo si se ha llamado al logout
		 */
		if(logout.equals("yes")){
			session.invalidate();
			resp.sendRedirect("/");
			return;
		}
		/*
		 * Compruebo si existe sesión.
		 */
		if(session.getAttribute("user") == null ){
			resp.sendRedirect("/");
			return;
		}
		//String email = session.getAttribute("user").toString();
		req.getSession().setAttribute("email", email);
		
		RequestDispatcher view = req.getRequestDispatcher("Reports.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		HttpSession session = req.getSession();
	
		if(session.getAttribute("user") == null ){
			resp.sendRedirect("/");
			return;
		}
		
		String email = session.getAttribute("user").toString();
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		Empresa e = dao.getEnterprise(email);
		String name = e.getName();
		
		FacturaDAO facturaDAO = FacturaDAOImpl.getInstance();
		
		
		LocalizacionDAO localizacionDAO = LocalizacionDAOImpl.getInstance();
		
		if(req.getParameter("report")!=null){
	        
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
	        // Add more CSV data to the buffer

	        byte[] bytes = buffer.toString().getBytes();

	        // This will suggest a filename for the browser to use
	        resp.addHeader("Content-Disposition", "attachment; filename='"+name+"-SalesReport.csv'");

	        resp.getOutputStream().write(bytes, 0, bytes.length);
			resp.flushBuffer();	
		//	resp.sendRedirect("/dashboard");
			
		}
		
		RequestDispatcher view = req.getRequestDispatcher("Reports.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	
}

private String checkNull(String s) {
	if (s == null) {
		return "";
	}
	return s;
}

}
