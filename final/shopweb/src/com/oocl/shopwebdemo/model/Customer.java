package com.oocl.shopwebdemo.model;

import java.util.List;


public class Customer {

	private Account user;
	private List<Address> addrList;
//	private 
	
	public Customer(Account a) {
		setUser(a);
	}
	
	public Account getUser() {
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
}