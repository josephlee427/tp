package com.fdmgroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tp_trade")
public class Trade extends Storable{

	@Id
	@Column(name = "trade_id")
	@GeneratedValue(generator = "trade_id_seq")
	@SequenceGenerator(name = "trade_id_seq", sequenceName = "tp_trade_seq", initialValue = 0, allocationSize = 0)
	private int tradeID;
	
	@Column(name = "buyer_id")
	private int buyer;
	
	@Column(name = "seller_id")
	private int seller;
	
	@Column(name = "shares")
	private int amount;
	
	@Column(name = "share_price")
	private int price;
	
	@Column(name = "price_total")
	private float total;
	
/*	@Column(name = "minimum_shares")
	private int minShares;*/
	
	@Column(name = "buy_request_id")
	private int buy_req_id;
	
	@Column(name = "sell_request_id")
	private int sell_req_id;
	
//	@Column(name = "request_date")
//	private String requestedOn;
	
/*	@Column(name = "status")
	private String status;*/
	
	@Column(name = "stock_id")
	private int stockID;
	
	@Transient
	private String bors;
	
	@Column(name = "transaction_time")
	private Date tradedOn;
	
//	@Column(name = "time_in_force")
//	private String timeInForce;
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBors() {
		return bors;
	}

	public void setBors(String bors) {
		this.bors = bors;
	}

	public Date getTradedOn() {
		return tradedOn;
	}

/*	public void setTradedOn(String tradedOn) {
		this.tradedOn = tradedOn;
	}*/

/*	public String getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
	}
*/
/*	public int getMinShares() {
		return minShares;
	}

	public void setMinShares(int minShares) {
		this.minShares = minShares;
	}*/

	public int getBuy_req_id() {
		return buy_req_id;
	}

	public void setBuy_req_id(int buy_req_id) {
		this.buy_req_id = buy_req_id;
	}

	public int getSell_req_id() {
		return sell_req_id;
	}

	public void setSell_req_id(int sell_req_id) {
		this.sell_req_id = sell_req_id;
	}

/*	public String getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}*/

/*	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
*/
	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	
	public int getTradeID() {
		return tradeID;
	}

	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
		
	}

	public Trade(){
		
	}
	
	public Trade(int seller, int sreq_id, int breq_id, int amount, String tradedOn) {
		this.seller = seller;
		this.sell_req_id = sreq_id;
		this.buy_req_id = breq_id;
		this.amount = amount;
		this.tradedOn = new Date();
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tradeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeID != other.tradeID)
			return false;
		return true;
	}
	
	public void reset(){
	}

	@Override
	public String toString() {
		return "Trade [buyer=" + buyer + ", seller=" + seller + ", stockID=" + stockID + ", amount=" + amount
				+ ", tradedOn=" + tradedOn + ", tradeID=" + tradeID + "]";
	}

	public int getSeller() {
		return seller;
	}
	
	public int getBuyer(){
		return buyer;
	}


	
	
	
	
}
