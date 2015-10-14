package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.util.*;

public class DataStorage implements Serializable{
	private static final long serialVersionUID = 1603842454809468995L;
	private List<Category> categoryList;
	private List<Product> hotProductList;
	private List<Product> advProductList;

	public DataStorage(List<Category> cate, List<Product> hotProd, List<Product> advProd) {
		setCategoryList(cate);
		setHotProductList(hotProd);
		setAdvProductList(advProd);
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Product> getHotProductList() {
		return hotProductList;
	}
	public void setHotProductList(List<Product> hotProductList) {
		this.hotProductList = hotProductList;
	}
	public List<Product> getAdvProductList() {
		return advProductList;
	}
	public void setAdvProductList(List<Product> advProductList) {
		this.advProductList = advProductList;
	}
}
