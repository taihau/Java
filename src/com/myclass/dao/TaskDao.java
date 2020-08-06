package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.database.JDBCConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.entity.User;

public class TaskDao {
	private final String QUERY_ALL = "SELECT * FROM tasks;";
	private final String QUERY_JOIN_USER_JOB_STATUS = "SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, u.fullname AS userfullname, j.name AS jobname , s.name AS statusname " + 
			"FROM tasks AS t " + 
			"JOIN users AS u ON t.user_id = u.id " + 
			"JOIN jobs AS j ON t.job_id = j.id " + 
			"JOIN status AS s ON t.status_id = s.id "+
			"ORDER BY id ASC;";
	private final String QUERY_ADD = "INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES(?,?,?,?,?,?);";
	private final String QUERY_FIND_ID = "SELECT * FROM tasks WHERE id = ?;";
	private final String QUERY_EDIT_BY_ID = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, job_id = ?, status_id = ? "
			+ "WHERE id = 1; ";
	private final String QUERY_DELETE_BY_ID = "DELETE FROM tasks WHERE id = ?;";
	private final String QUERY_USER = "SELECT distinct  t.user_id, u.fullname AS userfullname, u.avatar  " + 
			"FROM tasks AS t " + 
			"JOIN users AS u ON t.user_id = u.id " + 
			"WHERE t.job_id = ?;";
	private final String QUERY_JOIN_WHERE_JOBID = "SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, u.fullname AS userfullname, j.name AS jobname , s.name AS statusname " 
			+ "FROM tasks AS t "
			+ "JOIN users AS u ON t.user_id = u.id "
			+ "JOIN jobs AS j ON t.job_id = j.id "
			+ "JOIN status AS s ON t.status_id = s.id "
			+ "WHERE t.job_id = ? ";
	private final String QUERY_PERCENT = "SELECT status.id, status.name,\r\n" + 
			"round((count(status.id)/(SELECT COUNT(*) FROM tasks WHERE tasks.job_id = jobs.id))* 100,2)  as percent\r\n" + 
			"FROM jobs join tasks on (jobs.id = tasks.job_id)\r\n" + 
			"		  join status on (status.id = tasks.status_id)\r\n" + 
			"WHERE jobs.id = ?\r\n" + 
			"group by status.id;";
	private final String QUERY_TASK_USERID = "SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, u.fullname AS userfullname, j.name AS jobname , s.name AS statusname \r\n" + 
			"			FROM tasks AS t\r\n" + 
			"			JOIN users AS u ON t.user_id = u.id\r\n" + 
			"			JOIN jobs AS j ON t.job_id = j.id\r\n" + 
			"			JOIN status AS s ON t.status_id = s.id\r\n" + 
			"			WHERE u.id = ?;";
	private final String QUERY_PERSENT_USERID = "SELECT status.id AS status_id, status.name AS statusName , round((COUNT(status_id) * 100 / ( select count(*) from tasks where tasks.user_id = ? )), 2) AS percent \r\n" + 
			"FROM tasks join status on tasks.status_id = status.id\r\n" + 
			"			where tasks.user_id = ?\r\n" + 
			"			GROUP BY status_id;";
	
	public List<TaskDto> GetJoinAll(){
		List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_JOIN_USER_JOB_STATUS);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(resultset.getInt("id"));
				taskDto.setName(resultset.getString("name"));
				taskDto.setStartDate(resultset.getDate("start_date"));
				taskDto.setEndDate(resultset.getDate("end_date"));
				taskDto.setUserName(resultset.getString("userfullname"));
				taskDto.setJobName(resultset.getString("jobname"));
				taskDto.setStatusName(resultset.getString("statusname"));
				listTaskDto.add(taskDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTaskDto;
	}
	
