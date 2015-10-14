package com.oocl.shopwebdemo.util;

import java.util.TimerTask;
import javax.servlet.ServletContext;
import com.oocl.shopwebdemo.service.*;

public class TimerTaskUtil extends TimerTask {

	private ServletContext application=null;
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	@Override
	public void run() {
		application.setAttribute("home_data", new ApplicationServiceImpl().refreshHomeData());
	}
}
