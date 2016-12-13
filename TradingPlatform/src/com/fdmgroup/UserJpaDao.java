package com.fdmgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.mockito.internal.util.collections.ArrayUtils;

@NamedQueries({
		@NamedQuery(name = "getSpecificUser", query = "Select user from User user where user.username = :user"),
		@NamedQuery(name= "getAllUsers", query = "select user from User user")})
public class UserJpaDao extends DAO {
	private static EntityManager em;

	public UserJpaDao() {
		super();
		em = DbAccessor.getEntityManager();
	}

	public static void closeResources() {

	}

	public static User newUser(User newUser) {

		em.getTransaction().begin();

		em.persist(newUser);

		em.getTransaction().commit();

		TypedQuery<User> allPeople = em.createQuery("Select user from User user where user.username = :user",
				User.class);
		allPeople.setParameter("user", newUser.getUsername());

		User returnUser = allPeople.getSingleResult();

		UserRole ur = new UserRole(returnUser.getUserID(), Role.mapRole(returnUser.getRoles()));

		if (ur.getRoleId()!=5) {
			em.getTransaction().begin();
			em.persist(ur);
			em.getTransaction().commit();
		}
		else{
			em.getTransaction().begin();
			em.persist(new UserRole(returnUser.getUserID(), 2));
			em.persist(new UserRole(returnUser.getUserID(), 3));
			em.getTransaction().commit();
		}
		
		return returnUser;

	}

	public static User getUser(User user) {

		TypedQuery<User> allPeople = em.createQuery("Select user from User user where user.username = :user",
				User.class);
		allPeople.setParameter("user", user.getUsername());
		
		return allPeople.getSingleResult();
	}

	public static boolean isIn(User user) {

		TypedQuery<User> allPeople = em.createQuery("Select user from User user where user.username = :user",
				User.class);
		allPeople.setParameter("user", user.getUsername());

		User eval = allPeople.getSingleResult();

		return eval.equals(user);

	}

	public static void reset() {

	}

	public static void deleteUser(User delUser) {

	}

	public static Role getRoles(User user) {

		TypedQuery<UserRole> roles = em
				.createQuery("Select userrole from UserRole userrole where userrole.userID = :user", UserRole.class);
		roles.setParameter("user", user.getUserID());

		// User eval = roles.getSingleResult();

		// return eval.equals(user);

		/*
		 * if(roles.getResultList().equals(new ArrayList<UserRole>())){ return
		 * Role.Shareholder; }
		 */
		
		List<UserRole> returnRolls = roles.getResultList();

		if (returnRolls.size() > 1) {
			user.setRole(Role.HYBRID);
			return Role.HYBRID;
		}
		// else if(returnRolls.isEmpty()){
		// return Role.Shareholder;
		// }
		else
			return Role.mapRole(returnRolls.get(0).getRoleId());
	}

	public static List<User> getAllUsers() {

		TypedQuery<User> users = em.createQuery("select user from User user", User.class);
		
		return users.getResultList();
	}
	
	public static User modUser(User modUser){

		User oldUser = modUser;
		User refUser = getUser(modUser);
		
		em.getTransaction().begin();
		refUser = em.find(User.class, refUser.getUserID());
		refUser.setFirstName(oldUser.getFirstName());
		refUser.setLastName(oldUser.getLastName());
		refUser.setPassword(oldUser.getPassword());
		em.persist(refUser);
		em.getTransaction().commit();
		
		return refUser;
	}
	
	
	
	
	
	
	
	
	
	

}
