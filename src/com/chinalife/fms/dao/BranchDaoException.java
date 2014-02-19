package com.chinalife.fms.dao;

import java.sql.SQLException;

public class BranchDaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BranchDaoException(SQLException e){
		super(e);
	}
	
}
