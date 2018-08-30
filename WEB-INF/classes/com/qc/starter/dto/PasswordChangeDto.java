package com.qc.starter.dto;

public class PasswordChangeDto {

	private String currentpwd;
	private String newpwd;
	private String confirmnewpwd;
	private String encconfirmnewpwd;
	private String enccurrentpwd;




	public String getEnccurrentpwd() {
		return enccurrentpwd;
	}
	public void setEnccurrentpwd(String enccurrentpwd) {
		this.enccurrentpwd = enccurrentpwd;
	}
	public String getEncconfirmnewpwd() {
		return encconfirmnewpwd;
	}
	public void setEncconfirmnewpwd(String encconfirmnewpwd) {
		this.encconfirmnewpwd = encconfirmnewpwd;
	}
	public String getCurrentpwd() {
		return currentpwd;
	}
	public void setCurrentpwd(String currentpwd) {
		this.currentpwd = currentpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getConfirmnewpwd() {
		return confirmnewpwd;
	}
	public void setConfirmnewpwd(String confirmnewpwd) {
		this.confirmnewpwd = confirmnewpwd;
	}




}
