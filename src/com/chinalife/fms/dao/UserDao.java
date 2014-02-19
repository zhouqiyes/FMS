package com.chinalife.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinalife.fms.bean.Branch;
import com.chinalife.fms.bean.User;

public class UserDao {

	public User retrieveUserByLogin(String loginID, String loginPassword) throws UserDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		Branch branch = null;
		try {
			stmt = con.prepareStatement(
					"SELECT a.LoginId, a.LoginPwd, a.Name AS UserName, a.Email, " + 
							"b.BranchId, b.Name AS BranchName, b.SuperiorBranchId " + 
					"FROM SysUser a, Branch b " + 
					"WHERE a.BranchId = b.BranchId "
					+ "AND a.LoginId = ? AND a.LoginPwd = ? "
					);
			stmt.setString(1, loginID);
			stmt.setString(2, loginPassword);
			rs = stmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setLoginId(rs.getString("LoginId"));
				user.setLoginPwd(rs.getString("LoginPwd"));
				user.setName(rs.getString("UserName"));
				user.setEmail(rs.getString("Email"));
				branch = new Branch();
				branch.setBranchId(rs.getInt("BranchId"));
				branch.setName(rs.getString("BranchName"));
				branch.setSuperiorBranchId(rs.getInt("SuperiorBranchId"));
				user.setBranch(branch);
			}
			
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return user;
	}
	
	public List<User> retrieveUsers() throws UserDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		Branch branch = null;
		List<User> users = new ArrayList<User>(0);
		try {
			stmt = con.prepareStatement(
					"SELECT a.LoginId, a.LoginPwd, a.Name AS UserName, a.Email, " + 
							"b.BranchId, b.Name AS BranchName, b.SuperiorBranchId " + 
					"FROM SysUser a, Branch b " + 
					"WHERE a.BranchId = b.BranchId "
					);
			rs = stmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setLoginId(rs.getString("LoginId"));
				user.setLoginPwd(rs.getString("LoginPwd"));
				user.setName(rs.getString("UserName"));
				user.setEmail(rs.getString("Email"));
				branch = new Branch();
				branch.setBranchId(rs.getInt("BranchId"));
				branch.setName(rs.getString("BranchName"));
				branch.setSuperiorBranchId(rs.getInt("SuperiorBranchId"));
				user.setBranch(branch);
				users.add(user);
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return users;
	}
	
	public void updateUser(User user) throws UserDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE SysUser " + 
					"SET LoginPwd = ?, Name = ?, Email = ?, BranchId = ? " + 
					"WHERE Loginid = ? "
					);
			stmt.setString(1, user.getLoginPwd());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getBranch().getBranchId());
			stmt.executeBatch();
			
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			JdbcUtil.close(null, stmt, con);
		}
	}
}
