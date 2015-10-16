package com.oocl.shopwebdemo.model;


public class Account {

	private User user;
	private String login_id;
	private String pw;
	private boolean isLogin;
	
	public Account() {}

	public Account(String login_id, String pw) {
		setLoginId(login_id);
		setPw(pw);
	}

	public void validateUser(User validUser) {
//		public Account validateUser(User validUser) {
		user = validUser;
		isLogin = isValidUser();
	}
	
	public boolean isValidUser() {
		return user!=null;
	}
	
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLoginId() {
		return login_id;
	}

	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	@Override
	public String toString(){
		return String.format("user_id: %d \n "
				+ "user_name: %s \n "
				+ "login_id: %s \n"
				+ "login_pw: %s \n"
				+ "role_id: %d \n", user.getId(),user.getName(),login_id,pw,user.getRoleId());
	}
}