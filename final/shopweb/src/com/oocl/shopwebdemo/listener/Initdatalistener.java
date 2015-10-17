package com.oocl.shopwebdemo.listener;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oocl.shopwebdemo.service.ProductServiceImpl;
import com.oocl.shopwebdemo.model.ApplicationStat;
import com.oocl.shopwebdemo.model.CateProductStat;
import com.oocl.shopwebdemo.util.*;

public class Initdatalistener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		TimerTaskUtil task=new TimerTaskUtil();
		task.setApplication(event.getServletContext());
		new Timer(true).schedule(task, 0, Integer.parseInt(ConfigReader.getSystemValue("listener-homePageDataRefreshTime")));
		
		String basePath = event.getServletContext().getRealPath("");
		System.out.println("获取项目的绝对地址:" + basePath);
		
		// 项目启动的时候获取银行的图标
		String[] list = new File(basePath + "/images/bank/").list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".gif");
			}
		});
		
		// 把银行的图标存放application内置对象中
		event.getServletContext().setAttribute("banks", list);
		event.getServletContext().setAttribute(ConfigReader.getSystemValue("application_stat"), new ApplicationStat());

		System.out.println(ConfigReader.getSystemValue("application_stat"));
		System.out.println("app stat: " + ((ApplicationStat)event.getServletContext().getAttribute("stat")).prod_list.size());
//				event.getServletContext().getAttribute(Locale.getSystemValue("application_stat")));

		
		
		// cache distinct product names for search box suggestion
		List<String> productNames = new ProductServiceImpl().getDistinctProductNames();
		Map<String, List<String>> productNamesSuggestionMap = new HashMap<String, List<String>>();
		for (String name : productNames) {
			String prefix = name.toLowerCase();
			
			if (prefix.length() > 1) {
				prefix = prefix.substring(0,2);
			}

			List<String> suggestions;
			if (productNamesSuggestionMap.containsKey(prefix)) {
				suggestions = productNamesSuggestionMap.get(prefix);
			} else {
				suggestions = new ArrayList<String>();
				productNamesSuggestionMap.put(prefix, suggestions);
			}
			
			suggestions.add(name);
		}
		event.getServletContext().setAttribute("productNamesSuggestionMap", productNamesSuggestionMap);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//vicx
	}
}
