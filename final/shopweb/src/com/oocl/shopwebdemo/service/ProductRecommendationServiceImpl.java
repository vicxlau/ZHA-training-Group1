package com.oocl.shopwebdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oocl.shopwebdemo.model.Product;

public class ProductRecommendationServiceImpl implements IProductRecommendationService {

	private static final Map<Integer, Map<Product, Integer>> commonRecentItems = new HashMap<Integer, Map<Product, Integer>>();
	
	@Override
	public List<Product> recommendProducts(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
