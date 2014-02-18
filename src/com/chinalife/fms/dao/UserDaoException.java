package com.chinalife.fms.dao;

import java.sql.SQLException;

public class UserDaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UserDaoException(SQLException e){
		super(e);
	}
	
}
