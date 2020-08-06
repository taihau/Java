package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.LoginDto;
import com.myclass.util.UrlConstants;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Ép kiểu interface servletrequest sang httpservlerrequest để dùng hàm
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String action = req.getServletPath();
		if (action.startsWith("/login") || action.startsWith("/logout")) {
			// cho phép đi vào luon
			chain.doFilter(request, response);
			return;
		}
		
		// --------------Kiểm tra đăng nhập---------------------
		HttpSession session = req.getSession();
		LoginDto loginDto = (LoginDto) session.getAttribute("USER_LOGIN");
		// Nếu vào trang login không cân kiểm tra
		
		if (loginDto == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
//		if (session.getAttribute("USER_LOGIN") == null) {
//			resp.sendRedirect(req.getContextPath() + "/login");
//			return;
//		}
		// Kiểm tra URL
		String roleName = loginDto.getRolename();
		if (action.startsWith(UrlConstants.ROLE_ALL) && !roleName.equals("ROLE_ADMIN")) {
			resp.sendRedirect(req.getContextPath() + "/error/forbidden");
			return;
		}
		
		if (action.startsWith(UrlConstants.USER_ALL) && roleName.equals("ROLE_USER")) {
			resp.sendRedirect(req.getContextPath() + "/error/forbidden");
			return;
		}

//		if (!BCrypt.checkpw(password, user.getPassword())) {
//			req.setAttribute("Message", "Sai Thông Tin Đăng Nhập!");
//			req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
//		}

		chain.doFilter(request, response);
	}

}
