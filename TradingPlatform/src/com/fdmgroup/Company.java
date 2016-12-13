package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;


@Entity
@Table(name = "tp_company")
public class Company extends Storable{
	
	@Id
	@Column(name = "company_id")
	@GeneratedValue(generator = "company_id_seq")
	@SequenceGenerator(name = "company_id_seq", sequenceName = "tp_company_seq", initialValue = 864, allocationSize = 0)
	private int company_ID;
	
	@Column(name = "company_name")
	private String name;
	
	@Column(name = "stock_id")
	private int stock_ID;
	
	@Column(name = "starting_price")
	private double startingPrice;
	
	public Company() {
		super();
	}

	public Company(String name, double startingPrice) {
		super();
		this.name = name;
		this.startingPrice = startingPrice;
	}

	public int getCompany_ID() {
		return company_ID;
	}

	public void setCompany_ID(int company_ID) {
		this.company_ID = company_ID;
		this.stock_ID = company_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock_ID() {
		return stock_ID;
	}

	public void setStock_ID(int stock_ID) {
		this.stock_ID = stock_ID;
	}

	@Override
	public String toString() {
		return "Company [company_ID=" + company_ID + ", name=" + name + ", stock_ID=" + stock_ID + ", startingPrice="
				+ startingPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		


}
