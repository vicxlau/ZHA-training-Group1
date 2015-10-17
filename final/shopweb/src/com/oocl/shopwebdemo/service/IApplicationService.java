package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.model.ApplicationStat;
import com.oocl.shopwebdemo.model.CateProductStat;
import com.oocl.shopwebdemo.model.Customer;
import com.oocl.shopwebdemo.model.DataStorage;
import com.oocl.shopwebdemo.model.Product;

public interface IApplicationService {
	DataStorage refreshHomeData();
	ApplicationStat updateStat(ApplicationStat stat, Product p, Customer u);
}
