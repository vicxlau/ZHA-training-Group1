package cn.oocl.dao.imple;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.oocl.model.Category;

public class CategoryDaoImplTest {

	private static CategoryDaoImpl categoryDaoImpl = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDaoImpl = new CategoryDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDaoImpl = null;
	}

	@Test
	public void testGetAll() {
		List<Category> lCat = categoryDaoImpl.getAll();
		System.out.println(lCat);
	}
	
	@Test
	public void testGet() {
		List<Category> lCat = categoryDaoImpl.get(1, 3);
		System.out.println(lCat);
	}

	@Test
	public void testGetByIndex() {
		// Work
		Category c = categoryDaoImpl.getByIndex(6);
		System.out.println(c);
	}

	@Test
	public void testGetByPage() {
		List<Category> lCat = categoryDaoImpl.getByPage("2", 1, 3);
		System.out.println(lCat);
	}

	@Test
	public void testSave() {
		// Work
		categoryDaoImpl.save(new Category("家用电器!", true));
	}

	@Test
	public void testUpdate() {
		// Work
		categoryDaoImpl.update(new Category(11, "家用电器2", true));
	}

	@Test
	public void testDelete() {
		// Work
		categoryDaoImpl.delete(7);
	}

}
