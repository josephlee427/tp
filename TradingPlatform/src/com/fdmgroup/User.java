package com.fdmgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tp_users")
public class User extends Storable {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "tp_users_seq", initialValue = 23, allocationSize = 0)
	private int userID;
	
	@Column(name = "pw", nullable = false)
	private String password;
	
	@Column(name = "user_name", unique = true, nullable = false)
	private String username;
	
	@Column(name = "first_name", nullable = false)
	private String firstName = "placeholder";
	
	@Column(name = "last_name", nullable = false)
	private String lastName = "placeholder";
	
	@Transient
	private Company company = null;
	
	@Transient
	private Role role = Role.SHAREHOLDER;
	@Transient
	private Map<Integer, Integer> portfolio = new HashMap<>();
	
	public User(){
		super();
	}
	
	public User(String username2, String password2) {
		super();
		this.password = password2;
		this.username = username2;
		role = role.SHAREHOLDER;
	}
	public User(int userID,String username, String password, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public User(String username, String password, String firstName, String lastName, Role role) {
		super();
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public User(String username2, String password2, Role role) {
		super();
		this.password = password2;
		this.username = username2;
		
		this.role = role;
	}
	
	public User(String username, String password, Company company){
		this.password = password;
		this.username = username;
		this.role = Role.COMPANY;
		
		this.company = CompanyJpaDao.getCompany(company);
	}
	
	public User(String username, String password, String string, Company company) {
		this.password = password;
		this.username = username;
		this.role = role.HYBRID;
		
		this.company = CompanyJpaDao.getCompany(company);
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getUserID() {
		return userID;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", role=" + role + "]";
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Role getRoles(){
		return role;
	}
	
	public void addStocks(int stockID, int amount){
		
		if(portfolio.containsKey(stockID)){
			portfolio.replace(stockID, portfolio.get(stockID)+amount);
		}
		else
			portfolio.put(stockID, amount);
	}
	
	public void tradeStrocks(int stockID, int amount){
		
		if(portfolio.containsKey(stockID)){
			if(portfolio.get(stockID) >= amount)
				portfolio.replace(stockID, portfolio.get(stockID)-amount);
			else
				System.out.println("Sorry not enough stocks");
		}
		else
			System.out.println("Sorry, you dont have that stock");
	}
	
	public String getPortfolio(){
		
		return portfolio.toString();
	}
	public void setRole(Role role) {

		this.role = role;
	}
	

	
	
	
}
