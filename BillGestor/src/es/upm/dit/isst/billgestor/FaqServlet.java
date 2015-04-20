package es.upm.dit.isst.billgestor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;
import es.upm.dit.isst.billgestor.model.Empresa;

@SuppressWarnings("serial")
public class FaqServlet extends HttpServlet {
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
		
		
		RequestDispatcher view = req.getRequestDispatcher("Faq.jsp");
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
			


			
			if(req.getParameter("delete")!=null){
				dao.remove(e.getId());
				session.invalidate();
				resp.sendRedirect("/");
				return;
			}
			
			RequestDispatcher view = req.getRequestDispatcher("Faq.jsp");
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