package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.model.*;

public class ApplicationServiceImpl implements IApplicationService {
	private ICategoryService categoryService=new CategoryServiceImpl();
	private ISearchService searchService=new SearchServiceImpl();
	@Override
	public DataStorage refreshHomeData() {
			return new DataStorage(categoryService.getAllCategory(),
		searchService.getHotProduct(),
		searchService.getAdvProduct());
	}
}
