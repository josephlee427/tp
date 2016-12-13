package com.fdmgroup;

import java.util.Scanner;

public class CompanyController extends SessionController {
	private TradeCollectionDAO<Trade> trades;
	private RequestCollectionDAO requests;
	
	private User user;
	private static StringBuffer buf;
	
	@Override
	public String giveDisplay() {
		
		return buf.toString();
	}

	@Override
	public void updateView() {
		sv.update(buf.toString());
	}

	public static StringBuffer getBuf() {
		return buf;
	}

	public static void setBuf(StringBuffer buf) {
		CompanyController.buf = buf;
	}

	@Override
	public void controlApp() {
		
		String compName;
		String startPrice;
		
		updateView();
		
		Scanner scn = new Scanner(System.in);
		
		String line = scn.nextLine();
		while(!line.equals("l")){
			
			buf = new StringBuffer();
			
			switch (line){
				case "1":
					buf.append(getProfile());
					
					break;				

				case "2":
					buf.append("Adding new company...\n");
					buf.append("Please specify new company name:\n");
					updateView();
					compName = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("What do you want to set the new company's starting stock price to?\n");
					updateView();
					startPrice = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("New company created:\n");
					buf.append(newCompany(compName, Float.valueOf(startPrice)));
					
					break;
					
				case "3":
					buf.append("Removable companies:\n");
					buf.append("-----------------------------------------------------------\n");
					buf.append(CompanyJpaDao.getAllCompanies()+"\n");
					buf.append("Please give name of company you want to remove:\n");
					updateView();
					
					buf = new StringBuffer();
					compName = scn.nextLine();
					deleteCompany(compName);
					
					break;
					
				case "4":
					buf.append("Modifiable companies:\n");
					buf.append("-----------------------------------------------------------\n");
					buf.append(CompanyJpaDao.getAllCompanies()+"\n");
					buf.append("Please give name of company you want to modify:\n");
					updateView();
					compName = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("Please enter new name for this company:\n");
					updateView();
					
					buf = new StringBuffer();
					buf.append("Company Modified:\n");
					buf.append(modCompany(compName, scn.nextLine()).toString()+"\n");
					
					
					break;
					
				case "e":
					exit();
			}
			
			buf.append("\n");
			buf.append("Press enter to continue...\n");
			updateView();
			
			scn.nextLine();
			
			buf = new StringBuffer();
			
			buf.append("	Logged as: " + user.getUsername() + "!\n");
			buf.append("	Please enter one of the following options:\n");
			buf.append("------------------------------------------------------------------\n");
			buf.append("1) View Profile\n");
			buf.append("2) Add Company\n");
			buf.append("3) Remove Company\n");
			buf.append("4) Modify Company\n");
			buf.append("l) logout\n");
			buf.append("e) exit\n");
			
			updateView();
			
			line = scn.nextLine();
		};
		
		System.out.println("    logging out...");
		
		
	}

	private Company modCompany(String compName, String newName) {

		Company comp = new Company(compName, 0);
		
		return CompanyJpaDao.modCompany(comp, new Company(newName, 0));
	}

	private void deleteCompany(String compName) {
		Company delComp = new Company(compName, 0);
		
		CompanyJpaDao.deleteCompany(delComp);
	}

	public CompanyController(User user) {
		super();
		this.user = user;
		
		buf = new StringBuffer();
		
		buf.append("	Logged as: " + user.getUsername() + "!\n");
		buf.append("	Please enter one of the following options:\n");
		buf.append("------------------------------------------------------------------\n");
		buf.append("1) View Profile\n");
		buf.append("2) Add Company\n");
		buf.append("3) Remove Company\n");
		buf.append("4) Modify Company\n");
		buf.append("l) logout\n");
		buf.append("e) exit\n");
		
		trades= new TradeCollectionDAO<>();
		requests = new RequestCollectionDAO();
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProfile() {
		
		StringBuffer sb = new StringBuffer();
		sb.append(user.toString()+"\n");
		sb.append(user.getCompany()+"\n");
		
		return sb.toString();
	}

	public void getTradeHistory() {
		
		buf = new StringBuffer();
		for (Trade trade : trades.getTrades()) {
			buf.append(trade);
		}
		
		updateView();
	}

	public void getRequestHistory() {
		buf = new StringBuffer();
		for (Req request: requests.getAllRequests()) {
			buf.append(request);
		}
		
		updateView();
	}
		
	public Company newCompany(String name, Float startPrice){
		
		Company newCompany = new Company(name, startPrice);
		
		return CompanyJpaDao.newCompany(newCompany);
	}
}
