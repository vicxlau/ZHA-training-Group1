package cn.oocl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.oocl.model.Product;
import cn.oocl.service.CategoryService;
import cn.oocl.service.ProductService;
import cn.oocl.service.imple.CategoryServiceImpl;
import cn.oocl.service.imple.ProductServiceImpl;

//@WebService("/CategoryServlet") used web.xml instead
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//UI -> Servlet (Data裝到Model)|| -> Service ||-> Dao -> DB
	private ProductService productService = new ProductServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	//store searching keyword, if singleton, dont use private variable, thread security problem
	//private String keyword = null;
	
    public ProductServlet() {
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
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			product.setRemark(request.getParameter("remark"));
			product.setPic(request.getParameter("pic"));
			product.setAdv(Double.parseDouble(request.getParameter("adv")));
			product.setDiscount(Integer.parseInt(request.getParameter("discount")));
			product.setCategory(categoryService.getById(Integer.parseInt(request.getParameter("category"))));
			// 2: pass to Service
			int result = productService.save(product);
		
			// 3: get the result from Service then send back to UI
			request.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(request, response); 
			
		}
		else if(status.equals("update")){
			// 1: get data from UI
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			product.setRemark(request.getParameter("remark"));
			product.setPic(request.getParameter("pic"));
			product.setId(Integer.parseInt(request.getParameter("id")));
			product.setAdv(Double.parseDouble(request.getParameter("adv")));
			product.setDiscount(Integer.parseInt(request.getParameter("dis")));
			product.setCategory(categoryService.getById(Integer.parseInt(request.getParameter("category"))));
			
			// 2: pass to Service
			int result = productService.update(product);
			
			// 3: get the result from Service then send back to UI
			request.getRequestDispatcher("/WEB-INF/productUpdate.jsp").forward(request, response); 
		}
		else if (status.equals("query")){
			// 1: get data from UI
			String name = request.getParameter("name");
			
			//store the keyword to the class's attribute (singleton)
			//keyword = type;
			
			//using session to solve the problem about singleton
			////using session, data appear if we dont shut down browser
			HttpSession session = request.getSession();
			session.setAttribute("keyword", name);
			request.setAttribute("page", 1);
			
			// 2: pass to Service, the result needs to be stored in request, session or application object.
			List<Product> productList = productService.query(name, 1, 3);
			System.out.println(productList.size());
			// 3: get the result from Service then send back to UI
			//response.sendRedirect("/shop/query.jsp"); will make request loss data
			request.setAttribute("productList", productList);
			request.setAttribute("keyword", name);

			RequestDispatcher requestDispactcher = request.getRequestDispatcher("/WEB-INF/productUpdate.jsp"); //no need include project name, because 
			requestDispactcher.forward(request, response);
			
			
		}
		else if (status.equals("delete")){
			// 1: get data from UI
			int id = Integer.parseInt(request.getParameter("id"));
			int num = productService.delete(id);
			System.out.println("Delete records: " + num);
			//ref to the above query
			String keyword = (String)request.getSession().getAttribute("keyword");
			List<Product> productList = productService.query(keyword, 1, 100);
			request.setAttribute("productList", productList);
			RequestDispatcher requestDispactcher = request.getRequestDispatcher("/WEB-INF/productUpdate.jsp"); 
			requestDispactcher.forward(request, response);
		}
		else if (status.equals("getById")){
			int id = Integer.parseInt(request.getParameter("id"));
			// find object by id
			request.setAttribute("productUpdate", productService.getById(id));
			// request "product" to update.jsp
			request.getRequestDispatcher("/WEB-INF/productEdit.jsp").forward(request, response); 
		}
		
		else if (status.equals("query2")){
			// 1: get data from UI
			String name = (String) request.getSession().getAttribute("keyword");
			int page = Integer.parseInt(request.getParameter("pageNum"));
			//store the keyword to the class's attribute (singleton)
			//keyword = type;
			
			//using session to solve the problem about singleton
			////using session, data appear if we dont shut down browser
			request.setAttribute("page", page);
			
			// 2: pass to Service, the result needs to be stored in request, session or application object.
			List<Product> productList = productService.query(name, page, 3);
			// 3: get the result from Service then send back to UI
			//response.sendRedirect("/shop/query.jsp"); will make request loss data
			request.setAttribute("productList", productList);
			request.setAttribute("keyword", name);

			RequestDispatcher requestDispactcher = request.getRequestDispatcher("/WEB-INF/productUpdate.jsp"); //no need include project name, because 
			requestDispactcher.forward(request, response);
		}
		
	}

}
