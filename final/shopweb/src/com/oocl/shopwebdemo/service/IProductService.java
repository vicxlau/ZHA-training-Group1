package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.model.Product;

public interface IProductService {

	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	void addProductVisitTime(int productId);
	Product getProductByIndex(int pid);
}
