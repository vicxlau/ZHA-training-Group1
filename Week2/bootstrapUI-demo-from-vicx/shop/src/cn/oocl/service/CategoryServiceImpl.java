package cn.oocl.service;

import java.util.*;
import cn.oocl.dao.imple.CategoryDaoImpl;
import cn.oocl.model.Category;

public class CategoryServiceImpl {

	// Tutor: 
//	private CategoryDaoImpl cDao = new CategoryDaoImpl();
	
	private CategoryDaoImpl cDao =null; 

	public CategoryServiceImpl() {
		cDao = new CategoryDaoImpl();
	}
	
//	public CategoryServiceImpl(String type, Boolean isHot) {
//		cDao= new CategoryDaoImpl();
//		cDao.save(new Category(type,isHot));
//	}
	
	public void save(Category c){
		cDao.save(c);
	}
	
	public List<Category> queryAll(){
		return cDao.getAll();
//		System.out.println(lCat);
	}
	
	public void query(){
		List<Category> lCat = cDao.get(1,3);
		System.out.println(lCat);
	}
	
	public void query(String keyword){
		List<Category> lCat = cDao.getByPage(keyword,1,3);
		System.out.println(lCat);
	}
	
	public void update(Category c){
		cDao.update(c);
	}
	
	public void delete(int id){
		cDao.delete(id);
	}
}
