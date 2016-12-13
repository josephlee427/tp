package com.fdmgroup;

import java.io.Serializable;

public class RoleCompKey implements Serializable{

	private int userID;
	private int roleId;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
		result = prime * result + userID;
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
		RoleCompKey other = (RoleCompKey) obj;
		if (roleId != other.roleId)
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}
	public RoleCompKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleCompKey(int userID, int roleId) {
		super();
		this.userID = userID;
		this.roleId = roleId;
	}
	
	
	
}
