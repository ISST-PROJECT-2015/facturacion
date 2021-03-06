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
import java.text.DecimalFormat;

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
import es.upm.dit.isst.billgestor.dao.EmpresaDAO;
import es.upm.dit.isst.billgestor.dao.EmpresaDAOImpl;

public class Prueba3 extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Cojemos el host, y el total
	  	String host = request.getParameter("host");
	  	String totalString = request.getParameter("total");
	  	double total = Double.parseDouble(totalString);
    	
	  	//Acudimos a la base de datos de EMPRESA
	  	EmpresaDAO daoE = EmpresaDAOImpl.getInstance();
	  	
	  	//Si el host esta registrado
	  	if (daoE.isDomainRegistered(host) == true){
	  		
	    	//Cogemos la IP
	    	String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		  	   if (ipAddress == null){  
		  		   ipAddress = request.getRemoteAddr();
		  	   }
		  
		  	//Creamos la URL para coger el JSON
		  	String url = "http://freegeoip.net/json/"+ipAddress;
		  	
		  	//Cojemos el JSON
		  	JSONObject contenido = readJsonFromUrl(url);
		  	
		  	//Cojemos de ese JSON el nombre del pais
		  	String countryName = contenido.getString("country_name");   
		  	
		  	//Cojemos el callback y creamos el PrintWriter donde pondremos la respuesta
		  	String callback = request.getParameter("callback");
	        PrintWriter out = response.getWriter();     
	        
	        //Creamos un GSOn y un JsonObject
	        Gson gson = new Gson(); 
	        JsonObject myObj = new JsonObject();
	        
	        //Recuperamos los paises de la base de datos
	        LocalizacionDAO daoL = LocalizacionDAOImpl.getInstance();
	        List<Country> paises = new ArrayList<Country>();
	        paises = daoL.getPaises("gestiondefacturas.isst");
	        
	        //En caso de no estar en la base de datos asignar� este 
	        Country countryInfo = new Country("gestiondefacturas.isst", "No Localizado", "100", "NLO");
	        
	        //Busca el nombre del pais en la base de datos. Si no lo encuentra, pone el por defecto.
	        for (int i=0; i < paises.size(); i++){
	        	Country paisBucle = paises.get(i);
	        	String nameBucle = paisBucle.getName();
	        	if (nameBucle.equals(countryName)){
	        		countryInfo = paisBucle;
	        	}
	        }
	        
	        
	        //Calculamos el total + iva
	        double iva = Double.parseDouble(countryInfo.getIva());
	        double totalIva = total * ((iva/100)+1);
	        DecimalFormat resultado = new DecimalFormat("#.##");
	        String totalIvaString = resultado.format(totalIva);
	        
	        //Creamos JsonElement y les a�adimos el pais y el total+iva
	        JsonElement countryObj = gson.toJsonTree(countryInfo);
	        JsonElement totalIvaObj = gson.toJsonTree(totalIvaString);
	       
	        //Comprobamos si el nombre del pais localizado es nulo
	        if(countryInfo.getName() == null){
	            myObj.addProperty("success", false); //Escribir� ERROR
	        }
	        else {
	            myObj.addProperty("success", true);  //Se ejecutar� lo que hay en if(data.succes)
	            myObj.add("countryInfo", countryObj);
	            myObj.add("totalIva", totalIvaObj);
	        }
	        
	        //Escribimos la respuesta
	        if(callback != null) {
	            out.println(callback + "(" + myObj.toString() + ");");
	        }
	        else {
	            out.println(myObj.toString());
	        }
	        out.close();
	  	}
	  	else{
	  		PrintWriter out = response.getWriter();  
	  		JsonObject myObj = new JsonObject();
	  		myObj.addProperty("success", false);
	  		String callback = request.getParameter("callback");
	  		if(callback != null) {
	            out.println(callback + "(" + myObj.toString() + ");");
	        }
	        else {
	            out.println(myObj.toString());
	        }
	        out.close();
	  	}
    }
	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	 }

	 private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try{
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    }
	    finally{
	      is.close();
	    }
	 }
}