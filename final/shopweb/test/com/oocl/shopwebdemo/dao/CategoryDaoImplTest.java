package com.oocl.shopwebdemo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.util.ConnUtil;

public class CategoryDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetAll_SP() {
		List<Category> results = new CategoryDaoImpl().getAll();
		System.out.println("");
		for (Category c : results) {
			System.out.println(String.format("Id: %s, type: %s, hot: %s ",
						c.getId(),
						c.getType(),
						c.getHot()
					));
		}
	}
	
	@Test
	public void testAddCategory() {
		System.out.println(new CategoryDaoImpl().addCategory(new Category("testType1",true)));
	}

	@Test
	public void testUpdateCategory() {
		System.out.println(new CategoryDaoImpl().updateCategory(new Category(11,"testType11",false)));
	}

	@Test
	public void testGetCategorysSearchByKeyword() {
		// 2nd page work?
		List<Category> results = new CategoryDaoImpl().getCategoryByKeyword("", 6,1);
		for (Category c : results) {
			System.out.println(String.format("Id: %s, type: %s, hot: %s ",
						c.getId(),
						c.getType(),
						c.getHot()
					));
		}
	}
//	@Test
//	public void testDeleteCategory() {
//		// pls ensure DB has product with the corresponding id
//		int id = 2;
//		new CategoryDaoImpl().deleteCategory(new Category(id));
//	}
//
//	
//	@Test
//	public void testSearchCategorysByKeywordWithPaging() {
//		
//		List<Category> results = new CategoryDaoImpl().searchCategorysByKeywordWithPaging("es", 3, 2);
//		
//		for (Category product : results) {
//			System.out.println(String.format("Id: %s, Name: %s, Price: %s, Pic: %s, Remarks: %s",
//						product.getId(),
//						product.getName(),
//						product.getPrice(),
//						product.getPic(),
//						product.getRemark()
//					));
//		}
//	}
}
