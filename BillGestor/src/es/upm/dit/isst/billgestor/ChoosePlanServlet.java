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


public class ChoosePlanServlet extends HttpServlet {

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
		
		String language = req.getParameter("l");
	
		
		 if(language!=null){	
			 if (language.equals("es")){
			
				RequestDispatcher view = req.getRequestDispatcher("ChoosePlan_es.jsp");
				try {
					view.forward(req, resp);
				} catch (ServletException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}else {
			
			RequestDispatcher view = req.getRequestDispatcher("ChoosePlan.jsp");
			try {
				view.forward(req, resp);
			} catch (ServletException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
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
		String language = req.getParameter("l");
		
		
		if (language.equals("es")){
			if(req.getParameter("startup")!=null){
				dao.increaseRequests(100, email);
				resp.sendRedirect("/dashboard?l=es");
				return;
			}
			if(req.getParameter("premium")!=null){
				dao.increaseRequests(1000, email);
				resp.sendRedirect("/dashboard?l=es");
				return;
			}
			if(req.getParameter("gold")!=null){
				dao.increaseRequests(10000, email);
				resp.sendRedirect("/dashboard?l=es");
				return;
			}
		} else {
			if(req.getParameter("startup")!=null){
				dao.increaseRequests(100, email);
				resp.sendRedirect("/dashboard");
				return;
			}
			if(req.getParameter("premium")!=null){
				dao.increaseRequests(1000, email);
				resp.sendRedirect("/dashboard");
				return;
			}
			if(req.getParameter("gold")!=null){
				dao.increaseRequests(10000, email);
				resp.sendRedirect("/dashboard");
				return;
			}
		}
		
		
		
		
		
		
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
