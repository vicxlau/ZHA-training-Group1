package cn.oocl.service.imple;

import java.util.List;

import cn.oocl.dao.imple.CategoryDaoImpl;
import cn.oocl.model.Category;

public class CategoryServiceImpl {

	//Service call Dao
	
	private CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
	
	public int save(Category category){
		//business logic
		return categoryDaoImpl.save(category);
	}
	
	public int update(Category category){
		//business logic
		return categoryDaoImpl.update(category);
	}
	
	public int delete(int id){
		//business logic
		return categoryDaoImpl.delete(id);
	}
	
	public Category getById(int id){
		//business logic
		return categoryDaoImpl.getById(id);
	}
	
	public List<Category> query (String keyword, int page, int size){
		return categoryDaoImpl.query(keyword, page, size);
	}
	
	public List<Category> queryAll (){
		return categoryDaoImpl.query("", 1, 100);
	}
}
