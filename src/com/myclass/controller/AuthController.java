package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.UserDao;
import com.myclass.dto.LoginDto;
import com.myclass.entity.User;
import com.myclass.util.UrlConstants;

@WebServlet (urlPatterns = { UrlConstants.LOGIN, UrlConstants.LOGOUT })
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = null;
	UserDao userDao = new UserDao();
	
	@Override
	public void init() throws ServletException {
		super.init();
		user = new User();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		switch (action) {
			case UrlConstants.LOGIN:
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
				break;
			case UrlConstants.LOGOUT:{
				HttpSession session = req.getSession();
				session.removeAttribute("USER_LOGIN");
				resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
				break;
			}
			default:
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = null, password = null;
		email = req.getParameter("email");
		password = req.getParameter("password");
		if (email.isEmpty() || password.isEmpty()) {
			req.setAttribute("Message", "Email và Mật Khẩu không được để trống.");
			req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
		}
		
		LoginDto user = userDao.checkLogin(email);
		
		if (!BCrypt.checkpw(password, user.getPassword())) {
			req.setAttribute("Message", "Sai Thông Tin Đăng Nhập!");
			req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
		}
		
		// B4: lưu thông tin user vào section
		HttpSession session = req.getSession();
		session.setAttribute("USER_LOGIN", user);
		
		resp.sendRedirect(req.getContextPath() + UrlConstants.HOME);
		
	}
}
