package es.upm.dit.isst.factura.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.billgestor.dao.EMFService;
import es.upm.dit.isst.factura.model.Factura;

public class FacturaDAOImpl implements FacturaDAO {

	private static FacturaDAOImpl instance;

	private FacturaDAOImpl() {
	}

	public static FacturaDAOImpl getInstance(){
		if (instance == null)
			instance = new FacturaDAOImpl();
		return instance;
	}

	@Override
	public void add(String name, String pais, String total, String totalIva) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Factura factura = new Factura(name, pais, total, totalIva);
			em.persist(factura);
			em.close();
		}
	}

	@Override
	public boolean tieneFacturaPais(String name, String pais) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select f from Factura f where f.name = :name and f.pais = :pais");
		q.setParameter("name", name);
		q.setParameter("pais", pais);
		if(!q.getResultList().isEmpty()) return true;
		return false;
	}
	
	@Override
	public Factura getFacturaPais(String name, String pais){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select f from Factura f where f.name = :name and f.pais = :pais");
		q.setParameter("name", name);
		q.setParameter("pais", pais);
		Factura factura = (Factura) q.getResultList().get(0);
		return factura;
	}

	@Override
	public void setTotalFacturaPais(String name, String pais, String total) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select f from Factura f where f.name = :name and f.pais = :pais");
		q.setParameter("name", name);
		q.setParameter("pais", pais);
		Factura factura = (Factura) q.getResultList().get(0);
		em.getTransaction().begin();
		factura.setTotal(total);
		em.getTransaction().commit();
	}

	@Override
	public void setTotalIvaFacturaPais(String name, String pais, String totalIva) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select f from Factura f where f.name = :name and f.pais = :pais");
		q.setParameter("name", name);
		q.setParameter("pais", pais);
		Factura factura = (Factura) q.getResultList().get(0);
		em.getTransaction().begin();
		factura.setTotalIva(totalIva);
		em.getTransaction().commit();
	}
}
