package com.chinalife.fms.dao;

import java.sql.Connection;

import com.chinalife.fms.bean.Branch;

public class TestDao {

	public static void main(String[] args) {
		Connection con = JdbcUtil.getConnection();
		BranchDao branchDao = new BranchDao();
		try{
			Branch branch = null;
			branch = branchDao.retrieveRootBranch();
			System.out.println(branchDao.convertBranchToEasyUiTreeJson(branch, branchDao.retrieveBranches(branch)));
		} catch(Exception e){
			System.out.println("Query error:");
			e.printStackTrace();
		}
		JdbcUtil.close(null, null, con);
	}

}
