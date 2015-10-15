package cn.oocl.dao.imple;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.oocl.model.Category;

public class CategoryDaoImplTest {
	
	private static CategoryDaoImpl categoryDaoImpl=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDaoImpl=new CategoryDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDaoImpl=null;
	}

	@Test
	public void testSave() {
		categoryDaoImpl.save(new Category("耐克",true));
	}
	
	@Test
	public void testGet() {
		System.out.println(categoryDaoImpl.getById(8).toString());
	}
	
	@Test
	public void testDelete(){
		categoryDaoImpl.delete(10);
	}
	
	
	
	
	
	
	
	
	
	
	

}
