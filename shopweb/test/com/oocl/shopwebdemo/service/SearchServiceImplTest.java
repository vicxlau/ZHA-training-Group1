package com.oocl.shopwebdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.Product;

public class SearchServiceImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSearchProducts() {

		String keyword = "es";
		int pageSize = 3;
		int pageNum = 2;
		
		SearchProductsResult searchResults = new SearchServiceImpl().searchProducts(keyword, pageSize, pageNum);
		
		assertEquals(searchResults.getPageSize(), pageSize);
		assertEquals(searchResults.getPageNum(), pageNum);
		
		System.out.println("Total: " + searchResults.getTotalResultCount());
		System.out.println("Current Page: " + searchResults.getPageNum());
		
		for (Product product : searchResults.getResults()) {
			System.out.println(String.format("Id: %s, Name: %s, Price: %s, Pic: %s, Remarks: %s",
						product.getId(),
						product.getName(),
						product.getPrice(),
						product.getPic(),
						product.getRemark()
					));
		}
	}
}
