package com.fdmgroup;

import java.io.InputStream;
import java.util.Scanner;

public class AuthenticationController extends SessionController {

	String display;
	private static StringBuffer buf = new StringBuffer();
	Scanner scn;
	String line = "";

	private String username;
	String pass;

	private User user;

//	UserCollectionDAO<User> users = new UserCollectionDAO<>();
//	UserJpaDao users = new UserJpaDao();
	
	private InputStream sysIn;

	public AuthenticationController() {

		this(System.in);

		buf = new StringBuffer();

		buf.append("	Hello, welcome to the FDM group trading platform!\n");
		buf.append("	Please enter one of the following options:\n");
		buf.append("------------------------------------------------------------------\n");
		buf.append("1) login\n");
		buf.append("2) register\n");
		buf.append("e) exit\n");

	}

	public AuthenticationController(InputStream sysIn) {
		super();
		this.sysIn = sysIn;
	}

	@Override
	public String giveDisplay() {

		return buf.toString();

	}

	@Override
	public void updateView() {

		sv.update(buf.toString());

	}

	@Override
	public void controlApp() {

		System.setIn(sysIn);

		do {

			buf = new StringBuffer();

			buf.append("	Hello, welcome to the FDM group trading platform!\n");
			buf.append("	Please enter one of the following options:\n");
			buf.append("------------------------------------------------------------------\n");
			buf.append("1) login\n");
			buf.append("2) register\n");
			buf.append("e) exit\n");

			updateView();

			scn = new Scanner(System.in);

			line = scn.nextLine();

			if (line.equals("e"))
				break;

			switch (line) {
			case "1":

				buf = new StringBuffer();
				buf.append("Enter username: \n");
				updateView();

				username = scn.nextLine();

				buf = new StringBuffer();
				buf.append("Enter password: \n");
				updateView();

				pass = scn.nextLine();

				user = login(username, pass);
				if (user != null) {

					switch (UserJpaDao.getRoles(user)) {

					case HYBRID:
						HybridController hc = new HybridController(user);
						hc.controlApp();
						break;

					case ADMIN:
						AdminController ac = new AdminController(user);
						ac.controlApp();
						break;

					case COMPANY:
						CompanyController cc = new CompanyController(user);
						cc.controlApp();
						break;

					case SHAREHOLDER:
						UserController uc = new UserController(user);
						uc.controlApp();
						break;
					case BROKER:
						break;
					default:
						break;
					}

				}
				break;
			case "2":

				buf = new StringBuffer();
				buf.append("Enter username: \n");
				updateView();

				username = scn.nextLine();

				buf = new StringBuffer();
				buf.append("Enter password: \n");
				updateView();

				pass = scn.nextLine();

				register(username, pass);

				break;

			}
		} while (!line.equals("e"));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User login(String username, String password) {

		user = new User(username, password);

		String test = UserJpaDao.getUser(user).getPassword().trim();
		
		if (UserJpaDao.isIn(user)){
			if (user.getPassword().trim().equals(test)) {
				return UserJpaDao.getUser(user);
			}
		}

		buf = new StringBuffer();
		buf.append("Invalid Username/Password combination, please try again...\n");
		updateView();
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void resetDAO() {
		UserJpaDao.reset();
	}

	public User register(String username, String password) {

		user = new User(username, password);

		return UserJpaDao.newUser(user);
				
		// UserJDBCDAO.newUser(user);

		// return users.newUser(user);
	}

	public User register(String username, String password, String role) {

		user = new User(username, password, Role.valueOf(role));

		// return users.newUser(user);
//		return UserJDBCDAO.newUser(user);
		return UserJpaDao.newUser(user);
	}

	public User register(String username, String password, Company company) {

		user = new User(username, password, company);

		// return users.newUser(user);
//		return UserJDBCDAO.newUser(user);
		return UserJpaDao.newUser(user);
	}

	public User register(String username, String password, String role, Company company) {

		user = new User(username, password, "Hybrid", company);

		// return users.newUser(user);
//		return UserJDBCDAO.newUser(user);
		return UserJpaDao.newUser(user);
	}
	
/*	@Override
	public void finalize(){
		UserJpaDao.close();
	}*/

}
