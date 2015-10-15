package cn.oocl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.oocl.model.Category;
import cn.oocl.service.CategoryService;
import cn.oocl.service.imple.CategoryServiceImpl;

//@WebService("/CategoryServlet") used web.xml instead
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//UI -> Servlet (Data裝到Model)|| -> Service ||-> Dao -> DB
	private CategoryService categoryService = new CategoryServiceImpl();
	
	//store searching keyword, if singleton, dont use private variable, thread security problem
	//private String keyword = null;
	
    public CategoryServlet() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// all requests will be sent to doPost
		this.doPost(request, response);
	}

	/*
	 * JSP: 有9個內置對象, 不同new, 是JSP 提供
	 * request: 代表前台UI 頁面的請求信息(Java is OO, 所有前request, 都會在request 的對象中)
	 * 
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		System.out.println("ref." + request.getQueryString());
//		System.out.println("address" + request.getRequestURI());
//		System.out.println("name" + request.getContextPath());
//		System.out.println("request data" + request.getParameter("type") + " " + request.getParameter("hot"));
			
		// 0: get the hidden value to handle differnet mode;
		String status = request.getParameter("status");
		if(status.equals("save")){
			// 1: get data from UI
			Category category = new Category();
			category.setType(request.getParameter("type"));
			category.setHot(Boolean.parseBoolean(request.getParameter("hot")));
			
			// 2: pass to Service
			int result = categoryService.save(category);
			System.out.println("no. of record: " + result);
			
			// 3: get the result from Service then send back to UI
	        System.out.println("----Send back to UI----");
	        response.sendRedirect("/shopwebb/AccountServlet");
			
		}
		else if (status.equals("query")){
			// 1: get data from UI
			String type = request.getParameter("type");
			
			//store the keyword to the class's attribute (singleton)
			//keyword = type;
			
			//using session to solve the problem about singleton
			////using session, data appear if we dont shut down browser
			HttpSession session = request.getSession();
			session.setAttribute("keyword", type);
			
			// 2: pass to Service, the result needs to be stored in request, session or application object.
			List<Category> categoryList = categoryService.query(type, 1, 100);
		
			// 3: get the result from Service then send back to UI
			//response.sendRedirect("/shop/query.jsp"); will make request loss data
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("keyword", type);

			RequestDispatcher requestDispactcher = request.getRequestDispatcher("/WEB-INF/categoryUpdate.jsp"); //no need include project name, because 
			requestDispactcher.forward(request, response);
			
			
		}
		else if (status.equals("delete")){
			// 1: get data from UI
			int id = Integer.parseInt(request.getParameter("id"));
			int num = categoryService.delete(id);
			System.out.println("Delete records: " + num);
			//ref to the above query
			String keyword = (String)request.getSession().getAttribute("keyword");
			List<Category> categoryList = categoryService.query(keyword, 1, 100);
			request.setAttribute("categoryList", categoryList);
			RequestDispatcher requestDispactcher = request.getRequestDispatcher("/WEB-INF/categoryUpdate.jsp"); 
			requestDispactcher.forward(request, response);
		}
		else if (status.equals("getById")){
			int id = Integer.parseInt(request.getParameter("id"));
			// find object by id
			request.setAttribute("categoryUpdate", categoryService.getById(id));
			// request "category" to update.jsp
			request.getRequestDispatcher("/WEB-INF/categoryEdit.jsp").forward(request, response); 
		}
		else if(status.equals("update")){
			// 1: get data from UI
			Category category = new Category();
			category.setType(request.getParameter("type"));
			category.setHot(Boolean.parseBoolean(request.getParameter("hot")));
			category.setId(Integer.parseInt(request.getParameter("id")));
			
			// 2: pass to Service
			int result = categoryService.update(category);
			
			// 3: get the result from Service then send back to UI
			request.getRequestDispatcher("/WEB-INF/categoryUpdate.jsp").forward(request, response); 
		}
	}
}
