package com.chinalife.fms.service;

import com.chinalife.fms.bean.User;
import com.chinalife.fms.dao.UserDao;
import com.chinalife.fms.dao.UserDaoException;
import com.google.gson.Gson;

public class UserService {
	private UserDao dao = new UserDao();

	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	public String retrieveUsers() throws UserServiceException {
		String usersToJson = null;
		try {
			 usersToJson = new Gson().toJson(dao.retrieveUsers());
		} catch(UserDaoException e) {
			throw new UserServiceException(e);
		}
		return usersToJson;
	}
	
	public void updateUser(User user) throws UserServiceException {
		
	}


}
