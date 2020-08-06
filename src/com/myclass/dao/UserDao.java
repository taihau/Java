package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.database.JDBCConnection;
import com.myclass.dto.LoginDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserDao {
	private final String QUERY_ALL = "SELECT * FROM users;";
	private final String QUERY_ADD = "INSERT INTO users(email, password, fullname, avatar, role_id) VALUES(?,?,?,?,?);";
	private final String QUERY_FIND_BY_ID = "SELECT * FROM users WHERE id = ?;";
	private final String QUERY_FIND_BY_ID_ROLEID = "SELECT u.email, u.password, u.fullname, u.avatar, r.description FROM users AS u " + 
						"JOIN roles AS " + 
						"ON u.role_id = r.id " + 
						"WHERE u.id = 2;";
	private final String QUERY_EDIT_BY_ID = "UPDATE users SET email = ?, fullname = ?, avatar = ?, role_id = ?, password = ? WHERE id = ?;";
	private final String QUERY_DELETE_ID = "DELETE FROM users WHERE id = ?;";
	private final String QUERY_FIND_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
	private final String QUERY_USER_ROLE_ID = "SELECT u.email, u.id, u.fullname, r.description FROM users AS u JOIN roles AS r ON u.role_id = r.id order by u.id ASC;";
	private final String QUERY_CHECK = "SELECT u.email, u.id, u.fullname, r.name, u.password, u.avatar FROM users AS u JOIN roles AS r ON u.role_id = r.id WHERE u.email = ?;";
	
	// ---------GET--------------

	public List<UserDto> FindAllUserRole() {
		List<UserDto> List = new ArrayList<UserDto>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_USER_ROLE_ID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				UserDto user = new UserDto();
				user.setId(resultset.getInt("id"));
				user.setFullname(resultset.getString("fullname"));
				user.setEmail(resultset.getString("email"));
				user.setDescription(resultset.getString("description"));
				List.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return List;
	}

	public List<User> getAll() {
		List<User> UserList = new ArrayList<User>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt("id"));
				user.setFullname(resultset.getString("fullname"));
				user.setEmail(resultset.getString("email"));
				user.setRoleID(resultset.getInt("role_id"));
				UserList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UserList;
	}

	public User FindByID(User user) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_FIND_BY_ID);
			statement.setInt(1, user.getId());
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				user.setId(resultset.getInt("id"));
				user.setFullname(resultset.getString("fullname"));
				user.setEmail(resultset.getString("email"));
				user.setAvatar(resultset.getString("avatar"));
				user.setRoleID(resultset.getInt("role_id"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public int DeleteById(int id) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_ID);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// ---------POST--------------
	public int NewAdd(User user) {
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_ADD);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleID());
			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int EditByID(User user) {

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_EDIT_BY_ID);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getAvatar());
			statement.setInt(4, user.getRoleID());
			statement.setString(5, user.getPassword());
			statement.setInt(6, user.getId());
			
			return statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-1");
		return -1;
	}

	public User FindByEmail(String email) {
		User user = new User();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_FIND_BY_EMAIL);
			statement.setString(1, email);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				user.setFullname(resultset.getString("fullname"));
				user.setAvatar(resultset.getString("avatar"));
				user.setEmail(resultset.getString("email"));
				user.setRoleID(resultset.getInt("role_id"));
				user.setPassword(resultset.getString("password"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return user;
	}

	public LoginDto checkLogin(String email) {
		LoginDto loginDto = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(QUERY_CHECK);
			statement.setString(1, email);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				loginDto = new LoginDto();
				loginDto.setId(resultset.getInt("id"));
				loginDto.setRolename(resultset.getString("name"));
				loginDto.setFullname(resultset.getString("fullname"));
				loginDto.setEmail(resultset.getString("email"));
				loginDto.setPassword(resultset.getString("password"));
				loginDto.setAvatar(resultset.getString("avatar"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDto;
	}
}
