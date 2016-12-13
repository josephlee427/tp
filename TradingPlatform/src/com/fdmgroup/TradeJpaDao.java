package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TradeJpaDao {

	private static EntityManager em;

	public TradeJpaDao() {

		em = DbAccessor.getEntityManager();

	}

	public static Trade newTrade(Trade newTrade) {

/*		Req parentRequest = RequestJpaDao.getRequest(newTrade.getReq_id());

		newTrade.setReq_id(parentRequest.getReqID());
		newTrade.setStockID(parentRequest.getStockID());

		switch (parentRequest.getbOrS()) {

		case "Buy":
			newTrade.setSeller(newTrade.getSeller());
			newTrade.setBuyer(parentRequest.getBuyer());
			newTrade.setBors("Sell");
			break;

		case "Sell":
			newTrade.setBuyer(newTrade.getSeller());
			newTrade.setSeller(parentRequest.getBuyer());
			newTrade.setBors("Buy");
			break;
		}

		newTrade.setTimeInForce(parentRequest.getInterval());
		parentRequest.fillShares(newTrade.getAmount());
		
		em.getTransaction().begin();
		
		em.persist(newTrade);
		em.persist(parentRequest);
		
		em.getTransaction().commit();

		return em.find(Trade.class,newTrade.getTradeID());*/
		
		return null;
	}

	public static Trade getTrade(Trade user) {

		return null;
	}

	public static boolean isIn(Trade trade) {
		return false;
	}

	public void reset() {

	}

	public List<Trade> getTrades() {
		return null;
	}

	public static List<Trade> matchTrades(User user) {
		
		TypedQuery<Trade> trades = em.createQuery("Select trade from Trade trade where trade.buyer = :user or trade.seller = :user", Trade.class);
		trades.setParameter("user", user.getUserID());
		
		
		return trades==null ? null : trades.getResultList();
		
	}

	public static void resolveTrade() {
		
	}

	public static List<Trade>getAllTrades() {
		
		TypedQuery<Trade> trades = em.createQuery("Select trade from Trade trade", Trade.class);
		
		return trades.getResultList();
	}

}

/*
 * package com.fdmgroup;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.sql.Statement; import java.util.List;
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.EntityManagerFactory; import
 * javax.persistence.NamedQueries; import javax.persistence.NamedQuery; import
 * javax.persistence.Persistence; import javax.persistence.TypedQuery;
 * 
 * 
 * @NamedQueries({
 * 
 * @NamedQuery(name = "getSpecificUser", query =
 * "Select user from User user where user.username = :user") }) public class
 * UserJpaDao extends DAO{
 * 
 * private static EntityManagerFactory emf; private static EntityManager em;
 * 
 * public UserJpaDao() { super(); emf =
 * Persistence.createEntityManagerFactory("TradingPlatform"); em =
 * emf.createEntityManager(); }
 * 
 * public static void closeResources() {
 * 
 * }
 * 
 * public static User newUser(User newUser) {
 * 
 * em.getTransaction().begin();
 * 
 * em.persist(newUser);
 * 
 * em.getTransaction().commit();
 * 
 * TypedQuery<User> allPeople= em.createQuery(
 * "Select user from User user where user.username = :user", User.class);
 * allPeople.setParameter("user", newUser.getUsername());
 * 
 * 
 * List<User> thing = allPeople.getResultList();
 * 
 * for (User returnUser : thing) { return returnUser; }
 * 
 * return null; }
 * 
 * public static User getUser(User user) {
 * 
 * TypedQuery<User> allPeople= em.createQuery(
 * "Select user from User user where user.username = :user", User.class);
 * allPeople.setParameter("user", user.getUsername());
 * 
 * List<User> thing = allPeople.getResultList();
 * 
 * for (User returnUser : thing) { return returnUser; }
 * 
 * return null;
 * 
 * return thing.get(0); }
 * 
 * public boolean isIn(User user) {
 * 
 * TypedQuery<User> allPeople= em.createQuery(
 * "Select user from User user where user.username = :user", User.class);
 * allPeople.setParameter("user", user.getUsername());
 * 
 * User eval = allPeople.getSingleResult();
 * 
 * return eval.equals(user);
 * 
 * }
 * 
 * public static void reset() {
 * 
 * }
 * 
 * public static void deleteUser(User delUser) {
 * 
 * }
 * 
 * public static void close(){ em.close(); emf.close(); }
 * 
 * }
 */
