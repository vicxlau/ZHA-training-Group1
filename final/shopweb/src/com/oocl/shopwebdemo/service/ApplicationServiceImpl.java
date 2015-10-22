package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dao.StatisticDaoImpl;
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
	
//	@Override
//	public void saveStatisticDataToDB() {
//		new StatisticDaoImpl().
//	}

	@Override
	public int addCurrentVisitor(ProductCurrentVisiter stat, Product p){
		if(p!=null){
			int prod_id = p.getId();
			if(stat!=null){
				stat.log(prod_id);
			}else{
				stat = new ProductCurrentVisiter();
			}
			return stat.addVisitor(prod_id);
		}
		return 0;
	}
	
	@Override
	public void removeCurrentVisitor(ProductCurrentVisiter stat, int prod_id){
		if(stat!=null){
			stat.log(prod_id);
		}else{
			stat = new ProductCurrentVisiter();
		}
		stat.removeVisitor(prod_id);
	}
	
	@Override
	public int getCurrentVisitorCount(ProductCurrentVisiter stat,int prod_id){
		if(stat!=null){
			stat.log(prod_id);
		}else{
			stat = new ProductCurrentVisiter();
		}
		return stat.getVisitor(prod_id);
	}
	
	@Override
	public ApplicationStat updateStat(ApplicationStat stat, Product p, Customer u){
		if(stat!=null){
			System.out.println(stat.prod_list.size());
		}else{
			stat = new ApplicationStat();
		}
		stat.update((u==null || u.getAccount()==null || u.getAccount().getUser()==null)?0:u.getAccount().getUser().getId(), p.getId(), p.getCategoryID());
		return stat;
	}
	
	@Override
	public int getProductVisitCount(ApplicationStat stat, int prod_id){
		if(stat!=null){
			System.out.println(stat.prod_list.size());
		}else{
			stat = new ApplicationStat();
		}
		return stat.getProductCount(prod_id);
	}
	
	public void saveStatToDB(){
		
	}
}
