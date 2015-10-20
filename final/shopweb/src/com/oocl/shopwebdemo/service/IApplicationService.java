package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.model.ApplicationStat;
import com.oocl.shopwebdemo.model.CateProductStat;
import com.oocl.shopwebdemo.model.Customer;
import com.oocl.shopwebdemo.model.DataStorage;
import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.model.ProductCurrentVisiter;

public interface IApplicationService {
	DataStorage refreshHomeData();
	ApplicationStat updateStat(ApplicationStat stat, Product p, Customer u);
	int addCurrentVisitor(ProductCurrentVisiter stat, Product p);
	void removeCurrentVisitor(ProductCurrentVisiter stat, int prod_id);
	int getCurrentVisitorCount(ProductCurrentVisiter stat, int prod_id);
	int getProductVisitCount(ApplicationStat stat, int prod_id);
}