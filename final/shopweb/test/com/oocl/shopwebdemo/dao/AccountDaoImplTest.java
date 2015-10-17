package com.oocl.shopwebdemo.dao;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.AccountServiceImpl;
import com.oocl.shopwebdemo.util.ConfigReader;
import com.oocl.shopwebdemo.util.Logger;

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
	
	@Test
	public void testFileWriter() {
		Logger.log(Logger.INFO,"testing 測");
		
//		try {
//			PrintWriter out = new PrintWriter(Locale.getSystemValue("log-file-location"),"UTF-8");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try{
//			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Locale.getSystemValue("log-file-location"), true)));
//		    out.println("the text");
//		    //more code
//		    out.println("more text");
//		    //more code
//		    out.close();
//		    System.out.println("done \n ");
//		}catch (IOException e) {
//		    //exception handling left as an exercise for the reader
//			System.out.println("fail \n ");
//		}
	}
}
