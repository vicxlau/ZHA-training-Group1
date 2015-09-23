package com.oocl.shopwebdemo.dto;

import java.util.List;

import com.oocl.shopwebdemo.model.Product;

public class SearchProductsResult {
	private int totalResultCount;
	private int pageSize;
	private int pageNum;
	private List<Product> results;


	public int getTotalResultCount() { return totalResultCount; }
	public int getPageSize() { return pageSize; }
	public int getPageNum() { return pageNum; }
	public List<Product> getResults() { return results; }


	public void setTotalResultCount(int totalResultCount) {
		this.totalResultCount = totalResultCount;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setResults(List<Product> results) {
		this.results = results;
	}
}
