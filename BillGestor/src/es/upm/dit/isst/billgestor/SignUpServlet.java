package es.upm.dit.isst.billgestor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;
import es.upm.dit.isst.billgestor.model.Empresa;

public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		RequestDispatcher view = req.getRequestDispatcher("SignUp.jsp");
		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("Creating new enterprise ");
		String name = checkNull(req.getParameter("name"));
		String domain = checkNull(req.getParameter("domain"));
		String email = checkNull(req.getParameter("email"));
		String password = checkNull(req.getParameter("password"));
		String product = checkNull(req.getParameter("product"));
		String language = checkNull(req.getParameter("language"));
		EmpresaDAO dao = EmpresaDAOImpl.getInstance();
		dao.add(name, email, domain, password, product, language);
		resp.sendRedirect("/");
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "nada";
		}
		return s;
	}

}
