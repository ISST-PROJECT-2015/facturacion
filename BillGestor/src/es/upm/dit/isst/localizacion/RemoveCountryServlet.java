package es.upm.dit.isst.localizacion;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.localizacion.dao.LocalizacionDAO;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAOImpl;

public class RemoveCountryServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String id = req.getParameter("id");
    LocalizacionDAO dao = LocalizacionDAOImpl.getInstance();
    dao.remove(Long.parseLong(id));
    resp.sendRedirect("/main");
  }
} 
