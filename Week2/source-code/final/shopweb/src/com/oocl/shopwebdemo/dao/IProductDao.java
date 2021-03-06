package com.oocl.shopwebdemo.dao;

import java.util.List;
import com.oocl.shopwebdemo.model.Product;

public interface IProductDao {
	
	int addProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(Product product);
	int addVisitTime(Product product);
	
	int getProductsSearchResultCountByKeyword(String keyword);
	List<Product> searchProductsByKeywordWithPaging(String keyword, int pageSize, int pageNum);
	List<Product> getProductInCategory(int cid,int pageSize, int pageNum);
	List<Product> getAdProduct();
	List<Product> getProductByIndex(int id);
	List<Product> getHotProduct();
	List<Product> getAdvProduct();
}
