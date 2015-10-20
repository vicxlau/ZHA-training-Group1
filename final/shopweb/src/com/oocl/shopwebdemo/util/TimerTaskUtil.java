package com.oocl.shopwebdemo.util;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.oocl.shopwebdemo.model.ApplicationStat;
import com.oocl.shopwebdemo.service.*;

public class TimerTaskUtil extends TimerTask {

	private ServletContext application=null;
	private ApplicationServiceImpl appService = new ApplicationServiceImpl();
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	@Override
	public void run() {
		// refresh home page data
		application.setAttribute("home_data", appService.refreshHomeData());
		
		// save application statistic into DB
//		ApplicationStat stat = (ApplicationStat) application.getAttribute(ConfigReader.getSystemValue("application_stat"));
//		appService.saveStatisticDataToDB(stat);
	}
}