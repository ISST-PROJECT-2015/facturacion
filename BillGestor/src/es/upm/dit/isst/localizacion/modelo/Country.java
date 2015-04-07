package es.upm.dit.isst.localizacion.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String autor;

	
    private String name;
    private String iva;
    
    public Country(String autor, String name, String iva){
    	this.autor = autor;
    	this.name = name;
    	this.iva = iva;
    }
    
    public Long getId() {
		return id;
	}
    
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIva() {
        return iva;
    }
    
    public void setIva(String iva) {
        this.iva = iva;
    }
}