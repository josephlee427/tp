package com.fdmgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserCollectionDAO<T> extends DAO<T>{

		static int numUsers = 0;
		static List<User> users = new ArrayList<>();

		public static User newUser(User newUser) {
			
			if(users.contains(newUser)){
				return null;
			}
			
			newUser.setUserID(numUsers);
			users.add(newUser);
			numUsers++;
			return users.get(users.indexOf(newUser));
		}

		public UserCollectionDAO() {
			super();
		}

		public static User getUser(User user) {
			
			if(users.contains(user)){
				return users.get(users.indexOf(user));
			}
			
			return null;
		}
		
		public boolean isIn(User user){
			return users.contains(user);
		}
		
		public void reset(){
			numUsers = 0;
			users = new ArrayList<>();
		}

		public static void deleteUser(User delUser) {
			if(users.contains(delUser)){
				users.remove(delUser);
			}
			else
				System.out.println("that user does not exist");
		}
		
	
}
