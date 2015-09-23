package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.dto.SearchProductsResult;

public class SearchServiceImpl implements ISearchService {

	@Override
	public SearchProductsResult searchProducts(String keyword, int pageSize, int pageNum) {
		
		if (keyword == null)
			throw new IllegalArgumentException("Null keyword.");
		
		if (pageSize < 1)
			throw new IllegalArgumentException("Page Size should be larger than zero.");
		
		if (pageNum < 1)
			throw new IllegalArgumentException("Page Num should be larger than zero.");
		
		SearchProductsResult searchResults = new SearchProductsResult();
		IProductDao productDao = new ProductDaoImpl();
		
		searchResults.setPageSize(pageSize);
		searchResults.setPageNum(pageNum);
		searchResults.setTotalResultCount(productDao.getProductsSearchResultCountByKeyword(keyword));
		searchResults.setResults(productDao.searchProductsByKeywordWithPaging(keyword, pageSize, pageNum));
		
		return searchResults;
	}
}
