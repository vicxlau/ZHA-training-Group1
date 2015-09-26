package com.oocl.shopwebdemo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.util.ConnUtil;

public class ProductDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddProduct() {
		new ProductDaoImpl().addProduct(new Product("hhest3",99.9,"D:\\testing.png","testing"));
	}

	@Test
	public void testUpdateProduct() {
		// pls ensure DB has product with the corresponding id
		int id = 2;
		new ProductDaoImpl().updateProduct(new Product(id,"Test",99.9,"E:\\testing.png","testing"));
	}

	@Test
	public void testDeleteProduct() {
		// pls ensure DB has product with the corresponding id
		int id = 2;
		new ProductDaoImpl().deleteProduct(new Product(id));
	}

	@Test
	public void testGetProductsSearchResultCountByKeyword() {
		
		System.out.println(new ProductDaoImpl().getProductsSearchResultCountByKeyword("es"));
	}
	
	@Test
	public void testSearchProductsByKeywordWithPaging() {
		
		List<Product> results = new ProductDaoImpl().searchProductsByKeywordWithPaging("es", 3, 2);
		
		for (Product product : results) {
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
