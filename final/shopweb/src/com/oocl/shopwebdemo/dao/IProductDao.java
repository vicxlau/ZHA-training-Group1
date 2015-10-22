package com.oocl.shopwebdemo.dao;

import java.util.List;
import com.oocl.shopwebdemo.model.Product;

public interface IProductDao {
	
	int addProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(Product product);
	int addVisitTime(Product product);
	
	int getProductsSearchResultCountByKeyword(String keyword);
	int getProductResultCountInCategory(int cid);
	int getProductResultCountByCatIdAndPrice(int cid, int lowerbound, int upperbound);
	int getProductResultCountByCatIdAndVolumn(String keyword);
	int getProductResultCountByCatIdAndVisit(String keyword);
	
	List<Product> searchProductsByKeywordWithPaging(String keyword, int pageSize, int pageNum);
	List<Product> getProductInCategory(int cid,int pageSize, int pageNum);
	List<Product> getProductByIndex(int id);
	List<Product> getHotProduct();
	List<Product> getAdvProduct();
	List<Product> getDistinctOrderedProductNames();
	List<Product> getProductByCatIdAndPrice(int cid, int lowerbound, int upperbound, int pageSize, int pageNum);
	List<Product> getProductByCatIdAndVolumn(String keyword, int pageSize, int pageNum);
	List<Product> getProductByCatIdAndVisit(String keyword, int pageSize, int pageNum);
	List<Product> getProductsByIds(List<Integer> productIdList);
	
}
