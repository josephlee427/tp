package com.fdmgroup;

import java.util.Date;
import java.util.Scanner;

public class HybridController extends SessionController {

	private TradeCollectionDAO<Trade> trades;
	private RequestCollectionDAO requests;
	private CompanyCollectionDAO companies;
	
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
		HybridController.buf = buf;
	}

	@Override
	public void controlApp() {
		updateView();
		
		String tempStr;
		String tempStr2;
		
		Date date = new Date();
		
		Scanner scn = new Scanner(System.in);
		
		String line = scn.nextLine();
		while(!line.equals("l")){
			
			buf = new StringBuffer();
			
			switch (line){
			case "1":
				buf.append(getProfile());
				break;
			case "2":
				buf.append("Your trade history:\n");
				buf.append("-----------------------------------\n");
				for (Trade trade : TradeJpaDao.matchTrades(user)) {
					buf.append(trade.toString()+"\n");
				}
				break;
			case "3":
				buf.append("Your request history:\n");
				buf.append("-----------------------------------\n");
				for (Req request : RequestJpaDao.matchRequests(user)) {
					buf.append(request.toString()+"\n");
				}
				break;
				
			case "4":
				buf.append("Adding new company...\n");
				buf.append("Please specify new company name:\n");
				updateView();
				tempStr = scn.nextLine();
				
				buf = new StringBuffer();
				buf.append("What do you want to set the new company's starting stock price to?\n");
				updateView();
				tempStr2 = scn.nextLine();
				
				buf = new StringBuffer();
				buf.append("New company created:\n");
				buf.append(newCompany(tempStr, Float.valueOf(tempStr2))+"\n");
				
				break;
				
			case "5":
				buf.append("Removable companies:\n");
				buf.append("-----------------------------------------------------------\n");
				buf.append(CompanyJpaDao.getAllCompanies()+"\n");
				buf.append("Please give name of company you want to remove:\n");
				updateView();
				
				buf = new StringBuffer();
				tempStr = scn.nextLine();
				deleteCompany(tempStr);
				
				break;
				
			case "6":
				buf.append("Modifiable companies:\n");
				buf.append("-----------------------------------------------------------\n");
				buf.append(CompanyJpaDao.getAllCompanies()+"\n");
				buf.append("Please give name of company you want to modify:\n");
				updateView();
				tempStr = scn.nextLine();
				
				buf = new StringBuffer();
				buf.append("Please enter new name for this company:\n");
				updateView();
				
				buf = new StringBuffer();
				buf.append("Company Modified:\n");
				buf.append(modCompany(tempStr, scn.nextLine()).toString()+"\n");
				
				
				break;
				
			case "7":
				buf.append("Your portfolio: (stock ID=amount possessed)\n");
				buf.append(user.getPortfolio()+ "\n");
				break;
				
/*			case "8":
				buf.append("Your portfolio: (stock ID=amount possessed)\n");
				buf.append(user.getPortfolio()+ "\n");
				buf.append("---------------------------------------------------\n");
				buf.append("\n");
				buf.append("Please enter stock ID of the stock you want to trade:\n");
				updateView();
				
				tempStr = Integer.toString(scn.nextInt());
				
				buf = new StringBuffer();
				buf.append("Please enter how many of this stock you want to trade:");
				updateView();
				
				tempStr2 = Integer.toString(scn.nextInt());
				scn.nextLine();
				
				buf = new StringBuffer();
				buf.append("Trade placed:\n");
//				buf.append(newTrade(user.getUserID(), Integer.parseInt(tempStr), Integer.parseInt(tempStr2), date.toString()).toString()+"\n");
				
				break;*/
				
			case "9":
				buf.append("Please select one of the following stocks to buy:\n");
				buf.append("--------------------------------------------------\n");
				buf.append(CompanyJpaDao.getAllCompanies());
				buf.append("\n");
				buf.append("please enter stockID for the stock you want to trade: \n");
				updateView();
				
				tempStr = Integer.toString(scn.nextInt());
				
				buf = new StringBuffer();
				buf.append("How many of this stock would you like to buy?\n");
				updateView();
				
				tempStr2 = Integer.toString(scn.nextInt());
				scn.nextLine();
				
				
				
				buf = new StringBuffer();
				buf.append("Your request is placed:\n");
				buf.append("\n");
				buf.append(newRequest(user.getUserID(), Integer.parseInt(tempStr), Integer.parseInt(tempStr2), date.toString(), BuyOrSell.BUY).toString()+"\n");
				
				
				break;

				case "e":
					exit();
			}
			
			buf.append("Press enter to continue...");
			updateView();
			
			scn.nextLine();
			
			buf = new StringBuffer();
			
			buf.append("	Logged as: " + user.getUsername() + "!\n");
			buf.append("	Please enter one of the following options:\n");
			buf.append("------------------------------------------------------------------\n");
			buf.append("1) View Profile\n");
			buf.append("2) View Trade History\n");
			buf.append("3) View Request History\n");
			buf.append("4) Add Company\n");
			buf.append("5) Remove Company\n");
			buf.append("6) Modify Company\n");
			buf.append("7) View Portfolio\n");
//			buf.append("8) New Trade\n");
			buf.append("9) New Request\n");
			buf.append("l) logout\n");
			buf.append("e) exit\n");
			
			updateView();
			
			line = scn.nextLine();
		};
		
		System.out.println("    logging out...");
		
		
	}

	public HybridController(User user) {
		super();
		this.user = user;
		
		buf = new StringBuffer();
		
		buf.append("	Logged as: " + user.getUsername() + "!\n");
		buf.append("	Please enter one of the following options:\n");
		buf.append("------------------------------------------------------------------\n");
		buf.append("1) View Profile\n");
		buf.append("2) View Trade History\n");
		buf.append("3) View Request History\n");
		buf.append("4) Add Company\n");
		buf.append("5) Remove Company\n");
		buf.append("6) Modify Company\n");
		buf.append("7) View Portfolio\n");
//		buf.append("8) New Trade\n");
		buf.append("9) New Request\n");
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
		
/*		buf = new StringBuffer();
		for (Trade trade : trades.getTrades()) {
			buf.append(trade);
		}
		
		updateView();*/
	}

	public void getRequestHistory() {
/*		buf = new StringBuffer();
		for (Req request: requests.getAllRequests()) {
			buf.append(request);
		}
		
		updateView();*/
	}
	
	
	//TODO make trades a bit more eloquent
	public Trade newTrade(int buyer, int seller, int stockID, int amount, String tradedOn){
		
		Trade newTrade = new Trade(buyer, seller, stockID, amount, tradedOn);
		user.tradeStrocks(stockID, amount);
		
		return trades.newTrade(newTrade);
	}
	
	public Req newRequest(int buyer, int stockID, int amount,  String requestedOn, BuyOrSell bos){
		
		Req newRequest = new Req(buyer, stockID, amount, requestedOn, bos);
		user.addStocks(stockID, amount);
		
		return RequestJpaDao.newRequest(newRequest);
	}
	
	public Company newCompany(String name, Float startPrice){
		
		Company newCompany = new Company(name, startPrice);
		
		return CompanyJpaDao.newCompany(newCompany);
	}
	
	private Company modCompany(String compName, String newName) {

		Company comp = new Company(compName, 0);
		
		return CompanyJpaDao.modCompany(comp, new Company(newName, 0));
	}

	private void deleteCompany(String compName) {
		Company delComp = new Company(compName, 0);
		
		CompanyJpaDao.deleteCompany(delComp);
	}
	
}
	

