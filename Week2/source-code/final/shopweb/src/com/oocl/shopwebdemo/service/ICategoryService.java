package com.oocl.shopwebdemo.service;

import java.util.*;

import com.oocl.shopwebdemo.model.Category;
import com.oocl.shopwebdemo.model.Product;

public interface ICategoryService {

	List<Category> getAllCategory();

	List<Category> getCategoryByIndex(int cid);

}
