package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.ProfileDao;
import com.myclass.dao.RoleDao;
import com.myclass.dao.TaskDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.LoginDto;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.util.UrlConstants;

@WebServlet(urlPatterns = {UrlConstants.PROFILE_ALL, UrlConstants.PROFILE_EDIT, UrlConstants.PROFILE_DETAILS})
public class ProfileController extends HttpServlet {
	TaskDao taskDao = null;
	User user = null;
	RoleDao roleDao = null;
	UserDao userDao = null;
	ProfileDao profileDao = null;
	@Override
	public void init() throws ServletException {
		taskDao = new TaskDao();
		roleDao = new RoleDao();
		userDao = new UserDao();
		profileDao = new ProfileDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			return;
		}
		
		LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN");
		
		switch (action) {
		case UrlConstants.PROFILE_ALL:
			
			req.setAttribute("statusPercent", taskDao.GoupPersentUserID(Integer.valueOf(loginDto.getId())));
			req.setAttribute("LoginDto", loginDto);
			req.getRequestDispatcher("/WEB-INF/view/profile/index.jsp").forward(req, resp);
			break;
		case UrlConstants.PROFILE_DETAILS:
			user = new User();
			user.setId(Integer.valueOf(loginDto.getId()));
			
			req.setAttribute("roles", roleDao.getAll());
			req.setAttribute("user", userDao.FindByID(user));
			req.setAttribute("tasks", taskDao.FindTaskUserID(user));
			req.setAttribute("statusPercent", taskDao.GoupPersentUserID(Integer.valueOf(loginDto.getId())));
			req.setAttribute("LoginDto", loginDto);
			req.getRequestDispatcher("/WEB-INF/view/profile/details.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int id = Integer.valueOf(req.getParameter("id"));
		
		
		switch (action) {
		case UrlConstants.PROFILE_EDIT: {
			user = new User();
			user.setFullname(fullname);
			user.setEmail(email);
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
			user.setId(id);
			int result = 0;
			result = profileDao.EditByID(user);
//			if(result == -1) {
//				req.setAttribute("Message", "Sửa thất bại!");
//				return;
//			}
			
			break;
		}
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.PROFILE_ALL);
		
	}
}
