package com.oocl.shopwebdemo.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -5476405291900933919L;
	private Integer id;
	private String login_id;
//	private String pw;
	private String name;
	private Integer role_id;
	
	public User() {}

	public User(String login_id) {
		setLoginId(login_id);
	}
	
//	public User(String login_id, String pw) {
//		setLoginId(login_id);
//		setPw(pw);
//	}
	
	public User(Integer id, String name, Integer rid) {
		setId(id);
		setName(name);
		setRoleId(rid);
	}
	
	public Integer getId() { return id; }
	public String getName() { return name; }
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getRoleId() {
		return role_id;
	}

	public void setRoleId(Integer role_id) {
		this.role_id = role_id;
	}

	public String getLoginId() {
		return login_id;
	}

	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}

//	public String getPw() {
//		return pw;
//	}
//
//	public void setPw(String pw) {
//		this.pw = pw;
//	}
}