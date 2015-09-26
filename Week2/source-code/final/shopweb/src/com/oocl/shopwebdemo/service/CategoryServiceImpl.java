package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.dao.CategoryDaoImpl;
import com.oocl.shopwebdemo.dao.ICategoryDao;
import com.oocl.shopwebdemo.model.Category;

public class CategoryServiceImpl implements ICategoryService {
//vicx
//	private ICategoryDao cDao = new CategoryDaoImpl();
	private CategoryDaoImpl cDao = new CategoryDaoImpl();
	
	@Override
	public List<Category> getAllCategory(){
		return cDao.getAll();
	}

	@Override
	public List<Category> getCategoryByIndex(int cid){
		return cDao.getCategoryByIndex(cid);
	}
	

}
