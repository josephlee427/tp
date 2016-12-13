package com.fdmgroup;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbAccessor {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TradingPlatform");
	private static EntityManager em =emf.createEntityManager();
	
	public static EntityManager getEntityManager(){
		return em;
	}
	
	public static void close(){
		em.close();
		emf.close();
	}
}
