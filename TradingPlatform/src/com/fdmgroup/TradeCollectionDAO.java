package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

public class TradeCollectionDAO<T> extends DAO<T>{
	
	static int numTrades = 0;
	static private List<Trade> trades = new ArrayList<>();

	public static Trade newTrade(Trade newTrade) {
		
		numTrades++;
		newTrade.setTradeID(numTrades);
		
		if(trades.contains(newTrade)){
			return null;
		}
		
		trades.add(newTrade);
		
		return trades.get(trades.indexOf(newTrade));
	}

	public static Trade getTrade(Trade user) {
		
		if(trades.contains(user)){
			return trades.get(trades.indexOf(user));
		}
		
		return null;
	}
	
	public static boolean isIn(Trade trade){
		return trades.contains(trade);
	}
	
	public void reset(){
		numTrades = 0;
		trades = new ArrayList<>();
	}

	public List<Trade> getTrades() {
		return trades;
	}
	
	public static List<Trade> matchTrades(User user){
		
		List<Trade> returnList = new ArrayList<>();
		
		for (Trade trade: trades) {
			if(trade.getSeller()==user.getUserID()||trade.getBuyer()==user.getUserID())
				returnList.add(trade);
		}
		
		return returnList;
	}
}
