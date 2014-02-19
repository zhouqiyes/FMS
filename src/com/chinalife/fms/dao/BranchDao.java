package com.chinalife.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinalife.fms.bean.Branch;

public class BranchDao {

	public List<Branch> retrieveBranches() throws BranchDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Branch branch = null;
		List<Branch> branches = new ArrayList<Branch>(0);
		try {
			stmt = con.prepareStatement(
					"SELECT BranchId, Name, SuperiorBranchId " + 
					"FROM Branch "
					);
			rs = stmt.executeQuery();
			while(rs.next()){
				branch = new Branch();
				branch.setBranchId(rs.getInt("BranchId"));
				branch.setName(rs.getString("Name"));
				branch.setSuperiorBranchId(rs.getInt("SuperiorBranchId"));
				branches.add(branch);
			}
		} catch (SQLException e) {
			throw new BranchDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return branches;
	}
	
	public List<Branch> retrieveBranches(Branch superior) throws BranchDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Branch branch = null;
		List<Branch> branches = new ArrayList<Branch>(0);
		try {
			stmt = con.prepareStatement(
					"SELECT BranchId, Name, SuperiorBranchId " + 
					"FROM Branch " + 
					"WHERE SuperiorBranchId = ? "
					);
			stmt.setInt(1, superior.getBranchId());
			rs = stmt.executeQuery();
			while(rs.next()){
				branch = new Branch();
				branch.setBranchId(rs.getInt("BranchId"));
				branch.setName(rs.getString("Name"));
				branch.setSuperiorBranchId(rs.getInt("SuperiorBranchId"));
				branches.add(branch);
			}
		} catch (SQLException e) {
			throw new BranchDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return branches;
	}
	
	public Branch retrieveRootBranch() throws BranchDaoException {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Branch branch = null;
		try {
			stmt = con.prepareStatement(
					"SELECT BranchId, Name, SuperiorBranchId " + 
					"FROM Branch " + 
					"WHERE SuperiorBranchId = 0 "
					);
			rs = stmt.executeQuery();
			while(rs.next()){
				branch = new Branch();
				branch.setBranchId(rs.getInt("BranchId"));
				branch.setName(rs.getString("Name"));
				branch.setSuperiorBranchId(rs.getInt("SuperiorBranchId"));
			}
		} catch (SQLException e) {
			throw new BranchDaoException(e);
		} finally {
			JdbcUtil.close(rs, stmt, con);
		}
		return branch;
	}
	
	public String convertBranchToEasyUiTreeJson(Branch branch, List<Branch> branches) throws BranchDaoException {
		StringBuffer treeJson = new StringBuffer("{" + branch.toEasyUiTreeJson() + ",\"children\":[");
		for(int i = 0; i <= branches.size()-1; i++){
			treeJson.append(this.convertBranchToEasyUiTreeJson(branches.get(i), this.retrieveBranches(branches.get(i))));
			if(i < branches.size()-1) treeJson.append(",");
		}
		return treeJson + "]}";
	}
}
