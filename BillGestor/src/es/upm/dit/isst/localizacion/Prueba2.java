package es.upm.dit.isst.localizacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.*;

import es.upm.dit.isst.localizacion.modelo.Country;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAO;
import es.upm.dit.isst.localizacion.dao.LocalizacionDAOImpl;

public class Prueba2 extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Pillamos la IP
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
	  	   if (ipAddress == null){  
	  		   ipAddress = request.getRemoteAddr();
	  	   }
	  	
	  	//Creamos la URL para coger el JSON
	  	String url = "http://freegeoip.net/json/"+ipAddress;
	  	
	  	//Cojemos el JSON
	  	JSONObject contenido = readJsonFromUrl(url);
	  	
	  	//Tenemos las String del pais, countrycode,callback
	  	String nameWeb = contenido.getString("country_name");   
        String callback = request.getParameter("callback");
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        
        LocalizacionDAO dao = LocalizacionDAOImpl.getInstance();
        List<Country> paises = new ArrayList<Country>();
        paises = dao.getPaises("manu.alvarez.29");
        
        Country countryInfo = new Country("manu.alvarez.29", "Marte", "100");
        
        for (int i=0; i < paises.size(); i++){
        	Country paisBucle = paises.get(i);
        	String nameBucle = paisBucle.getName();
        	if (nameBucle.equals(nameWeb)){
        		countryInfo = paisBucle;
        	}
        }
        
        JsonElement countryObj = gson.toJsonTree(countryInfo);
        if(countryInfo.getName() == null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("countryInfo", countryObj);
        if(callback != null) {
            out.println(callback + "(" + myObj.toString() + ");");
        }
        else {
            out.println(myObj.toString());
        }
        out.close();
    }
	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	 }

	 public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
}
