package com.oocl.shopwebdemo.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.AccountServiceImpl;

public class AccountDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetValidUser() {

//		Account acc = new Account(name, pass);
		
		User u = new AccountDaoImpl().getValidUser("lauvi","Password1");
		System.out.println("result name " + u.getName());
	}
	
	@Test
	public void testGetUserByID() {

//		Account acc = new Account(name, pass);
		
		User u = new AccountDaoImpl().getValidUser(1);
		System.out.println("result name " + u.getName());
	}
	
	@Test
	public void testLoginService() {
		
		Account acc = new Account("lauvi@oocl.com", "Password1");
		new AccountServiceImpl().login(acc);

		System.out.println("result");
	}
}
