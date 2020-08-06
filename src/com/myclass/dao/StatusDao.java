package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.database.JDBCConnection;
import com.myclass.entity.Status;

public class StatusDao {
	
	private final String QUERY_GET_ALL = "SELECT * FROM status";
	
	public List<Status> getAll(){
		List<Status> listStatus = new ArrayList<Status>();
		
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Status status = new Status();
				status.setId(resultset.getInt("id"));
				status.setName(resultset.getString("name"));
				listStatus.add(status);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStatus;
	}
}
