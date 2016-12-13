package com.fdmgroup;

import java.util.ArrayList;
import java.util.List;

public class RequestCollectionDAO extends DAO<Req> {

	/*
	 * List<Req> requests = new ArrayList<>();
	 * 
	 * public static void getRequests(){
	 * 
	 * }
	 * 
	 * public void newRequest(Req request) {
	 * 
	 * }
	 */

	static int numRequests = 0;
	private static List<Req> requests = new ArrayList<>();

	public static Req newRequest(Req newReq) {

		numRequests++;
		newReq.setReqID(numRequests);
		if (requests.contains(newReq)) {
			return null;
		}
		requests.add(newReq);

		return requests.get(requests.indexOf(newReq));
	}

	public static Req getRequest(Req user) {

		if (requests.contains(user)) {
			return requests.get(requests.indexOf(user));
		}

		return null;
	}

	public static boolean isIn(Req trade) {
		return requests.contains(trade);
	}

	public void reset() {
		numRequests = 0;
		requests = new ArrayList<>();
	}

	public List<Req> getTrades() {
		return requests;
	}

	public static List<Req> getAllRequests() {

		return requests;
	}

	public static List<Req> matchRequests(User user) {

		List<Req> returnList = new ArrayList<>();

		for (Req req : requests) {
			if (req.getBuyer() == user.getUserID())
				returnList.add(req);
		}

		return returnList;
	}
}
