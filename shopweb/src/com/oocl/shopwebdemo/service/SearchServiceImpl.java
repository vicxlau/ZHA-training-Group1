package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.dto.SearchProductsResult;

public class SearchServiceImpl implements ISearchService {

	private IProductDao productDao = new ProductDaoImpl();
	
	@Override
	public SearchProductsResult searchProducts(String keyword, int pageSize, int pageNum) {
		
		if (keyword == null)
			throw new IllegalArgumentException("Null keyword.");
		
		if (keyword.equals(""))
			throw new IllegalArgumentException("Empty keyword.");
		
		if (pageSize < 1)
			throw new IllegalArgumentException("Page Size should be larger than zero.");
		
		if (pageNum < 1)
			throw new IllegalArgumentException("Page Num should be larger than zero.");
		
		SearchProductsResult searchResults = new SearchProductsResult();
		
		int totalResultCount = productDao.getProductsSearchResultCountByKeyword(keyword);
		
		searchResults.setPageSize(pageSize);
		searchResults.setPageCount(totalResultCount/3 + 1);
		searchResults.setPageNum(pageNum);
		searchResults.setTotalResultCount(totalResultCount);
		searchResults.setPageResults(productDao.searchProductsByKeywordWithPaging(keyword, pageSize, pageNum));
		
		return searchResults;
	}
}
