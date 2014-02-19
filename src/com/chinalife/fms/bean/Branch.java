package com.chinalife.fms.bean;

public class Branch {
	private int branchId;
	private String name;
	private int superiorBranchId;
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSuperiorBranchId() {
		return superiorBranchId;
	}
	public void setSuperiorBranchId(int superiorBranchId) {
		this.superiorBranchId = superiorBranchId;
	}
	public String toEasyUiTreeJson(){
		return "\"id\":"+branchId+",\"text\":\""+name+"\"";
	}
	
}
