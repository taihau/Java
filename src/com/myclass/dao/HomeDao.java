package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.database.JDBCConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.User;

public class HomeDao {
		private final String QUERY_PRESENT = "SELECT status.id AS status_id, status.name AS statusName , round((COUNT(status_id) * 100 / ( select count(*) from tasks )), 2) AS percent \r\n" + 
				"FROM tasks join status on tasks.status_id = status.id\r\n" + 
				"			GROUP BY status_id;";
		
		public List<TaskDto> FindPercentAll() {
			List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
			try(Connection conn = JDBCConnection.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(QUERY_PRESENT);
				ResultSet resultset = statement.executeQuery();
				while (resultset.next()) {
					TaskDto taskDto = new TaskDto();
					taskDto.setStatus_id(resultset.getInt("status_id"));
					taskDto.setStatusName(resultset.getString("statusName"));
					taskDto.setPercent(resultset.getFloat("percent"));
					listTaskDto.add(taskDto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listTaskDto;
		}
}
