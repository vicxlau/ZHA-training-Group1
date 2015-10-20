package com.oocl.shopwebdemo.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.Category;
import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.service.CategoryServiceImpl;
import com.oocl.shopwebdemo.service.SearchServiceImpl;
import com.oocl.shopwebdemo.util.ConnUtil;

public class ProductDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetProductByIndex_SP() {
		List<Product> results = new ProductDaoImpl().getProductByIndex(19);
		System.out.println("");
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
	
	@Test
	public void testAddProduct() {
		System.out.println(new ProductDaoImpl().addProduct(new Product("hhest3",99.9,"D:\\testing.png","testing",3333.00,0,1)));
		

//		List<Category> allCata = new CategoryServiceImpl().getAllCategory();
//		List<Product> hotPro = new SearchServiceImpl().getHotProduct();
//		List<Product> advPro = new SearchServiceImpl().getAdvProduct();
//		System.out.println();
	}

	
	@Test
	public void testUpdateProduct() {
		// pls ensure DB has product with the corresponding id
		int id = 2;
		new ProductDaoImpl().updateProduct(new Product(id,"Test",99.9,"E:\\testing.png","testing",1));
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
	
	
	@Test
	public void testGetDistinctOrderedProductNames() {
		
		List<Product> results = new ProductDaoImpl().getDistinctOrderedProductNames();
		
		for (Product product : results) {
			System.out.println(product.getName());
		}
	}


	@Test
	public void testGetProductsByIds() {
		
		List<Integer> id_list = new ArrayList<Integer>();
		id_list.add(19);
		
		List<Product> results = new ProductDaoImpl().getProductsByIds(id_list);
		
		for (Product product : results) {
			System.out.println(product.getName());
		}
	}
}
