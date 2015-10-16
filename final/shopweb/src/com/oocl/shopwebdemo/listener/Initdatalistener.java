package com.oocl.shopwebdemo.listener;

import java.io.*;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oocl.shopwebdemo.model.CateProductStat;
import com.oocl.shopwebdemo.util.*;

public class Initdatalistener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		TimerTaskUtil task=new TimerTaskUtil();
		task.setApplication(event.getServletContext());
		new Timer(true).schedule(task, 0, Integer.parseInt(Locale.getSystemValue("listener-homePageDataRefreshTime")));
		
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
		
		event.getServletContext().setAttribute(Locale.getSystemValue("application_stat"), new CateProductStat());
		
		System.out.println(Locale.getSystemValue("application_stat"));
		System.out.println("app stat: " + ((CateProductStat)event.getServletContext().getAttribute("stat")).prod_list.size());
//				event.getServletContext().getAttribute(Locale.getSystemValue("application_stat")));
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//vicx
	}
}
