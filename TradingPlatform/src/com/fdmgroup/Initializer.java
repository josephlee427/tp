package com.fdmgroup;

public class Initializer {
	
	public Initializer(){
		new DbAccessor();
		new UserJpaDao();
		new CompanyJpaDao();
		new RequestJpaDao();
		new TradeJpaDao();
	}
}
