package com.fdmgroup;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tp_request")
public class Req extends Storable {

	@Id
	@Column(name = "request_id")
	@GeneratedValue(generator = "request_id_seq")
	@SequenceGenerator(name = "request_id_seq", sequenceName = "tp_request_id_seq", initialValue = 864, allocationSize = 0)
	private int reqID;

	@Column(name = "buy_sell")
	private String bOrS;


	@Column(name = "shareholder_id")
	private int buyer;

	@Transient
	private int seller;

	@Column(name = "stock_id")
	private int stockID;

	@Column(name = "shares")
	private int amount;

	@Column(name = "request_date")
	private Date requestedOn;
//	private String requestedOn;

	@Column(name = "parent_request_id")
	private Integer parentId;

	@Column(name = "shares_filled")
	private Integer filled;

	@Column(name = "status")
	private String state;

	@Column(name = "minimum_shares")
	private Integer minTrade = 0;

	@Column(name = "time_in_force")
	private String interval = "GOOD UNTIL CANCELLED";

	public Req(){
		
	}
	
	public Req(int buyer, int stockID, int amount, String requestedOn, BuyOrSell bos) {
		super();
		this.buyer = buyer;
		this.stockID = stockID;
		this.amount = amount;
		this.requestedOn = new Date();
		this.bOrS = bos.toString();
		state = Status.ACTIVE.toString();
	}

	public void setReqID(int i) {
		reqID = i;
	}

	@Override
	public String toString() {
		return "Req [reqID=" + reqID + ", bOrS=" + bOrS + ", buyer=" + buyer + ", stockID=" + stockID + ", amount="
				+ amount + ", requestedOn=" + requestedOn + ", parentId=" + parentId + ", filled=" + filled + ", state="
				+ state + ", minTrade=" + minTrade + ", interval=" + interval + "]";
	}
	
	public String getbOrS() {
		return bOrS;
	}
	
	public void setbOrS(String bOrS) {
		this.bOrS = bOrS;
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reqID;
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
		Req other = (Req) obj;
		if (reqID != other.reqID)
			return false;
		return true;
	}
	
	public String getInterval() {
		return interval;
	}
	
	public void setInterval(String interval) {
		this.interval = interval;
	}

	public int getReqID() {
		return reqID;
	}

	public int getBuyer() {
		return buyer;
	}

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getRequestedOn() {
		return requestedOn;
	}

	/*public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}*/

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getFilled() {
		return filled;
	}

	public void setFilled(int filled) {
		this.filled = filled;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMinTrade() {
		return minTrade;
	}

	public void setMinTrade(int minTrade) {
		this.minTrade = minTrade;
	}

	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}

	public int fillShares(int shares) {

		if (filled == null)
			filled = 0;
		if (filled + shares <= amount){
			filled += shares;
			if(filled==amount)
				setStatus(Status.COMPLETE);
		}
		else
			System.out.println("Sorry, request beyond capacity");
		
		return filled;
	}

	private void setStatus(Status status) {
		state = status.toString(); 
	}

}
