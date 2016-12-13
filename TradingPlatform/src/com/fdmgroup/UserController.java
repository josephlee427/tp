package com.fdmgroup;

import java.util.Date;
import java.util.Scanner;

public class UserController extends SessionController{

/*	private TradeJpaDao trades;
	private RequestJpaDao requests;
	private CompanyJpaDao companies;*/
	
	private int stockpick;
	private int howmany;
	
	private User user;
	private static StringBuffer buf;
	private static String bors;
	
	@Override
	public String giveDisplay(){
		
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
		UserController.buf = buf;
	}

	@Override
	public void controlApp() {
		updateView();
		
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
					buf.append(user.getPortfolio());
					break;
					
				case "5":
/*					buf.append("Your portfolio: (stock ID=amount possessed)\n");
					buf.append("---------------------------------------------------\n");
					buf.append(user.getPortfolio()+ "\n");
					buf.append("---------------------------------------------------\n");
					buf.append("\n");
					buf.append("\n");
					buf.append("active requests:\n");
					buf.append("---------------------------------------------------\n");
					buf.append(RequestJpaDao.getActiveRequests());
					buf.append("---------------------------------------------------\n");
					buf.append("\n");
					buf.append("\n");
					buf.append("Please request ID for a request you'd like to fill:\n");
					updateView();
					
					stockpick = scn.nextInt();
					
					buf = new StringBuffer();
					buf.append("Please enter how many of this stock you want to trade:");
					updateView();
					
					howmany = scn.nextInt();
					scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("Trade placed:\n");
					buf.append(newTrade(user.getUserID(), stockpick, howmany, date.toString()).toString()+"\n");*/
					
					break;
					
				case "6":
					buf.append("Please select one of the following stocks to request:\n");
					buf.append("--------------------------------------------------\n");
					buf.append(CompanyJpaDao.getAllCompanies());
					buf.append("\n");
					buf.append("please enter stockID for the company ID you want to request: \n");
					updateView();
					
					stockpick = scn.nextInt();
					
					buf = new StringBuffer("Buy or Sell?\n");
					updateView();
					
					scn.nextLine();
					bors = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("How many of this stock would you like to buy?\n");
					updateView();
					
					howmany = scn.nextInt();
					scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("Your request is placed:\n");
					buf.append("\n");
					buf.append(newRequest(user.getUserID(), stockpick, howmany, date.toString(), BuyOrSell.valueOf(bors)).toString()+"\n");
					
					break;
				
				
				case "e":
					exit();
			}
			
			
			
			buf.append("\n");
			buf.append("Press enter to continue...\n");
			updateView();
			
			buf = new StringBuffer();
			
			scn.nextLine();
			
			buf = new StringBuffer();
			
			buf.append("	Logged as: " + user.getUsername() + "!\n");
			buf.append("	Please enter one of the following options:\n");
			buf.append("------------------------------------------------------------------\n");
			buf.append("1) View Profile\n");
			buf.append("2) View Trade History\n");
			buf.append("3) View Request History\n");
			buf.append("4) View Portfolio\n");
//			buf.append("5) New Trade\n");
			buf.append("6) New Request\n");
			buf.append("l) logout\n");
			buf.append("e) exit\n");
			
			updateView();
			
			line = scn.nextLine();
		};
		
		System.out.println("    logging out...");
		
		
	}

	public UserController(User user) {
		super();
		new TradeJpaDao();
		this.user = user;
		
		buf = new StringBuffer();
		
		buf.append("	Logged as: " + user.getUsername() + "!\n");
		buf.append("	Please enter one of the following options:\n");
		buf.append("------------------------------------------------------------------\n");
		buf.append("1) View Profile\n");
		buf.append("2) View Trade History\n");
		buf.append("3) View Request History\n");
		buf.append("4) View Portfolio\n");
//		buf.append("5) New Trade\n");
		buf.append("6) New Request\n");
		buf.append("l) logout\n");
		buf.append("e) exit\n");
		
//		trades= new TradeJpaDao();
//		requests = new RequestJpaDao();
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProfile() {
		
		return user.toString();
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
	
	public Trade newTrade(int buyer, int seller, int stockID, int amount, String tradedOn){
		
		Trade newTrade = new Trade(buyer, seller, stockID, amount, tradedOn);
		user.tradeStrocks(stockID, amount);
		
		return TradeJpaDao.newTrade(newTrade);
	}
	
	public Req newRequest(int buyer, int stockID, int amount,  String requestedOn, BuyOrSell bos){
		
		Req newRequest = new Req(buyer, stockID, amount, requestedOn, bos);
		
		TradeJpaDao.resolveTrade();
		
		//TODO: get stocks figured out
		//user.addStocks(stockID, amount);
		
		return RequestJpaDao.newRequest(newRequest);
	}
	
}


























