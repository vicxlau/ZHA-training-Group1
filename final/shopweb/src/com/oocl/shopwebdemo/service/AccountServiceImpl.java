package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dao.*;
import com.oocl.shopwebdemo.model.*;

public class AccountServiceImpl implements IAccountService {
	private AccountDaoImpl aDao = null;
	private AddressDaoImpl addrDao = null;

	public void setaDao(AccountDaoImpl aDao) {
		this.aDao = aDao;
	}
	
	public void setAddrDao(AddressDaoImpl addrDao) {
		this.addrDao = addrDao;
	}
	@Override
	public Customer login(Account acc) {
//		public Account login(Account acc) {

		// User uResult = aDao.getValidUser(acc.getLoginId(),acc.getPw());
		// if(uResult != null){
		// acc.isLogin() = ture;
		// acc.setUser(uResult);
		// }else{
		// acc.isLogin() = false;
		// }

		// return
		// acc.validateUser(aDao.getValidUser(acc.getLoginId(),acc.getPw()));

		acc.validateUser(aDao.getValidUser(acc.getLoginId(), acc.getPw()));
		System.out.println(acc.toString());
		Customer c = new Customer(acc);
		c.setAddrList(addrDao.getAddressByUserID(acc.getUser().getId()));
		return c;
	}
}
