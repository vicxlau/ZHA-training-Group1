package cn.oocl.dao.imple;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.oocl.model.Product;
import cn.oocl.service.ProductService;

public class ProductDaoImplTest {
	
	private static ProductDaoImpl proDaoImpl;
	private static ProductService srv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proDaoImpl=new ProductDaoImpl();
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		proDaoImpl=null;
	}

	@Test
	public void testSave() {
		proDaoImpl.save(new Product("小手机", 999.00, "3.jpg", "新发布的Mi"));
	}

	@Test
	public void testQuery1() {
		srv.queryAll();
	}
	
	@Test
	public void testUpdate(){
		Product product = new Product(1,"锤子手机", 3888.00, "1.jpg", "新发布的老罗手机");
		proDaoImpl.update(product);
	}
	
	
	
	
	@Test
	public void testQuery() {
		List<Product> productList = proDaoImpl.query("", 1, 3);
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testGet() {
		System.out.println(proDaoImpl.getById(2));
	}
	
	
	
	
	
	
	
	
}
