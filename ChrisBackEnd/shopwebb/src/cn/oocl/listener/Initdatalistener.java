package cn.oocl.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.oocl.model.Category;
import cn.oocl.model.Product;
import cn.oocl.service.imple.CategoryServiceImpl;
import cn.oocl.service.imple.ProductServiceImpl;
/*
 *  ServletContextListener监听器：当项目启动的时候此监听器就会运行!
 *  
 *  JSP WEB组件：Servlet、Filter、Listener 都要配置在Web.xml中
 *  
 * */
public class Initdatalistener implements ServletContextListener {
	
	private CategoryServiceImpl categoryService=new CategoryServiceImpl();
	private ProductServiceImpl productService=new ProductServiceImpl();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("-------contextDestroyed--------");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("-------contextInitialized--------");
		//MyTimerTask myTimerTask = new MyTimerTask();
		//myTimerTask.setApplication(event.getServletContext());
		//Timer timer = new Timer();
		//timer.schedule(myTimerTask, 0, 1000*60*60*24);
	}

}
