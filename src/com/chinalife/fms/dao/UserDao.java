package com.chinalife.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinalife.fms.bean.User;

public class UserDao {

	public User checkLogin(String loginID, String loginPassword) throws UserDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			stmt = con.prepareStatement(
					"SELECT a.name, a.phone, a.email " + 
					"FROM users a " + 
					"WHERE a.login_Id = ? AND a.login_password = ? "
					);
			stmt.setString(1, loginID);
			stmt.setString(2, loginPassword);
			rs = stmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return user;
	}
	
	public List<User> queryUsers() throws UserDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		List<User> users = new ArrayList<User>(0);
		try {
			stmt = con.prepareStatement(
					"SELECT a.name, a.phone, a.email " + 
					"FROM users a "
					);
			rs = stmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return users;
	}
}
