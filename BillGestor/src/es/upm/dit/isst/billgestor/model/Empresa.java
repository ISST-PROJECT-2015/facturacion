package es.upm.dit.isst.billgestor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	/**
	 * Datos que necesitamos de la empresa.
	 */
	private Long id;
	private String name;
	private String email;
	private String domain;
	private String password;
	private String product;
	private String language;
	private int warning_request;
	private int remaining_request; 
	
	public enum Plan{
		FREE, STARTUP, PREMIUM, GOLD, NO_PLAN
	}

	private Plan plan;
	
	public Empresa(String name, String email, String domain, String password, String product, String language) {
		this.name = name;
		this.email = email;
		this.domain = domain;
		this.password = password;
		this.product = product;
		this.language = language;
		remaining_request = 50; // Número de peticiones iniciales en el plan free.
		warning_request = 10;
		plan = Plan.FREE; // Empezamos siempre con el plan free.
		
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

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getRemainingRequest() {
		return remaining_request;
	}

	public void setRemainingRequest(int remaining_request) {
		this.remaining_request = remaining_request;
	}
	
	public int getWarningRequest() {
		return warning_request;
	}

	public void setWarningRequest(int warning_request) {
		this.warning_request = warning_request;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
