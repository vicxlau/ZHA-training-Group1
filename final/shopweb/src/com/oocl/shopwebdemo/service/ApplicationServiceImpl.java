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

	@Override
	public ApplicationStat updateStat(ApplicationStat stat, Product p, Customer u){
		if(stat!=null){
			System.out.println(stat.prod_list.size());
		}else{
			stat = new ApplicationStat();
		}
		stat.update((u==null)?0:u.getUser().getUser().getId(), p.getId(), p.getCategoryID());
		return stat;
	}
	
	public void saveStatToDB(){
		
	}
}
