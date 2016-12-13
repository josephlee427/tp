package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

public class CompanyCollectionDAO extends DAO<Company>{
	
	static int numCompanies = 0;
	static List<Company> companies = new ArrayList<>();

	public static Company newCompany(Company newCompany) {
		
		newCompany.setCompany_ID(numCompanies);
		numCompanies++;
		
		if(companies.contains(newCompany)){
			return null;
		}
		
		companies.add(newCompany);
		return companies.get(companies.indexOf(newCompany));
	}

	public CompanyCollectionDAO() {
		super();
	}

	public static Company getCompany(Company company) {
		
		if(companies.contains(company)){
			return companies.get(companies.indexOf(company));
		}
		
		return null;
	}
	
	public boolean isIn(User user){
		return companies.contains(user);
	}
	
	public void reset(){
		numCompanies = 0;
		companies = new ArrayList<>();
	}

	public static String getAllCompanies(){
		StringBuffer buf = new StringBuffer();
		
		for (Company company : companies) {
			buf.append(company.toString() + "\n");
		}
		
		return buf.toString();
	}
	
	public static void deleteCompany(Company delCompany) {
		if(companies.contains(delCompany)){
			companies.remove(delCompany);
			System.out.println("deleted...");
		}
		else
			System.out.println("that company does not exist");
	}
}
