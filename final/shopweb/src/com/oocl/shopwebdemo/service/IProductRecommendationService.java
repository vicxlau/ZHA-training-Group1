package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.model.Product;

public interface IProductRecommendationService {

	List<Product> recommendProducts(int productId);
	void addProductBrowsePairs(Product lastBrowsedProduct, Product currentBrowsedProduct);
}
