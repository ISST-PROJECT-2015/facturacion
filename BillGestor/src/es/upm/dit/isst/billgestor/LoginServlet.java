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

public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		RequestDispatcher view = req.getRequestDispatcher("Login.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession();
		String email = checkNull(req.getParameter("email"));
		String password = checkNull(req.getParameter("password"));
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		if(dao.correctLogin(email, password)){
			session.setAttribute("user", email);
			resp.sendRedirect("/dashboard");
		}else{
			resp.sendRedirect("/");
		}
		
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
}
