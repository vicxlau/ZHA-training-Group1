package com.oocl.shopwebdemo.model;

import java.util.List;


public class Customer {

	private static final String EN = "en";
	private static final String ZH_CN = "zhcn";
	private Account user;
	private List<Address> addrList;
	private String locale;
//	private 
	
	public Customer() {
		setLocale(ZH_CN);
	}
	
	public Customer(Account a) {
		setUser(a);
		setLocale(ZH_CN);
	}
	
	public Account getAccount() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}

	public List<Address> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<Address> addrList) {
		this.addrList = addrList;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}