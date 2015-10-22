package cn.oocl.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import cn.oocl.model.Category;
import cn.oocl.model.Product;
import cn.oocl.service.imple.CategoryServiceImpl;
import cn.oocl.service.imple.ProductServiceImpl;

public class MyTimerTask extends TimerTask {
	private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
	private ProductServiceImpl productServiceImpl = new ProductServiceImpl();
	private ServletContext application = null;
	
	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public MyTimerTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		//List<Category> categoryList = categoryServiceImpl.queryByHot(1);
		//List<List<Product>> bigList = new ArrayList<List<Product>>();
//		for (Category category : categoryList) {
//			List<Product> productList = productServiceImpl.queryByCid(category.getId());
//			bigList.add(productList);
//		}
//		application.setAttribute("bigList", bigList); // let the front page											
//		// display
//		System.out.println(bigList);																			
	}
}
