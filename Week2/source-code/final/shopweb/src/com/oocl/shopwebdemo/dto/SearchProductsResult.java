package com.oocl.shopwebdemo.dto;

import java.util.List;

import com.oocl.shopwebdemo.model.Product;

public class SearchProductsResult {
	private int totalResultCount;
	private int pageCount;
	private int pageSize;
	private int pageNum;
	private List<Product> pageResults;


	public int getTotalResultCount() { return totalResultCount; }
	public int getPageCount() { return pageCount; }
	public int getPageSize() { return pageSize; }
	public int getPageNum() { return pageNum; }
	public List<Product> getPageResults() { return pageResults; }


	public void setTotalResultCount(int totalResultCount) {
		this.totalResultCount = totalResultCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageResults(List<Product> pageResults) {
		this.pageResults = pageResults;
	}
}
