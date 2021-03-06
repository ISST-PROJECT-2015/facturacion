package es.upm.dit.isst.billgestor.dao;

import es.upm.dit.isst.billgestor.model.Empresa;

public interface EmpresaDAO {

	// public List<Empresa> listTodos();
	
	public void add (String name, String email, String domain, String password, String product, String language);
	
	public void remove (long id);
	
	public Empresa getEnterprise(String email);
	
	public Empresa getEnterpriseDomain(String domain); //CLIENTE
	
	public boolean isDomainRegistered(String domain); //CLIENTE
	
	public boolean isEmailRegistered(String email);
	
	public boolean areEnoughRequestLeft(String domain); 
	
	public void setLanguage(String email, String language);
	
	public void increaseRequests(int newRequests, String domain);
	
	public void decreaseOneRequest(String email);
	
	public void decreaseOneRequestDomain(String domain); //CLIENTE
	
	public void setWarningRequest(int warning_request, String email);
	
	public boolean correctLogin(String email, String password);
	// public List<String> getUsers();

}
