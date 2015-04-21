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


public class DashboardServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession();
		
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
		
		String email = session.getAttribute("user").toString();
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		Empresa e = dao.getEnterprise(email);
		String name = e.getName();
		String domain =e.getDomain();
		String product =e.getProduct();
		String language =e.getLanguage();
		String warning = String.valueOf(e.getWarningRequest());
		String plan = e.getPlan().name();
		int nreq= e.getRemainingRequest();
		
		req.getSession().setAttribute("name", name);
		req.getSession().setAttribute("domain", domain);
		req.getSession().setAttribute("product", product);
		req.getSession().setAttribute("plan", plan);
		req.getSession().setAttribute("language", language);
		req.getSession().setAttribute("nreq", nreq);
		req.getSession().setAttribute("warning", warning);
		req.getSession().setAttribute("nreqplan", getRequestPlan(plan));
		RequestDispatcher view = req.getRequestDispatcher("Dashboard.jsp");
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
	
	private int getRequestPlan(String plan){
		if ("FREE".equals(plan )) {
			return 50;
		} else if ("STARTUP".equals(plan )) {
			return 100;
		} else if ("PREMIUM".equals(plan)) {
			return 1000;
		} else if ("GOLD".equals(plan )) {
			return 10000;
		} else {
			return 1;
		}
	}
}
