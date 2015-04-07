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


public class ConfigurationServlet extends HttpServlet {

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
		int nreq= e.getRemainingRequest();
		req.getSession().setAttribute("nreq", nreq);
		int war = (int)Math.ceil(nreq*1/100);
		req.getSession().setAttribute("nreqwar", war);
		war = (int)Math.ceil(nreq*5/100);
		req.getSession().setAttribute("nreqwarr", war);
		war = (int)Math.ceil(nreq*10/100);
		req.getSession().setAttribute("nreqwarrr", war);
		war = (int)Math.ceil(nreq*25/100);
		req.getSession().setAttribute("nreqwarrrr", war);
		war = (int)Math.ceil(nreq*50/100);
		req.getSession().setAttribute("nreqwarrrrr", war);
		
		
		RequestDispatcher view = req.getRequestDispatcher("Configuration.jsp");
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
			

			if(req.getParameter("subm")!=null){
				int remaing_request= e.getRemainingRequest();
				int warning_request = 0;
				int percent = 0;
				if(req.getParameter("product").equals("one")) percent = 1;
				if(req.getParameter("product").equals("five")) percent = 5;
				if(req.getParameter("product").equals("ten")) percent = 10;
				if(req.getParameter("product").equals("twentyfive")) percent = 25;
				if(req.getParameter("product").equals("fifty")) percent = 50;
				warning_request = (int)(Math.ceil(remaing_request*percent/100));
				dao.setWarningRequest(warning_request, email);
				resp.sendRedirect("/dashboard");
				return;
			}
			
			if(req.getParameter("delete")!=null){
				dao.remove(e.getId());
				session.invalidate();
				resp.sendRedirect("/");
				return;
			}
			
			RequestDispatcher view = req.getRequestDispatcher("Configuration.jsp");
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
