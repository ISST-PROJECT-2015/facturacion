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
import es.upm.dit.isst.billgestor.model.Empresa;
import es.upm.dit.isst.factura.dao.FacturaDAO;
import es.upm.dit.isst.factura.dao.FacturaDAOImpl;
import es.upm.dit.isst.factura.model.Factura;

public class Localizacion extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Cojemos el host, el total y el div exito
	  	String host = request.getParameter("host");
	  	String totalString = request.getParameter("total");
	  	String exito = request.getParameter("exito");
	  	String si = "si";
	  	String so = "SO"; //geolocalizacion soportada
	  	String codigoGeo = request.getParameter("codigoGeo");
	  	String latitude = request.getParameter("latitude");
	  	String longitude = request.getParameter("longitude");
	  	double total = Double.parseDouble(totalString);
	  	boolean geo = false;
	  	boolean ip = false;
    	
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
		  	
		  	//Cojemos de ese JSON el codigo del pais
		  	String countryCode = contenido.getString("country_code");   
		  	
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
	        
	        //En caso de no estar en la base de datos asignará este 
	        Country countryInfo = new Country("gestiondefacturas.isst", "FueraUE", "100", "NLO");
	        
	        //Busca el nombre del pais en la base de datos. Si no lo encuentra, pone el por defecto.
	        for (int i=0; i < paises.size(); i++){
	        	Country paisBucle = paises.get(i);
	        	String codeBucle = paisBucle.getCode();
	        	if (codeBucle.equals(countryCode)){
	        		countryInfo = paisBucle;
	        		ip = true;
	        	}
	        }
	        
	        //Calculamos el total + iva
	        DecimalFormat resultado = new DecimalFormat("#.##");
	        double iva = Double.parseDouble(countryInfo.getIva());
	        double totalIva = total * ((iva/100)+1);
	        String totalIvaString = resultado.format(totalIva);
	        
	        //Creamos JsonElement y les añadimos el pais y el total+iva
	        JsonElement countryObj = gson.toJsonTree(countryInfo);
	        JsonElement totalIvaObj = gson.toJsonTree(totalIvaString);
	        
		  	//El caso de que lo hemos geolocalizado ponemos el nombre como null para que escriba ERROR
		  	if (codigoGeo.equals(so)){
		  		String urlGeo = "http://api.geonames.org/countryCodeJSON?formatted=true&lat="+latitude+"&lng="+longitude+"&username=gestiondefacturas&style=full";
		  		JSONObject contenidoGeo = readJsonFromUrl(urlGeo);
		  		String countryCodeGeo = contenidoGeo.getString("countryCode");  
		  		if (countryCode.equals(countryCodeGeo) == false){
		  			countryInfo.setName(null);
		  			ip = false;
		  			geo = false;
		  		}else{
		  			ip = true;
		  			geo = true;
		  		}
		  	}else{
		  		geo = false;
		  	}
		  		  	
		  	String localizadoPor ="ERROR";
	        if ((ip == false) && (geo == false)){
	        	myObj.addProperty("success", false);
	        	localizadoPor = "Hemos obtenido ua diferencia entre su localizacion IP y su geolocalizacion";
	        }
	        if ((ip == true) && (geo == false)){
	        	localizadoPor = "Localizado mediante IP";
	        }
	        if ((ip == true) && (geo == true)){
	        	localizadoPor = "Localizado mediante IP y geolocalizacion";
	        }
	        if ((ip == false) && (geo == true)){
	        	localizadoPor = "Localizado mediante geolocalizacion";
	        }
	        
	        JsonElement localizadoPorObj = gson.toJsonTree(localizadoPor);
		  	
	        //Comprobamos si el nombre del pais localizado es nulo
	        if(countryInfo.getName() == null){
	            myObj.addProperty("success", false); //Escribirá ERROR
	        }
	        else {
	            myObj.addProperty("success", true);  //Se ejecutará lo que hay en if(data.succes)
	            myObj.add("countryInfo", countryObj);
	            myObj.add("totalIva", totalIvaObj);
	            myObj.add("localizadoPor",localizadoPorObj);
	        }    	        
	        
	        //Escribimos la respuesta
	        if(callback != null) {
	            out.println(callback + "(" + myObj.toString() + ");");
	        }
	        else {
	            out.println(myObj.toString());
	        }
	        out.close();
	        
	    	//Comprobamos si estamos en el caso de que la operacion se ha finalizado
			if (exito.equals(si)){
				//decrementamos una
				daoE.decreaseOneRequestDomain(host);
				
				//accedemos a la base de datos de las facturas
				FacturaDAO daoF = FacturaDAOImpl.getInstance();
	
				//obtenemos la empresa y su nombre
				Empresa empresa = daoE.getEnterpriseDomain(host);
				String nombreEmpresa = empresa.getName();
				
				//cojemosel pais al que hay que facturar
				String pais = countryInfo.getName();
				
				// si ya hay una factura de ese pais
				if (daoF.tieneFacturaPais(nombreEmpresa, pais) == true){
					//cogemos la factura
					Factura factura = daoF.getFacturaPais(nombreEmpresa, pais);
					//y sus datos de total y total+iva
					String totalPaisString = factura.getTotal();
					String totalIvaPaisString = factura.getTotalIva();
					Double totalPais = Double.parseDouble(totalPaisString);
					Double totalIvaPais = Double.parseDouble(totalIvaPaisString);
					//les sumamos los nuevos y los guardamos
					totalPais = totalPais + total;
					totalIvaPais = totalIvaPais + totalIva;
					totalPaisString = resultado.format(totalPais);
					totalIvaPaisString = resultado.format(totalIvaPais);
					daoF.setTotalFacturaPais(nombreEmpresa, pais, totalPaisString);
					daoF.setTotalIvaFacturaPais(nombreEmpresa, pais, totalIvaPaisString);
				}else{
					//en caso de que todavía no hubiera una, la creamos
					daoF.add(nombreEmpresa, pais, totalString, totalIvaString);
				}
			}
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