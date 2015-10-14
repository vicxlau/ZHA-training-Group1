package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.dao.CategoryDaoImpl;
import com.oocl.shopwebdemo.dao.ICategoryDao;
import com.oocl.shopwebdemo.model.Category;
import com.oocl.shopwebdemo.model.Product;

public class CategoryServiceImpl implements ICategoryService {
//vicx
//	private ICategoryDao cDao = new CategoryDaoImpl();
	private final int ADD = 1; 
	private final int UPDATE= 2; 
	private CategoryDaoImpl cDao = new CategoryDaoImpl();

	private void validateCategoryInfo(Category category, int action) {
		
		if (category == null)
			throw new IllegalArgumentException("Null category.");
		
		if (category.getType()== null)
			throw new IllegalArgumentException("Null category type.");
		
		if (category.getType().equals(""))
			throw new IllegalArgumentException("Empty category type.");
		
		if (category.getHot() == null)
			throw new IllegalArgumentException("Null category hot.");

		if(action==UPDATE)
			if(category.getId() == null)
				throw new IllegalArgumentException("Null category ID for update.");
				
	}
	
	@Override
	public List<Category> getAllCategory(){
		return cDao.getAll();
	}

	@Override
	public Category getCategoryByIndex(int cid){
		return cDao.getCategoryByIndex(cid).get(0);
	}

	@Override
	public List<Category> getCategoryByKeyword(String keyword,int pageSize, int pageNum) {
		return cDao.getCategoryByKeyword(keyword,pageSize,pageNum);
	}

	@Override
	public void addCategory(Category category) {
		validateCategoryInfo(category,ADD);
		cDao.addCategory(category);
	}
	
	@Override
	public void updateCategory(Category category) {
		validateCategoryInfo(category,UPDATE);
		cDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(int id) {
		cDao.deleteCategory(id);
	}
}
