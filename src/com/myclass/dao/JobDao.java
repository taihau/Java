package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.database.JDBCConnection;
import com.myclass.entity.Job;
import com.myclass.entity.Role;
import com.myclass.entity.User;

public class JobDao {
	private final String QUERY_ALL = "SELECT * FROM jobs;";
	private final String QUERY_FIND_BY_ID = "SELECT * FROM jobs WHERE id = ?;";
	private final String QUERY_DELETE_BY_ID = "DELETE FROM jobs WHERE id = ?;";
	private final String QUERY_ADD_NEW = "INSERT INTO jobs(name, start_date, end_date) VALUES (?,?,?);";
	private final String QUERY_EDIT_BY_ID = "UPDATE jobs SET name = ?, start_date = ?, end_date= ? WHERE id =?;";

	
	public List<Job> getAll() {
		List<Job> ListJob = new ArrayList<Job>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Job job = new Job();
				job.setId(resultset.getInt("id"));
				job.setName(resultset.getString("name"));
				job.setStartDate(resultset.getDate("start_date"));
				job.setEndDate(resultset.getDate("end_date"));
				ListJob.add(job);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ListJob;
	}

	public Job FindById(int id) {
		Job job = new Job();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				job.setId(resultset.getInt("id"));
				job.setName(resultset.getString("name"));
				job.setStartDate(resultset.getDate("start_date"));
				job.setEndDate(resultset.getDate("end_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	public int DeleteId(int id) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_BY_ID);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/// ---------- POST---------------------
	public int NewAdd(Job job) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ADD_NEW);
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			int resultset = statement.executeUpdate();
			return resultset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int EditById(Job job) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_EDIT_BY_ID);
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			statement.setInt(4, job.getId());
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
