package cn.oocl.dao.imple;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.oocl.model.Category;
import cn.oocl.model.Product;

public class ProductDaoImplTest {
	
	private static ProductDaoImpl proDaoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proDaoImpl=new ProductDaoImpl();
	}	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		proDaoImpl=null;
	}

	@Test
	public void testGet() {
		//WORK
		Product c = proDaoImpl.getByIndex(1);
		System.out.println(c);
	}

	@Test
	public void testGetByPage() {
//		List<Product> lCat = proDaoImpl.getByPage("手",1,3);
		List<Product> lCat = proDaoImpl.getByPage("手",1,3);
		System.out.println(lCat);
	}
	
	@Test
	public void testSave() {
		//WORK
		proDaoImpl.save(new Product("坚果手机", 5888.00, "1.jpg", "新发布的"));
	}

	@Test
	public void testUpdate(){
		//work : SP dun use NVARCHAR
		Product product = new Product(5,"!!!锤子手机", 3888.00, "1.jpg", "新发布的老罗手机");
		System.out.println(proDaoImpl.update(product));
	}
	
	@Test
	public void testDelete(){
		//work
		System.out.println(proDaoImpl.delete(7));
	}
	
	
	
	
	
	
	
	
	
}
