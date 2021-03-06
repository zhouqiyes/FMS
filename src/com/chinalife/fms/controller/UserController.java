package com.chinalife.fms.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinalife.fms.service.UserService;
import com.chinalife.fms.service.UserServiceException;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService service = new UserService();
	
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuffer reqURL = req.getRequestURL();
		String action = reqURL.substring(reqURL.lastIndexOf("/") + 1);
		String resultJson = null;
		switch (action) {
			case "list":
				try {
					resultJson = service.retrieveUsers();
				} catch (UserServiceException e) {
					e.printStackTrace();
				}	
				break;
		}
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(resultJson);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuffer reqURL = req.getRequestURL();
		String action = reqURL.substring(reqURL.lastIndexOf("/") + 1);
		switch (action) {
		case "update":
			System.out.println(req.getParameter("branch"));
			Enumeration<String> parameterNames = req.getParameterNames();
			while(parameterNames.hasMoreElements()){
				System.out.println(parameterNames.nextElement());
			}
			break;
	}
	resp.setCharacterEncoding("UTF-8");
		
	}

}
