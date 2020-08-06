package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.*;
import com.myclass.database.JDBCConnection;
import com.myclass.util.UrlConstants;

import com.myclass.entity.*;

@WebServlet(urlPatterns = { UrlConstants.ROLE_ALL, UrlConstants.ROLE_ADD, UrlConstants.ROLE_EDIT, UrlConstants.ROLE_DELETE})
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Role> listRole = null;
	RoleDao roleDao = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		listRole = new ArrayList<Role>();
		roleDao = new RoleDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			return;
		}
		Role role = new Role();
		String action = req.getServletPath();
		
		switch (action) {
		case UrlConstants.ROLE_ALL:{
			req.setAttribute("roles", roleDao.getAll());
			req.getRequestDispatcher("/WEB-INF/view/role/index.jsp").forward(req, resp);
			break;
		}			
		case UrlConstants.ROLE_ADD:{
			req.getRequestDispatcher("/WEB-INF/view/role/add.jsp").forward(req, resp);
			break;
		}
		case UrlConstants.ROLE_EDIT:{
			int id = Integer.valueOf(req.getParameter("id"));
			req.setAttribute("role", roleDao.FindById(id));
			req.getRequestDispatcher("/WEB-INF/view/role/edit.jsp").forward(req, resp);
			break;
		}
		case UrlConstants.ROLE_DELETE:{
			roleDao.DeleteId(Integer.valueOf(req.getParameter("id")));
			resp.sendRedirect(req.getContextPath() + UrlConstants.ROLE_ALL);
			break;
		}
			
		default:
			break;
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		
		switch (action) {
		case UrlConstants.ROLE_ADD: {
			roleDao.NewAdd(role);
			break;
		}
		case UrlConstants.ROLE_EDIT:{
			role.setId(Integer.valueOf(req.getParameter("id")));
			role.setName(name);
			role.setDescription(description);
			roleDao.EditById(role);
			break;
		}
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.ROLE_ALL);
		
	}
}
