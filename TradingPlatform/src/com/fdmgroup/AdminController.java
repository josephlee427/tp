package com.fdmgroup;

import java.util.Date;
import java.util.Scanner;

public class AdminController extends SessionController {
/*	private TradeJpaDao<Trade> trades;
	private UserJpaDao<User> users;
	private RequestJpaDao requests;
	private CompanyJpaDao companies;*/
	
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
		AdminController.buf = buf;
	}

	@Override
	public void controlApp() {
		updateView();
		
		String uname;
		String pwd;
		String subchoice;
		
		Scanner scn = new Scanner(System.in);
		
		String line = scn.nextLine();
		while(!line.equals("l")){
			
			buf = new StringBuffer();
			
			switch (line){
				case "1":
					buf.append(getProfile());
					updateView();
					break;
				case "2":
					buf.append("Adding user...\n");
					buf.append("Specify username:\n");
					updateView();
					
					uname = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("Specify password:\n");
					updateView();
					
					pwd = scn.nextLine();
					
					buf = new StringBuffer();
					buf.append("Please pick one of the following options:\n");
					buf.append("1) Done, create user\n");
					buf.append("2) Add Role\n");
					buf.append("3) Add Company\n");
					buf.append("4) Make hybrid and add Company\n");
					updateView();
					
					subchoice = scn.nextLine();
					
					buf = new StringBuffer();
					
					switch(subchoice){
						case "1":
							buf.append("User added:\n");
							buf.append(addUser(uname, pwd).toString()+"\n");
							break;
							
						case "2":
							buf.append("Please specify user role: (Shareholder/Amin/Company/Hybrid)\n");
							updateView();
							buf = new StringBuffer();
							buf.append(addUser(uname, pwd, scn.nextLine())+"\n");
							break;
							
						case "3":
							buf.append("List of existing companies:\n");
							buf.append(CompanyJpaDao.getAllCompanies() +"\n");
							buf.append("Please specify user company:\n");
							updateView();
							buf = new StringBuffer();
							buf.append("User added\n"+addUser(uname, pwd, CompanyJpaDao.getCompany(new Company(scn.nextLine(), 0)))+"\n");
							break;
							
						case "4":
							buf.append("List of existing companies:\n");
							buf.append(CompanyJpaDao.getAllCompanies() +"\n");
							buf.append("Please specify user company:\n");
							updateView();
							buf = new StringBuffer();
							buf.append("User added\n"+addUser(uname, pwd,"Hybrid", CompanyJpaDao.getCompany(new Company(scn.nextLine(), 0)))+"\n");
							break;
					}
					
					break;
				case "3":
					buf.append("Deleting user...\n");
					buf.append("Specify username:\n");
					updateView();
					buf = new StringBuffer();
					uname = scn.nextLine();
					delUser(uname);
					break;
				case "4":
					String fname, lname, comp, role;
					
					buf.append("Modifying user...\n");
					buf.append("Specify username:\n");
					updateView();
					buf = new StringBuffer();
					uname = scn.nextLine();
					
					buf.append("Specify password:\n");
					updateView();
					buf = new StringBuffer();
					pwd = scn.nextLine();
					
					buf.append("Specify User first name:\n");
					updateView();
					buf = new StringBuffer();
					fname = scn.nextLine();
					
					buf.append("Specify User last name:\n");
					updateView();
					buf = new StringBuffer();
					lname = scn.nextLine();
					
					buf.append("Specify User company:\n");
					updateView();
					buf = new StringBuffer();
					comp = scn.nextLine();
					
					buf.append("Specify User role:\n");
					updateView();
					buf = new StringBuffer();
					role = scn.nextLine();
					
					modUser(uname, pwd, fname, lname, comp, role);
					
					buf.append("User modified...\n");
					
					break;
				case "5":
					getTradeHistory();
					break;
				case "6":
					getRequestHistory();
					break;
				case "e":
					exit();
			}
			
			buf.append("press enter to continue...\n");
			updateView();
			
			scn.nextLine();
			
			buf = new StringBuffer();
			
			buf.append("	Logged as: " + user.getUsername() + "!\n");
			buf.append("	Please enter one of the following options:\n");
			buf.append("------------------------------------------------------------------\n");
			buf.append("1) View Profile\n");
			buf.append("2) Add User\n");
			buf.append("3) Delete User\n");
			buf.append("4) Modify User\n");
			buf.append("5) View all trades\n");
			buf.append("6) View all requests\n");
			buf.append("l) logout\n");
			buf.append("e) exit\n");
			
			updateView();
			
			line = scn.nextLine();
		};
		
		System.out.println("    logging out...");
	}

	public AdminController(User user) {
		super();
		this.user = user;
		
		buf = new StringBuffer();
		
		buf.append("	Logged as: " + user.getUsername() + "!\n");
		buf.append("	Please enter one of the following options:\n");
		buf.append("------------------------------------------------------------------\n");
		buf.append("1) View Profile\n");
		buf.append("2) Add User\n");
		buf.append("3) Delete User\n");
		buf.append("4) Modify User\n");
		buf.append("5) View all trades\n");
		buf.append("6) View all requests\n");
		buf.append("l) logout\n");
		buf.append("e) exit\n");
		
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
		
		buf = new StringBuffer();
		for (Trade trade : TradeJpaDao.getAllTrades()) {
			buf.append(trade+"\n");
		}
	}

	public void getRequestHistory() {
		buf = new StringBuffer();
		for (Req request: RequestJpaDao.getAllRequests()) {
			buf.append(request+"\n");
		}
	}
	
	public User addUser(String uname, String pwd){
		
		User newUser = new User(uname, pwd);
		
		return UserJpaDao.newUser(newUser);
	}
	public User addUser(String uname, String pwd, String role){
		
		User newUser = new User(uname, pwd, Role.valueOf(role));
		
		return UserJpaDao.newUser(newUser);
	}
	public User addUser(String uname, String pwd, Company comp){
		
		User newUser = new User(uname, pwd, comp);
		
		return UserJpaDao.newUser(newUser);
	}
	public User addUser(String uname, String pwd, String role, Company comp){
		
		User newUser = new User(uname, pwd, role, comp);
		
		return UserJpaDao.newUser(newUser);
	}
	
	public void delUser(String uname){
		
		if(user.getRoles().equals("Admin")){
			User delUser = new User(uname, "pwd");
			UserJpaDao.deleteUser(delUser);
		}
		else{
			buf = new StringBuffer();
			buf.append("Sorry you do not have admin permissions");
			updateView();
		}
	}
	
	
	public void modUser(String uname, String newPass, String firstName, String lastName, String comp, String role){
	
		User modUser = new User(uname, "");
		
		Company modComp = CompanyJpaDao.getCompany(new Company(comp, 0));
		
		UserJpaDao.getUser(modUser).setPassword(newPass);
		UserJpaDao.getUser(modUser).setFirstName(firstName);
		UserJpaDao.getUser(modUser).setLastName(lastName);
		UserJpaDao.getUser(modUser).setCompany(modComp);
		UserJpaDao.getUser(modUser).setRole(Role.valueOf(role));
		
	}
	
	public Company newCompany(String name, Float startPrice){
		
		Company newCompany = new Company(name, startPrice);
		
		return CompanyJpaDao.newCompany(newCompany);
	}
	
	private Company modCompany(String compName, String newName) {

		Company comp = new Company(compName, 0);
		
		CompanyJpaDao.getCompany(comp).setName(newName);
		
		comp = new Company(newName, 0);
		
		return CompanyJpaDao.getCompany(comp);
	}

	private void deleteCompany(String compName) {
/*		Company delComp = new Company(compName, 0);
		
		CompanyJpaDao.deleteCompany(delComp);*/
	}
}
