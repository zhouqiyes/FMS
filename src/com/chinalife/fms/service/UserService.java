package com.chinalife.fms.service;

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
	
	public String queryUsers() throws UserServiceException {
		String usersToJson = null;
		try {
			 usersToJson = new Gson().toJson(dao.queryUsers());
		} catch(UserDaoException e) {
			throw new UserServiceException(e);
		}
		return usersToJson;
	}


}
