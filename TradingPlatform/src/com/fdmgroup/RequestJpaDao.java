package com.fdmgroup;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class RequestJpaDao {

	private static EntityManager em;
	
	public static void getRequests(){
		
	}

	public RequestJpaDao(){
		em = DbAccessor.getEntityManager();
	}
	
	public static void RequestJpaDao(Req request) {
		
	}

	public static Req newRequest(Req newReq) {
		
		em.getTransaction().begin();
		
		em.persist(newReq);
		
		em.createStoredProcedureQuery("tp_request_pkg.ProcessRequests").executeUpdate();
		
		em.getTransaction().commit();
		
//		TypedQuery<Req> allPeople= em.createQuery("Select user from User user where user.username = :user", Req.class);
//		allPeople.setParameter("user", newReq.getReqID());
		
		return em.find(Req.class, newReq.getReqID());
		
/*		TypedQuery<User> allPeople= em.createQuery("Select user from User user where user.username = :user", User.class);
		allPeople.setParameter("user", newReq.getReqID());
		
		
		List<User> thing = allPeople.getResultList();
		
		for (User returnUser : thing) {
			return returnUser;
		}
		*/
	}

	public static Req getRequest(Req user) {
	
		return null;
	}
	
	public static boolean isIn(Req trade){
		return false;
	}
	
	public void reset(){
	}

	public List<Req> getTrades() {
		return null;
	}

	public static List<Req> getAllRequests() {
		
		TypedQuery<Req> reqs = em.createQuery("Select req from Req req", Req.class);
		
		return reqs.getResultList();
	}
	
	public static List<Req> matchRequests(User user){

		TypedQuery<Req> requests = em.createQuery("Select req from Req req where req.buyer = :user", Req.class);
		requests.setParameter("user", user.getUserID());
		
		return requests.getResultList();
		
	}

	public static String getActiveRequests() {

		TypedQuery<Req> allRequests= em.createQuery("Select req from Req req where req.state = :arg", Req.class);
		allRequests.setParameter("arg", Status.ACTIVE.toString());
		
		List<Req> thing = allRequests.getResultList();
		
		StringBuilder returnString = new StringBuilder();
		
		for (Req request : thing) {
			returnString.append(request.toString()+"\n");
		}
		
		return returnString.toString();
		
	}

	public static Req getRequest(int req_id) {
		return em.find(Req.class, req_id);
	}
	
}


/*package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

public class RequestCollectionDAO extends DAO<Req> {

	List<Req> requests = new ArrayList<>();
	
	public static void getRequests(){
		
	}

	public void newRequest(Req request) {
		
	}
	
	
	static int numRequests = 0;
	private static List<Req> requests = new ArrayList<>();

	public static Req newRequest(Req newReq) {
		
		numRequests++;
		newReq.setReqID(numRequests);
		if(requests.contains(newReq)){
			return null;
		}
		requests.add(newReq);
		
		return requests.get(requests.indexOf(newReq));
	}

	public static Req getRequest(Req user) {
		
		if(requests.contains(user)){
			return requests.get(requests.indexOf(user));
		}
		
		return null;
	}
	
	public static boolean isIn(Req trade){
		return requests.contains(trade);
	}
	
	public void reset(){
		numRequests = 0;
		requests = new ArrayList<>();
	}

	public List<Req> getTrades() {
		return requests;
	}

	public static List<Req> getAllRequests() {
		
		return requests;
	}
	
	public static List<Req> matchRequests(User user){
		
		List<Req> returnList = new ArrayList<>();
		
		for (Req req : requests) {
			if(req.getBuyer()==user.getUserID())
				returnList.add(req);
		}
		
		return returnList;
	}
}
*/