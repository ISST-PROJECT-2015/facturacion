package es.upm.dit.isst.localizacion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.localizacion.modelo.Country;

public class LocalizacionDAOImpl implements LocalizacionDAO {

	private static LocalizacionDAOImpl instance;

	private LocalizacionDAOImpl() {
	}

	public static LocalizacionDAOImpl getInstance(){
		if (instance == null)
			instance = new LocalizacionDAOImpl();
		return instance;
	}

	@Override
	public List<Country> listPaises() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Country m");
		List<Country> paises = q.getResultList();
		return paises;
	}

	@Override
	public void add(String userId, String name, String iva, String code) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Country pais = new Country(userId, name, iva, code);
			em.persist(pais);
			em.close();
		}
		
	}

	@Override
	public List<Country> getPaises(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Country t where t.autor = :userId");
		q.setParameter("userId", userId);
		List<Country> paises = q.getResultList();
		return paises;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Country pais = em.find(Country.class, id);
			em.remove(pais);
		} finally {
			em.close();
		}
	}

	@Override
	public List<String> getUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select distinct t.autor from Country t");
		List<String> users = q.getResultList();
		return users;
	}





}