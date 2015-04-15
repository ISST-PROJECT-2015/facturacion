package es.upm.dit.isst.factura.dao;

import java.util.List;

import es.upm.dit.isst.factura.model.Factura;

public interface FacturaDAO {

	public void add(String name, String pais, String total, String totalIva);  //cliente
	
	public List<String> getEnterpriseList();
	
	public Factura getFactura(String name);
	
	public boolean tieneFacturaPais(String name, String pais); //cliente
	
	public Factura getFacturaPais(String name, String pais);  //cliente
	
	public void setTotalFacturaPais(String name, String pais, String total);  //cliente
	
	public void setTotalIvaFacturaPais(String name, String pais, String totalIva);  //cliente
	
}
