package es.upm.dit.isst.billgestor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.billgestor.model.EmailUtility;
import es.upm.dit.isst.billgestor.model.Empresa;
import es.upm.dit.isst.billgestor.model.Empresa.Plan;

public class EmpresaDAOImpl implements EmpresaDAO {

	private static EmpresaDAOImpl instance;
	private static String USER_NAME = "gestiondefacturas.isst@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "gestiondefacturas"; // GMail password
    private static String RECIPIENT1 = "";
	    
	public EmpresaDAOImpl() {

	}

	public static EmpresaDAOImpl getInstance() {
		if (instance == null)
			instance = new EmpresaDAOImpl();
		return instance;
	}

	@Override
	public void add(String name, String email, String domain, String password,
			String product, String language) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Empresa empresa = new Empresa(name, email, domain, password,
					product, language);
			em.persist(empresa);
			em.close();
		}

	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Empresa empresa = em.find(Empresa.class, id);
			em.remove(empresa);
		} finally {
			em.close();
		}
	}

	//implementado por CLIENTE
	@Override
	public boolean isDomainRegistered(String domain) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.domain = :dom");
		q.setParameter("dom", domain);
		if(!q.getResultList().isEmpty()) return true;
		return false;
	}

	@Override
	public boolean areEnoughRequestLeft(String domain) {
		return false;
	}
	
	@Override
	public boolean correctLogin(String email, String password){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em and e.password = :pass");
		q.setParameter("em", email);
		q.setParameter("pass", password);
		if(!q.getResultList().isEmpty()) return true;
		return false;
	}
	
	@Override
	public Empresa getEnterprise(String email){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em");
		q.setParameter("em", email);
		Empresa e = (Empresa) q.getResultList().get(0);
		return e;
	}
	
	@Override
	public void increaseRequests(int newRequests, String email){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em");
		q.setParameter("em", email);
		Empresa e = (Empresa) q.getResultList().get(0);	
		em.getTransaction().begin();
		e.setRemainingRequest(e.getRemainingRequest() + newRequests);
		switch (newRequests){
			case 100:
				e.setPlan(Plan.STARTUP);
				break;
			case 1000:
				e.setPlan(Plan.PREMIUM);
				break;
			case 10000:
				e.setPlan(Plan.GOLD);
				break;
			default:
				e.setPlan(Plan.FREE);
				break;
		}
		em.getTransaction().commit();
		
	}
	
	@Override
	public void setWarningRequest(int warning_request, String email){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em");
		q.setParameter("em", email);
		Empresa e = (Empresa) q.getResultList().get(0);	
		em.getTransaction().begin();
		e.setWarningRequest(warning_request);
		em.getTransaction().commit();
	}
	
	@Override
	public void setLanguage(String email, String language){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em");
		q.setParameter("em", email);
		Empresa e = (Empresa) q.getResultList().get(0);	
		em.getTransaction().begin();
		e.setLanguage(language);
		em.getTransaction().commit();
	}
	
	
	@Override
	public void decreaseOneRequest(String email){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :em");
		q.setParameter("em", email);
		Empresa e = (Empresa) q.getResultList().get(0);	
		em.getTransaction().begin();
		if(e.getRemainingRequest()>0){
			e.setRemainingRequest(e.getRemainingRequest()-1);
		}if(e.getRemainingRequest()<=0){
			e.setPlan(Plan.NO_PLAN);
			e.setRemainingRequest(1);
		}
		if(e.getRemainingRequest() == e.getWarningRequest()){
			RECIPIENT1 = e.getEmail();
			String from = USER_NAME;
	        String pass = PASSWORD;
	        String to = RECIPIENT1 ; // list of recipient email addresses
	        String subject = "GEEFT: Request Limit Reached. " + e.getRemainingRequest() + " Requests Left";
	        String body = "We inform you are about to run out of requests available. Please log into your user account and choose a new package plan. "
	        		+ "Thank you.";

	        EmailUtility.sendFromGMail(from, to, subject, body);
			System.out.println("Correo enviado a:"+ RECIPIENT1);
			
		}
		if(e.getPlan().equals(Plan.NO_PLAN)){
			RECIPIENT1 = e.getEmail();
			String from = USER_NAME;
	        String pass = PASSWORD;
	        String to = RECIPIENT1; // list of recipient email addresses
	        String subject = "GEEFT: Request Limit Reached. 0 Requests Left";
	        String body = "We inform you have run out of requests available. Please log into your user account and choose a new package plan. "
	        		+ "Thank you.";

	        EmailUtility.sendFromGMail(from, to, subject, body);
			System.out.println("Correo enviado a:"+ RECIPIENT1 );
		}
		em.getTransaction().commit();
		
	}

	//implementado por cliente
	@Override
	public void decreaseOneRequestDomain(String domain) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.domain = :dom");
		q.setParameter("dom", domain);
		Empresa e = (Empresa) q.getResultList().get(0);	
		em.getTransaction().begin();
		if(e.getRemainingRequest()>0){
			e.setRemainingRequest(e.getRemainingRequest()-1);
		}if(e.getRemainingRequest()<=0){
			e.setPlan(Plan.NO_PLAN);
			e.setRemainingRequest(1);
		}
		if(e.getRemainingRequest() == e.getWarningRequest()){
			RECIPIENT1 = e.getEmail();
			String from = USER_NAME;
	        String pass = PASSWORD;
	        String to = RECIPIENT1 ; // list of recipient email addresses
	        String subject = "GEEFT: Request Limit Reached. " + e.getRemainingRequest() + " Requests Left";
	        String body = "We inform you are about to run out of requests available. Please log into your user account and choose a new package plan. "
	        		+ "Thank you.";

	        EmailUtility.sendFromGMail(from, to, subject, body);
			System.out.println("Correo enviado a:"+ RECIPIENT1);
			
		}
		if(e.getRemainingRequest() == 0 && !e.getPlan().equals(Plan.NO_PLAN)){
			RECIPIENT1 = e.getEmail();
			String from = USER_NAME;
	        String pass = PASSWORD;
	        String to = RECIPIENT1 ; // list of recipient email addresses
	        String subject = "GEEFT: Request Limit Reached. 0 Requests Left";
	        String body = "We inform you have run out of requests available. Please log into your user account and choose a new package plan. "
	        		+ "Thank you.";

	        EmailUtility.sendFromGMail(from, to, subject, body);
			System.out.println("Correo enviado a:"+ RECIPIENT1 );
		}
		em.getTransaction().commit();
		
	}

	//implementado por cliente
	@Override
	public Empresa getEnterpriseDomain(String domain) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.domain = :dom");
		q.setParameter("dom", domain);
		Empresa e = (Empresa) q.getResultList().get(0);
		return e;
	}

	@Override
	public boolean isEmailRegistered(String email) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Empresa e where e.email = :ema");
		q.setParameter("ema", email);
		if(!q.getResultList().isEmpty()) return true;
		return false;
	}


}
