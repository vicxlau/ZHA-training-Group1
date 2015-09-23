package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dto.SearchProductsResult;

public interface ISearchService {

	SearchProductsResult searchProducts(String keyword, int pageSize, int pageNum);
}
