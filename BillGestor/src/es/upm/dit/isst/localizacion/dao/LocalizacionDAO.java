package es.upm.dit.isst.localizacion.dao;

import java.util.List;

import es.upm.dit.isst.localizacion.modelo.Country;

public interface LocalizacionDAO {
	
	public List<Country> listPaises();
	public void add (String userId, String name, String iva, String code);
	public List<Country> getPaises(String userId);
	public void remove (long id);
	public List<String> getUsers();
}

