package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.HomeDao;
import com.myclass.dto.TaskDto;
import com.myclass.util.UrlConstants;

@WebServlet (urlPatterns = {"/welcome", "/home", "/index"})
public class HomeController extends HttpServlet {
	
	HomeDao homeDao = null;
	
	@Override
	public void init() throws ServletException {
		homeDao = new HomeDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
		
		// kiểm tra đăng nhập
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.HOME);
			return;
		}
		
		req.setAttribute("percent", homeDao.FindPercentAll());
		req.getRequestDispatcher("/WEB-INF/view/home/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
