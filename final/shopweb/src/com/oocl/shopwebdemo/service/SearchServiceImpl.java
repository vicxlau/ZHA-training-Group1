package com.oocl.shopwebdemo.service;

import java.util.List;

import javax.naming.directory.SearchResult;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.util.ConfigReader;

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
//	public List<Product> getProductInCategory(int cid,int pageNum) {
	public SearchProductsResult getProductInCategory(int cid,int pageNum) {
		//config page size
		//ori
//		return productDao.getProductInCategory(cid, 3, pageNum);
		
		int pageSize = Integer.parseInt(ConfigReader.getSystemValue("pageSize"));
		int totalResultCount = productDao.getProductResultCountInCategory(cid);
		
		SearchProductsResult s = new SearchProductsResult();
		s.setPageResults(productDao.getProductInCategory(cid, pageSize, pageNum));
		s.setPageNum(pageNum);
		s.setPageSize(pageSize);
		s.setPageCount(totalResultCount/pageSize + 1);
		s.setTotalResultCount(totalResultCount);
		return s;
		
//		SearchResults ss = new SearchResult(name, obj, attrs)
	}

	@Override
	public Product getProductByIndex(int pid) {
		return productDao.getProductByIndex(pid).get(0);
	}

	@Override
	public SearchProductsResult getProductByCatIdAndPrice(int cid, int lowerbound,
			int upperbound, int pageNum) {
		int pageSize = Integer.parseInt(ConfigReader.getSystemValue("pageSize"));
		int totalResultCount = productDao.getProductResultCountByCatIdAndPrice(cid, lowerbound, upperbound);
		SearchProductsResult s = new SearchProductsResult();
		//s.setPageResults(productDao.getProductByCatIdAndPrice(cid, lowerbound, upperbound, pageSize, pageNum));
		s.setPageResults(productDao.getProductByCatIdAndPrice(cid, lowerbound, upperbound, pageSize, pageNum));
		s.setPageNum(pageNum);
		s.setPageSize(pageSize);
		s.setPageCount(totalResultCount/pageSize + 1);
		s.setTotalResultCount(totalResultCount);
		return s;
	}
	
}
