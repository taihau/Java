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
import com.myclass.dao.StatusDao;
import com.myclass.dao.TaskDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Status;
import com.myclass.entity.Task;
import com.myclass.util.UrlConstants;

@WebServlet(urlPatterns = {UrlConstants.TASK_ALL, UrlConstants.TASK_ADD, UrlConstants.TASK_EDIT, UrlConstants.TASK_DETAILS,
		UrlConstants.TASK_DELETE})
public class TaskController extends HttpServlet {
	JobDao jobDao = null;
	List<Job> listJob = null;
	UserDao userDao = null;
	StatusDao statusDao = null;
	TaskDao taskDao = null;
	Task task = null;
	TaskDto taskDto = null;
	@Override
	public void init() throws ServletException {
		listJob = new ArrayList<Job>();
		userDao = new UserDao();
		jobDao = new JobDao();
		statusDao = new StatusDao();
		taskDao = new TaskDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstants.LOGIN);
			return;
		}
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.TASK_ALL:
			req.setAttribute("tasks", taskDao.GetJoinAll());
			req.getRequestDispatcher("/WEB-INF/view/task/index.jsp").forward(req, resp);
			break;
		case UrlConstants.TASK_ADD:
			req.setAttribute("jobs", jobDao.getAll());
			req.setAttribute("users", userDao.getAll());
			req.setAttribute("status", statusDao.getAll());
			req.getRequestDispatcher("/WEB-INF/view/task/add.jsp").forward(req, resp);
			break;
		case UrlConstants.TASK_EDIT:
			task = new Task();
			task.setId(Integer.valueOf(req.getParameter("id")));
			req.setAttribute("jobs", jobDao.getAll());
			req.setAttribute("users", userDao.getAll());
			req.setAttribute("status", statusDao.getAll());
			req.setAttribute("task", taskDao.FindID(task));
			req.getRequestDispatcher("/WEB-INF/view/task/edit.jsp").forward(req, resp);
			break;
		case UrlConstants.TASK_DETAILS:
			
			req.getRequestDispatcher("/WEB-INF/view/task/details.jsp").forward(req, resp);
			break;
		case UrlConstants.TASK_DELETE:
			taskDao.DeleteById(Integer.valueOf(req.getParameter("id")));
			resp.sendRedirect(req.getContextPath() + UrlConstants.TASK_ALL);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String name = req.getParameter("name");
		Date startDate = Date.valueOf(req.getParameter("startDate"));
		Date endDate = Date.valueOf(req.getParameter("endDate"));
		int userID = Integer.valueOf(req.getParameter("userId"));
		int jobID = Integer.valueOf(req.getParameter("jobId"));
		int statusID = Integer.valueOf(req.getParameter("statusId"));
		
		task = new Task();
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setUserId(userID);
		task.setJobId(jobID);
		task.setStatusId(statusID);
			
		switch (action) {
		case UrlConstants.TASK_ADD:
			taskDao.NewAdd(task);
			break;
		case UrlConstants.TASK_EDIT:
			task.setId(Integer.valueOf(req.getParameter("id")));
			taskDao.EditById(task);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.TASK_ALL);
	}
}
