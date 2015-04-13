package es.upm.dit.isst.factura.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String pais;
    private String total;
    private String totalIva; 
    
    public Factura(String name, String pais, String total, String totalIva){
    	this.name = name;
    	this.pais = pais;
    	this.total = total;
    	this.totalIva = totalIva;
    }
    
    public Long getId() {
		return id;
	}
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPais() {
        return pais;
    }
    
    public void setPais(String pais) {
    	this.pais = pais;
    }
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
    	this.total = total;
    }
    
    public String getTotalIva() {
        return totalIva;
    }
    
    public void setTotalIva(String totalIva) {
    	this.totalIva = totalIva;
    }
}
