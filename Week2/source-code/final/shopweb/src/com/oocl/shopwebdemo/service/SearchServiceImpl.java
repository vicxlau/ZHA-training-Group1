package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.Product;

public class SearchServiceImpl implements ISearchService {

	private IProductDao productDao = new ProductDaoImpl();
	
	@Override
	public SearchProductsResult searchProducts(String keyword, int pageSize, int pageNum) {
		
		if (keyword == null)
			throw new IllegalArgumentException("Null keyword.");
		
		if (pageSize < 1)
			throw new IllegalArgumentException("Page Size should be larger than zero.");
		
		if (pageNum < 1)
			throw new IllegalArgumentException("Page Num should be larger than zero.");
		
		SearchProductsResult searchResults = new SearchProductsResult();
		
		int totalResultCount = productDao.getProductsSearchResultCountByKeyword(keyword);
		
		searchResults.setPageSize(pageSize);
		searchResults.setPageCount(totalResultCount/pageSize + 1);
		searchResults.setPageNum(pageNum);
		searchResults.setTotalResultCount(totalResultCount);
		searchResults.setPageResults(productDao.searchProductsByKeywordWithPaging(keyword, pageSize, pageNum));
		
		return searchResults;
	}

	@Override
	public List<Product> getHotProduct() {
		return productDao.getHotProduct();
		
	}
	
	@Override
	public List<Product> getAdvProduct() {
		return productDao.getAdvProduct();
	}

	@Override
	public List<Product> getProductInCategory(int cid,int pageNum) {
		return productDao.getProductInCategory(cid, 3, pageNum);
	}

	@Override
	public Product getProductByIndex(int pid) {
		return productDao.getProductByIndex(pid).get(0);
	}
	
}
