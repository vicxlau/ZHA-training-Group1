package com.oocl.shopwebdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductServiceImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetDistinctProductNames() {

		List<String> productNames = new ProductServiceImpl().getDistinctProductNames();
		
		for (String name : productNames) {
			System.out.println(name);
		}
	}

}
