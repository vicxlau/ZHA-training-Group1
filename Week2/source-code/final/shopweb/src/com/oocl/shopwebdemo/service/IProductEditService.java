package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.model.Product;

public interface IProductEditService {

	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	void addProductVisitTime(int productId);
}
