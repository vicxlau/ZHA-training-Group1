package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.Product;

public interface ISearchService {

	SearchProductsResult searchProducts(String keyword, int pageSize, int pageNum);
	SearchProductsResult getProductByCatIdAndPrice(int cid, int lowerbound, int upperbound, int pageNum);
	
	SearchProductsResult getProductByCatIdAndVolumn(int cid, int pageNum);
	SearchProductsResult getProductByCatIdAndVisit(int cid, int pageNum);

	List<Product> getHotProduct();

	List<Product> getAdvProduct();

//	List<Product> getProductInCategory(int cid, int pageNum);
	SearchProductsResult getProductInCategory(int cid, int pageNum);

	Product getProductByIndex(int pid);
	
	}
