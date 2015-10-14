package com.oocl.shopwebdemo.service;

import java.util.*;

import com.oocl.shopwebdemo.model.Category;
import com.oocl.shopwebdemo.model.Product;

public interface ICategoryService {

	List<Category> getAllCategory();

	Category getCategoryByIndex(int cid);

	void addCategory(Category category);

	void updateCategory(Category category);

	List<Category> getCategoryByKeyword(String keyword, int pageSize,int pageNum);

	void deleteCategory(int id);

}
