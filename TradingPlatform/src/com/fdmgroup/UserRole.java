package com.fdmgroup;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(RoleCompKey.class)
@Table(name = "tp_user_role")
public class UserRole {
	
	@Id
//	@ManyToOne(optional=false, targetEntity=User.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Column(name = "user_id")
	private int userID;
	@Id
	@Column(name = "role_id")
	private int roleId;
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRole(int userID, int roleId) {
		super();
		this.userID = userID;
		this.roleId = roleId;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
