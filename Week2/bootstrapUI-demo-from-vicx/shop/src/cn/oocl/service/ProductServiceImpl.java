package cn.oocl.service;

import java.util.*;
import cn.oocl.dao.imple.*;
import cn.oocl.model.*;

public class ProductServiceImpl {

	// Tutor: 
//	private CategoryDaoImpl cDao = new CategoryDaoImpl();
	
	private ProductDaoImpl pDao =null; 

	public ProductServiceImpl() {
		pDao = new ProductDaoImpl();
	}
	
//	public ProductServiceImpl(String type, Boolean isHot) {
//		cDao= new ProductDaoImpl();
//		cDao.save(new Product(type,isHot));
//	}
	
	public void save(Product c){
		pDao.save(c);
	}
	
	public List<Product> queryAll(){
		return pDao.getAll();
//		System.out.println(lCat);
	}
	
//	public void query(){
//		List<Product> lCat = pDao.get(1,3);
//		System.out.println(lCat);
//	}
	
	public void query(String keyword){
		List<Product> lCat = pDao.getByPage(keyword,1,3);
		System.out.println(lCat);
	}
	
	public void update(Product c){
		pDao.update(c);
	}
	
	public void delete(int id){
		pDao.delete(id);
	}
}
