package com.chinalife.fms.service;

import com.chinalife.fms.bean.Branch;
import com.chinalife.fms.dao.BranchDao;
import com.chinalife.fms.dao.BranchDaoException;

public class BranchService {
	private BranchDao dao = new BranchDao();

	public BranchDao getDao() {
		return dao;
	}

	public void setDao(BranchDao dao) {
		this.dao = dao;
	}
	
	public String retrieveBranches() throws BranchServiceException{
		String branchesToJson = null;
		Branch branch = null;
		try {
			branch = dao.retrieveRootBranch();
			branchesToJson = "[" + 
					dao.convertBranchToEasyUiTreeJson(branch, dao.retrieveBranches(branch)) +
					"]";
		} catch (BranchDaoException e) {
			throw new BranchServiceException(e);
		}
		return branchesToJson;
	}
}