	public int NewAdd(Task task) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ADD);
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStartDate());
			statement.setDate(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			return statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public Task FindID(Task task) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement satement = conn.prepareStatement(QUERY_FIND_ID);
			satement.setInt(1, task.getId());
			ResultSet resultset = satement.executeQuery();
			if(resultset.next()) {
				task.setName(resultset.getString("name"));
				task.setStartDate(resultset.getDate("start_date"));
				task.setEndDate(resultset.getDate("end_date"));
				task.setUserId(resultset.getInt("user_id"));
				task.setJobId(resultset.getInt("job_id"));
				task.setStatusId(resultset.getInt("status_id"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return task;
	}
	
	public int EditById(Task task) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_EDIT_BY_ID);
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStartDate());
			statement.setDate(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			return statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int DeleteById(int id) {
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_BY_ID);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<TaskDto> GetByJobID(){
		List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_JOIN_USER_JOB_STATUS);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(resultset.getInt("id"));
				taskDto.setName(resultset.getString("name"));
				taskDto.setStartDate(resultset.getDate("start_date"));
				taskDto.setEndDate(resultset.getDate("end_date"));
				taskDto.setUserName(resultset.getString("userfullname"));
				taskDto.setJobName(resultset.getString("jobname"));
				taskDto.setStatusName(resultset.getString("statusname"));
				listTaskDto.add(taskDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTaskDto;
	}
	
	public List<User> FindUserID(int jobid){
		List<User> ListTask = new ArrayList<User>();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_USER);
			statement.setInt(1, jobid);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt("user_id"));
				user.setFullname(resultset.getString("userfullname"));
				user.setAvatar(resultset.getString("avatar"));
				ListTask.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListTask;
	}
	
	public List<TaskDto> FindJoinJobID(int jobID){
		List<TaskDto> ListTaskDto = new ArrayList<TaskDto>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_JOIN_WHERE_JOBID);
			statement.setInt(1, jobID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(resultset.getInt("id"));
				taskDto.setName(resultset.getString("name"));
				taskDto.setStartDate(resultset.getDate("start_date"));
				taskDto.setEndDate(resultset.getDate("end_date"));
				taskDto.setUser_id(resultset.getInt("user_id"));
				taskDto.setJob_id(resultset.getInt("job_id"));
				taskDto.setStatus_id(resultset.getInt("status_id"));
				taskDto.setUserName(resultset.getString("userfullname"));
				taskDto.setJobName(resultset.getString("jobname"));
				taskDto.setStatusName(resultset.getString("statusname"));
				ListTaskDto.add(taskDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListTaskDto;
	}
	
	public List<TaskDto> FindPercent(int status_id) {
		List<TaskDto> listTask = new ArrayList<TaskDto>();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_PERCENT);
			statement.setInt(1, status_id);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setStatus_id(resultset.getInt("id"));
				taskDto.setStatusName(resultset.getString("name"));
				taskDto.setPercent(resultset.getFloat("percent"));
				listTask.add(taskDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTask;
	}
	
	public List<TaskDto> FindTaskUserID(User user) {
		List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_TASK_USERID);
			statement.setInt(1, user.getId());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(resultset.getInt("id"));
				taskDto.setName(resultset.getString("name"));
				taskDto.setStartDate(resultset.getDate("start_date"));
				taskDto.setEndDate(resultset.getDate("end_date"));
				taskDto.setUser_id(resultset.getInt("user_id"));
				taskDto.setJob_id(resultset.getInt("job_id"));
				taskDto.setStatus_id(resultset.getInt("status_id"));
				taskDto.setUserName(resultset.getString("userfullname"));
				taskDto.setJobName(resultset.getString("jobname"));
				taskDto.setStatusName(resultset.getString("statusname"));
				listTaskDto.add(taskDto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTaskDto;
	}
	
	public List<TaskDto> GoupPersentUserID(int status_id) {
		List<TaskDto> listTask = new ArrayList<TaskDto>();
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_PERSENT_USERID);
			statement.setInt(1, status_id);
			statement.setInt(2, status_id);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setStatus_id(resultset.getInt("status_id"));
				taskDto.setStatusName(resultset.getString("statusName"));
				taskDto.setPercent(resultset.getFloat("percent"));
				listTask.add(taskDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTask;
	}
	
}
