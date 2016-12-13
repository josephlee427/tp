package com.fdmgroup;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO extends DAO {

	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String dbUName = "MS_tradingPlatform";
	private static final String dbPw = "omikronthenomadsoul";

	private static Connection conn;
	private static Statement st;
	private static ResultSet rst;

	public UserJDBCDAO() {
		super();
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(url, dbUName, dbPw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources() {
		try {
			if (rst != null && !rst.isClosed())
				rst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (st != null && !st.isClosed())
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User newUser(User newUser) {

		boolean isCompany = (newUser.getCompany()!=null);

		String whichSequence = isCompany ? "tp_company_id_seq.nextval" : "tp_user_id_seq.nextval";

		String query = "Insert into tp_users (user_id, user_name, pw, first_name, last_name) values(" + whichSequence
				+ ", ?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getFirstName());
			ps.setString(4, newUser.getLastName());
			ps.executeUpdate();

			/*
			 * if(isCompany){ query =
			 * "Insert into tp_users (user_id, user_name, pw, first_name, last_name) values("
			 * + whichSequence + ", ?, ?, ?, ?)"; }
			 */

			query = "Select user_id, user_name, pw, first_name, last_name " + "from tp_users " + "where user_name = '"
					+ newUser.getUsername() + "'";

			ps = conn.prepareStatement(query);
			rst = ps.executeQuery(query);
			rst.next();

			User returnUser = new User(rst.getInt("user_id"), rst.getString("user_name"), rst.getString("pw"), rst.getString("first_name"), rst.getString("last_name"));

			if (!newUser.getRoles().equals(Role.SHAREHOLDER)) {

				query = "Insert into tp_user_role (user_id, role_id) values(" + returnUser.getUserID() + ", " + Role.mapRole(newUser.getRoles()) +")";
				ps = conn.prepareStatement(query);
				ps.executeUpdate();
			}

			reset();

			return returnUser;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static User getUser(User user) {

		return null;
	}

	public boolean isIn(User user) {
		return false;
	}

	public static void reset() {
		closeResources();
		try {
			conn = DriverManager.getConnection(url, dbUName, dbPw);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Role> getRoles(User user) {
		
		
		
		
		
		return null;
	}
}
