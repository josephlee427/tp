package com.fdmgroup;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CompanyJpaDao {

	private static EntityManager em;
	
	public CompanyJpaDao() {
		super();
		em = DbAccessor.getEntityManager();
	}

	public static List<Company> getAllCompanies() {
		
		TypedQuery<Company> allCompanies= em.createQuery("Select company from Company company", Company.class);
		
		List<Company> thing = allCompanies.getResultList();
		
//		StringBuilder returnString = new StringBuilder();
		
		for (Company company : thing) {
			assignStockId(company);
		}
		
		return thing;
	}

	public static Company newCompany(Company newCompany) {
		
		em.getTransaction().begin();
		
		em.persist(newCompany);
		
		em.getTransaction().commit();
		
		Company returnCompany = em.find(Company.class, newCompany.getCompany_ID());
		
		return assignStockId(returnCompany);
	}

	public static Company getCompany(Company company) {
		
		TypedQuery<Company> allCompanies= em.createQuery("Select user from Company user where user.name = :user", Company.class);
		allCompanies.setParameter("user", company.getName());
		
//		List<Company> thing = allCompanies.getResultList();
		
/*		for (User returnUser : thing) {
			return returnUser;
		}
		
		return null;*/
		
//		List<Company> test = allCompanies.getResultList();
		
		return assignStockId(allCompanies.getSingleResult());
	}

	public boolean isIn(Company comp) {
		
		TypedQuery<Company> allCompanies= em.createQuery("Select user from Company user where user.name= :user", Company.class);
		allCompanies.setParameter("user", comp.getName());
		
		Company eval = allCompanies.getSingleResult();
		
		return eval.equals(comp);
		
	}

	public static void reset() {

	}

	private static Company assignStockId(Company comp){
		
		
		em.getTransaction().begin();
		
		Company outComp = em.find(Company.class, comp.getCompany_ID());
		
		outComp.setStock_ID(outComp.getCompany_ID());
		
		em.persist(outComp);
		
		em.getTransaction().commit();
		
		return outComp;
	}

	public static void deleteCompany(Company delComp) {
		
		TypedQuery<Company> allCompanies= em.createQuery("Select user from Company user where user.name= :user", Company.class);
		allCompanies.setParameter("user", delComp.getName());
		
		Company eval = allCompanies.getSingleResult();
		
		em.getTransaction().begin();
		
		eval = em.find(Company.class, eval.getCompany_ID());
		em.remove(eval);
		
		em.getTransaction().commit();
		
	}

	public static Company modCompany(Company comp, Company company) {
	
		em.getTransaction().begin();
		
		Company modComp = em.find(Company.class, justGetCompany(comp).getCompany_ID());
		
		modComp.setName(company.getName());
		
		em.getTransaction().commit();
		
		return modComp;
	}

	private static Company justGetCompany(Company comp) {

		TypedQuery<Company> allCompanies= em.createQuery("Select user from Company user where user.name = :user", Company.class);
		allCompanies.setParameter("user", comp.getName());
		
		return allCompanies.getSingleResult();
		
	}
	
}
