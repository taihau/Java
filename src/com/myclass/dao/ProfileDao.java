package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.myclass.database.JDBCConnection;
import com.myclass.entity.User;

public class ProfileDao {
	private final String QUERY_EDIT_BY_ID = "UPDATE users SET email = ?, fullname = ?, password = ? WHERE id = ?;";
	
	public int EditByID(User user) {

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_EDIT_BY_ID);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getId());
			
			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-1");
		return -1;
	}
}
