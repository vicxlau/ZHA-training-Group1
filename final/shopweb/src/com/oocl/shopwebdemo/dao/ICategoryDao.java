package com.oocl.shopwebdemo.dao;

import java.util.List;

import com.oocl.shopwebdemo.model.Category;

public interface ICategoryDao {

	int addCategory(Category category);

	int updateCategory(Category category);

	List<Category> getAll();

	List<Category> getCategoryByIndex(int cid);

	List<Category> getCategoryByKeyword(String keyword, int pageSize,int pageNum);

	int deleteCategory(int id);

}
