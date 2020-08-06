package com.myclass.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.dao.RoleDao;
import com.myclass.dao.TaskDao;
import com.myclass.dao.UserDao;
import com.myclass.util.UrlConstants;


@WebServlet (urlPatterns = {UrlConstants.USER_ALL, UrlConstants.USER_ADD, UrlConstants.USER_EDIT, UrlConstants.USER_DETAILS, 
		UrlConstants.USER_DELETE})
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = null;
	private RoleDao roleDao = null;
	private TaskDao taskDao = null;
	User user = null;
	private GenericServlet pageContext;
	
	@Override
	public void init() throws ServletException {
		userDao = new UserDao();
		roleDao = new RoleDao();
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
		case UrlConstants.USER_ALL:
			req.setAttribute("users", userDao.FindAllUserRole());
			req.getRequestDispatcher("/WEB-INF/view/user/index.jsp").forward(req, resp);
			break;
		case UrlConstants.USER_ADD:
			req.setAttribute("roles", roleDao.getAll());
			req.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(req, resp);
			break;
		case UrlConstants.USER_DETAILS:{
			user = new User();
			user.setId(Integer.valueOf(req.getParameter("id")));
			req.setAttribute("roles", roleDao.getAll());
			req.setAttribute("user", userDao.FindByID(user));
			req.setAttribute("tasks", taskDao.FindTaskUserID(user));
			req.setAttribute("statusPercent", taskDao.GoupPersentUserID(user.getId()));
			req.getRequestDispatcher("/WEB-INF/view/user/details.jsp").forward(req, resp);
			break;
		}
		case UrlConstants.USER_EDIT:
			user = new User();
			user.setId(Integer.valueOf(req.getParameter("id")));
			req.setAttribute("roles", roleDao.getAll());
			req.setAttribute("user", userDao.FindByID(user));
			req.getRequestDispatcher("/WEB-INF/view/user/edit.jsp").forward(req, resp);
			break;
		case UrlConstants.USER_DELETE:{
			int id = Integer.valueOf(req.getParameter("id"));
			user = new User();
			user.setId(id);
			user = userDao.FindByID(user);
			File myObj = new File(UrlConstants.SAVE_IMAGE +"\\"+ user.getAvatar()); 
	        myObj.delete();
			userDao.DeleteById(id);
			resp.sendRedirect(req.getContextPath() + UrlConstants.USER_ALL);
			break;
		}
		default:
			break;
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		User user = new User();
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		int roleID = Integer.valueOf(req.getParameter("roleID"));
		
		user.setFullname(fullname);
		user.setEmail(email);
		user.setRoleID(roleID);
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
		
		int result = -1;
		switch (action) {
		case UrlConstants.USER_ADD: {
		    Part filePart = req.getPart("avatar"); // Retrieves <input type="file" name="file">
		    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    int index = originalFileName.lastIndexOf(".");
			String ext = originalFileName.substring(index + 1);
			String fileName = System.currentTimeMillis() + "." + ext;
		    
		    InputStream fileContent = filePart.getInputStream();
		    String appPath = req.getServletContext().getRealPath("");
		    
		    appPath = UrlConstants.SAVE_IMAGE;
		    String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + "/" + fileName;
            } else {
                fullSavePath = appPath + "/"+ fileName;
            }
	          
	        fullSavePath = fullSavePath.replace('/','\\');
	        filePart.write(fullSavePath);   
	        user.setAvatar(fileName);
	        userDao.NewAdd(user);
			break;
		}
		case UrlConstants.USER_EDIT: {
			String fileName = null, originalFileName = null;
			user.setId(Integer.valueOf(req.getParameter("id")));
			String oldAvatar = req.getParameter("oldAvatar");
			Part filePart = req.getPart("avatar"); // Retrieves <input type="file" name="file">
		    originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    if (originalFileName.length() > 0) {
		    	int index = originalFileName.lastIndexOf(".");
				String ext = originalFileName.substring(index + 1);
				fileName = System.currentTimeMillis() + "." + ext;
			}
		    
		    InputStream fileContent = filePart.getInputStream();
		    String appPath = req.getServletContext().getRealPath("");
		    
		    appPath = UrlConstants.SAVE_IMAGE;
		    String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + "/" + fileName;
            } else {
                fullSavePath = appPath + "/"+ fileName;
            }
	        
	        if (originalFileName.length() > 0) {
	        	fullSavePath = fullSavePath.replace('/','\\');
		        filePart.write(fullSavePath);
	        	user.setAvatar(fileName);
	        	File myObj = new File(UrlConstants.SAVE_IMAGE +"\\"+ oldAvatar); 
		        myObj.delete();
			}else {
				user.setAvatar(oldAvatar);
			}
			result = userDao.EditByID(user);
			if(result == -1) {
				req.setAttribute("Message", "Sửa thất bại!");
				req.getRequestDispatcher("/WEB-INF/view/user/edit.jsp").forward(req, resp);
				return;
			}
			break;
		}
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + UrlConstants.USER_ALL);
	}
}
