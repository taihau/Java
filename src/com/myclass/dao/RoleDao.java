package com.myclass.dao;

import com.myclass.database.JDBCConnection;
import com.myclass.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
	private final String QUERY_GET_ALL = "SELECT * FROM roles";
	private final String QUERY_ADD = "INSERT INTO roles(name, description) VALUES (?,?)";
	private final String QUERY_BY_ID = "SELECT * FROM roles WHERE id = ?";
	private final String QUERY_EDIT_BY_ID = "UPDATE roles SET name = ?, description = ? WHERE id = ?";
	private final String QUERY_DELETE =  "DELETE FROM roles WHERE id = ?";
	
	/// -----------GET---------------
	public List<Role> getAll(){
		List<Role> ListRole = new ArrayList<Role>();
		
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while(resultset.next()) {
				Role role = new Role();
				role.setId(resultset.getInt("id"));
				role.setName(resultset.getString("name"));
				role.setDescription(resultset.getString("description"));
				ListRole.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ListRole;
	}
	public Role FindById(int id) {
		Role role = new Role();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_BY_ID);
			statement.setInt(1, id);
			ResultSet resultset = statement.executeQuery();
			if(resultset.next()) {
				role.setId(resultset.getInt("id"));
				role.setName(resultset.getString("name"));
				role.setDescription(resultset.getString("description"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	public int DeleteId(int id) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, id);
			int resultset = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/// ---------- POST---------------------
	public Role NewAdd(Role role) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ADD);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			
			int resultset = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Role EditById(Role role) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement  = conn.prepareStatement(QUERY_EDIT_BY_ID);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId());
			
			int resultset = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
