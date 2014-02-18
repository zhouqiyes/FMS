package com.chinalife.fms.dao;

import java.sql.Connection;
import java.util.List;

import com.chinalife.fms.bean.User;
import com.google.gson.Gson;

public class TestDao {

	public static void main(String[] args) {
		Connection con = JdbcUtil.getConnection();
		UserDao userDao = new UserDao();
		List<User> users = null;
		try{
			users = userDao.queryUsers();
			System.out.println(new Gson().toJson(users));
		} catch(UserDaoException e){
			System.out.println("Query error:");
			System.out.println(e.getLocalizedMessage());
		}
		JdbcUtil.close(null, null, con);
	}

}
