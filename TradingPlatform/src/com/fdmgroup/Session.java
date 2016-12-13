package com.fdmgroup;

import java.util.Date;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

import oracle.sql.DATE;

public class Session {
	public static void main(String[] args) {

		new Initializer();
		AuthenticationController ses = new AuthenticationController();

//		ses.register("JCD", "bionicman", "Admin");
//		ses.register("PDenton", "prometheus");
		
//		ses.register("JManderley", "mayors3girls", "Admin");
		
//		ses.register("pepsiman", "pepsi", "Admin");
		
//		Company comp1 = new Company("MJ12", 10.00);
//		Company comp2 = new Company("UNATCO",20.00);
		
//		CompanyJpaDao.newCompany(comp1);
//		CompanyJpaDao.newCompany(comp2);

//		Req request1 = new Req(23, 824, 400, new Date().toString(), BuyOrSell.Sell);
		
//		Req newRequest = new Req(23, 824, 12, new Date().toString(), BuyOrSell.BUY);
//		Req newRequest2 = new Req(24, 825, 12, new Date().toString(), BuyOrSell.SELL);
//		Req newRequest3 = new Req(23, 824, 12, new Date().toString(), BuyOrSell.SELL);
//		Req newRequest4 = new Req(24, 825, 12, new Date().toString(), BuyOrSell.BUY);
//		Req newRequest5 = new Req(23, 824, 12, new Date().toString(), BuyOrSell.SELL);
//		
//		RequestJpaDao.newRequest(newRequest);
//		RequestJpaDao.newRequest(newRequest2);
//		RequestJpaDao.newRequest(newRequest3);
//		RequestJpaDao.newRequest(newRequest4);
//		RequestJpaDao.newRequest(newRequest5);
		
//		RequestJpaDao.newRequest(new Req((UserJpaDao.getUser(new User("1", "1")).getUserID()), 824, 12, new Date().toString(), BuyOrSell.BUY));
		
//		ses.controlApp();
		DbAccessor.close();

		
		
		/*
		 * CompanyCollectionDAO.newCompany(new Company("Majestic 12", 50.00));
		 * CompanyCollectionDAO.newCompany(new Company("UNATCO", 10.00));
		 * CompanyCollectionDAO.newCompany(new Company("UNATCO", 10.00));
		 * CompanyCollectionDAO.newCompany(new Company("Luminious Path Triads",
		 * 5.00));
		 */

		// new UserJDBCDAO();

		// ses.register("JCDenton", "guy", "Hybrid", new Company("UNATCO", 0));
		// ses.register("dude", "guy", new Company("UNATCO", 0));
		// ses.register("yoloswag4", "guy", "Admin");

		// ses.controlApp();

		// @oneToMany()
		// @manyToOne(fetchType.lazy)

	}
}
