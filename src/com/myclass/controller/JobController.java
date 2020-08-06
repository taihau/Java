package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.JobDao;
import com.myclass.dao.RoleDao;
import com.myclass.dao.StatusDao;
import com.myclass.dao.TaskDao;
import com.myclass.dto.LoginDto;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.util.UrlConstants;

@WebServlet(urlPatterns = { UrlConstants.JOB_ALL, UrlConstants.JOB_ADD, UrlConstants.JOB_EDIT, UrlConstants.JOB_DETAILS,
		UrlConstants.JOB_DELETE })
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Job> listJob = null;
	JobDao jobDao = null;
	TaskDao taskDao = null;
	StatusDao statusDao = null;
	RoleDao roleDao = null;

	@Override
	public void init() throws ServletException {
		super.init();
		jobDao = new JobDao();
		listJob = new ArrayList<Job>();
		taskDao = new TaskDao();
		statusDao = new StatusDao();
		roleDao = new RoleDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			return;
		}
		LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN");
		String roleName = loginDto.getRolename();
		String action = req.getServletPath();
		if ((action.startsWith(UrlConstants.JOB_ADD) || action.startsWith(UrlConstants.JOB_DELETE) || action.startsWith(UrlConstants.JOB_EDIT) ) && roleName.equals("ROLE_USER")) {
			resp.sendRedirect(req.getContextPath() + "/error/forbidden");
			return;
		}
		switch (action) {
		case UrlConstants.JOB_ALL:
			req.setAttribute("roleName", roleName);
			req.setAttribute("job", jobDao.getAll());
			req.getRequestDispatcher("/WEB-INF/view/job/index.jsp").forward(req, resp);
			break;
		case UrlConstants.JOB_ADD:
			req.getRequestDispatcher("/WEB-INF/view/job/add.jsp").forward(req, resp);
			break;
		case UrlConstants.JOB_EDIT:
			req.setAttribute("job", jobDao.FindById(Integer.valueOf(req.getParameter("id"))));
			req.getRequestDispatcher("/WEB-INF/view/job/edit.jsp").forward(req, resp);
			break;
		case UrlConstants.JOB_DETAILS:
			int jobID = Integer.valueOf(req.getParameter("id"));
			List<User> listUser = new ArrayList<User>();
			listUser = taskDao.FindUserID(jobID);
			req.setAttribute("JoinUsers", listUser);
			req.setAttribute("findJob", taskDao.FindJoinJobID(jobID));
			req.setAttribute("roles", roleDao.getAll());
			req.setAttribute("statusPercent", taskDao.FindPercent(jobID));
			req.getRequestDispatcher("/WEB-INF/view/job/details.jsp").forward(req, resp);
			break;
			
		case UrlConstants.JOB_DELETE:
			jobDao.DeleteId(Integer.valueOf(req.getParameter("id")));			
			resp.sendRedirect(req.getContextPath() + UrlConstants.JOB_ALL);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN");
		String roleName = loginDto.getRolename();
		
		String action = req.getServletPath();
		String name = req.getParameter("name");
		
		Date startDate = Date.valueOf(req.getParameter("startDate"));
		Date endDate = Date.valueOf(req.getParameter("endDate"));
		Job job = new Job();
		job.setName(name);
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		if ((action.startsWith(UrlConstants.JOB_ADD)|| action.startsWith(UrlConstants.JOB_EDIT)) && roleName.equals("ROLE_USER")) {
			resp.sendRedirect(req.getContextPath() + "/error/forbidden");
			return;
		}
		switch (action) {
		case UrlConstants.JOB_ADD:
			jobDao.NewAdd(job);
			break;
		case UrlConstants.JOB_EDIT:
			job.setId(Integer.valueOf(req.getParameter("id")));
			jobDao.EditById(job);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.JOB_ALL);
	}
}
