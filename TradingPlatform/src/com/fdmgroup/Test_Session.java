package com.fdmgroup;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Request;

public class Test_Session {

	AuthenticationController ac;
	User user;
	//UserCollectionDAO ucDAO;
	//UserJDBCDAO ujDAO = new UserJDBCDAO();
	
	@BeforeClass
	public static void init(){
		
	}
	
	@AfterClass
	public static void destroy(){
		
	}
	
	//---------------------------------------------------------------------------------
	@Before
	public void setup(){
		ac = new AuthenticationController();
		ac.resetDAO();
		new UserJpaDao();
	}
	//----------------------------------------------------------------------------
	
	@After
	public void teardown(){
		
	}
	
	@Test
	public void testRegister(){
		user = new User("dave", "guy");
		assertEquals(user, ac.login("dave", "guy"));
	}
/*	
	@Test
	public void createNewLogin(){
		user = ac.register("user1", "pass");
		assertEquals(user, ac.login("user1", "pass"));
	}

	@Test
	public void createNewLoginWithRole(){
		user = ac.register("user1", "pass", "Admin");
		assertEquals(user, ac.login("user1", "pass"));
	}
	
	@Test
	public void createNewLoginWithCompany(){
		user = ac.register("user1", "pass", new Company("testComp", 0));
		assertEquals(user, ac.login("user1", "pass"));
		assertEquals("Company", user.getRoles());
		
	}
	
	@Test
	public void createNewLoginWithCompAndRole(){
		user = ac.register("user1", "pass","Shareholder", new Company("testComp", 0));
		assertEquals(user, ac.login("user1", "pass"));
		assertEquals("Hybrid", user.getRoles());
	}

	@Test
	public void registerNewUserAdmin(){
		user = ac.register("user1", "pass","Admin");
		assertEquals(user, ac.login("user1", "pass"));
		assertEquals("Admin", user.getRoles());
	}
	
	@Test
	public void createUserController(){
		user = new User("user1", "pass");
		UserController uc = new UserController(user);
		assertEquals(user, uc.getUser());
	}
	
	@Test
	public void createExistingUser(){
		user = ac.register("user1", "pass","Admin");
		user = ac.register("user1", "pass","Admin");
		assertNull(user);
	}
	
	@Test
	public void attemptLoginToNonUser(){
		user = ac.register("user1", "pass");
		assertNull(ac.login("user2", "pass"));
	}
	
	@Test
	public void attemptLoginWithWrongPass(){
		user = ac.register("user1", "pass1");
		assertNull(ac.login("user1", "pass2"));
	}
	
	@Test
	public void loginToUserMadeByAdmin(){
		
		user = ac.register("user1", "pass", "Admin");
		AdminController adc = new AdminController(user);
		
		User user2 = adc.addUser("user2", "pass");
		
		assertEquals(user2, ac.login("user2", "pass"));
	}
	
	@Test
	public void loginToCompanyUserMadeByAdmin(){
		
		user = ac.register("user1", "pass", "Admin");
		AdminController adc = new AdminController(user);
		
		User user2 = adc.addUser("user2", "pass", new Company("comp", 0));
		
		assertEquals(user2, ac.login("user2", "pass"));
		assertEquals("Company", ac.login("user2", "pass").getRoles());
	}
	
	@Test
	public void loginToHybridUserMadeByAdmin(){
		
		user = ac.register("user1", "pass", "Admin");
		AdminController adc = new AdminController(user);
		
		User user2 = adc.addUser("user2", "pass","Shareholder", new Company("comp", 0));
		
		assertEquals(user2, ac.login("user2", "pass"));
		assertEquals("Hybrid", ac.login("user2", "pass").getRoles());
	}
	
	@Test
	public void loginToAdminUserMadeByAdmin(){
		
		user = ac.register("user1", "pass", "Admin");
		AdminController adc = new AdminController(user);
		
		User user2 = adc.addUser("user2", "pass","Admin");
		
		assertEquals(user2, ac.login("user2", "pass"));
		assertEquals("Admin", ac.login("user2", "pass").getRoles());
	}
	*/
	
	
	
}
