package com.oocl.shopwebdemo.web.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.ConfigReader;
import com.oocl.shopwebdemo.web.socket.NotificationSocket;

public class RetrievalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_ERROR = ConfigReader.getSystemValue("URL_ERROR");
	private static final String URL_PRODUCT_PAGE = ConfigReader.getSystemValue("URL_PRODUCT_PAGE");
	private static final String URL_CATEGORY_PAGE = ConfigReader.getSystemValue("URL_CATEGORY_PAGE");
	
	private ICategoryService categoryService = new CategoryServiceImpl();
	private IProductService productService = new ProductServiceImpl();
	private ISearchService searchService = new SearchServiceImpl();
	private ApplicationServiceImpl appService = new ApplicationServiceImpl();
	private IProductRecommendationService recommendService = new ProductRecommendationServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action){
			case "getVisitorCount":
				getVisitorCount(request,response);
				break;
			case "categoryByPrice":
				categoryByPrice(request,response);
				break;
			case "categoryByVolumn":
				categoryByVolumn(request,response);
				break;
			case "categoryByVisit":
				categoryByVisit(request,response);
				break;
		}
	}
	
	private void getVisitorCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int prod_id = Integer.parseInt(request.getParameter("id"));
		
		// get application stat (by day)
		Integer count = appService.getProductVisitCount((ApplicationStat)request.getServletContext().getAttribute(ConfigReader.getSystemValue("application_stat")), prod_id);
		
		//get current visiting count
//		Integer count = appService.getCurrentVisitorCount((ProductCurrentVisiter)request.getServletContext().getAttribute("appication-current-visitor"), prod_id);

		PrintWriter writer = response.getWriter();
//		writer.write(appService.getCurrentVisitorCount((ProductCurrentVisiter)request.getServletContext().getAttribute("appication-current-visitor"), prod_id));
		writer.write(count.toString());
		writer.close();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {
			case "home":
				home(request, response);
				// request.getRequestDispatcher("/index.jsp").forward(request,response);
				break;
			case "category":
				category(request, response);
				break;
			case "product":
				product(request, response);
				break;
			}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e);
			request.getRequestDispatcher(URL_ERROR).forward(request,response);
		}

	}

	private List<Category> getCategoryList(){
		DataStorage appStore = (DataStorage) getServletContext().getAttribute("home_data");
		if(appStore != null)
			return appStore.getCategoryList();
		else
			return categoryService.getAllCategory();	
	}
	
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String pid = request.getParameter("id");
		if (pid != null) {
			try {
				int id= Integer.parseInt(pid);
				Product p = searchService.getProductByIndex(id);
				request.setAttribute("product",p);
//				request.setAttribute("proName",p.getName());
//				request.setAttribute("proPrice",p.getPrice());
//				request.setAttribute("proRemark",p.getRemark());
//				request.setAttribute("proPic",p.getPic());
				request.setAttribute("categoryList",categoryService.getAllCategory());
//				productService.addProductVisitTime(id);
				
				
				// product recommendation
				HttpSession session = request.getSession();
				Product lastVisitedProduct = (Product) session.getAttribute("lastVisitedProduct");
				
				if (lastVisitedProduct != null) {
					recommendService.addProductBrowsePairs(lastVisitedProduct, p);
				}
				
				session.setAttribute("lastVisitedProduct", p);
				request.setAttribute("prodRecommendList", recommendService.recommendProducts(p.getId()));


				// CateProdStat
//				appService.updateStat((CateProductStat)request.getServletContext().getAttribute(ConfigReader.getSystemValue("application_stat")), 
//						p, 
//						(Customer)request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr")));

				appService.updateStat((ApplicationStat)request.getServletContext().getAttribute(ConfigReader.getSystemValue("application_stat")), 
						p, 
						(Customer)request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr")));
				
				// update current visitor
				ProductCurrentVisiter visitor = (ProductCurrentVisiter)request.getServletContext().getAttribute(ConfigReader.getSystemValue("appication-current-visitor"));
				// delete last visit record
				if(request.getSession().getAttribute(ConfigReader.getSystemValue("session-visiting-product"))!=null){
					int previous_view = (int) request.getSession().getAttribute(ConfigReader.getSystemValue("session-visiting-product"));
					appService.removeCurrentVisitor(visitor, previous_view);
				}
				int visitor_count = appService.addCurrentVisitor(visitor, p);
				//update session visiting product
				request.getSession().setAttribute(ConfigReader.getSystemValue("session-visiting-product"), p.getId());
				
				// set current 
				
				// Broadcast notification
//				NotificationSocket socket = new NotificationSocket();
//				socket.onMessage(visitor_count+" people are viewing this product");
				
				request.getRequestDispatcher(URL_PRODUCT_PAGE).forward(request,response);
				
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void category(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cid = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		if (cid != null) {
			try {
				
				//ori
//				request.setAttribute("catName", categoryService.getCategoryByIndex(Integer.parseInt(cid)).getType());
//
//				request.setAttribute(
//						"cateResults",
//						searchService.getProductInCategory(
//								Integer.parseInt(cid),
//								Integer.parseInt(pageNum)));
//				request.getRequestDispatcher(URL_CATEGORY_PAGE).forward(request,
//						response);

				

				SearchProductsResult searchResults;
				searchResults = searchService.getProductInCategory(
						Integer.parseInt(cid),
						Integer.parseInt(pageNum)
					);
			
				request.setAttribute("categoryList", getCategoryList());
				request.setAttribute("category", categoryService.getCategoryByIndex(Integer.parseInt(cid)));
				request.setAttribute("totalResultCount", searchResults.getTotalResultCount());
				request.setAttribute("pageSize", searchResults.getPageSize());
				request.setAttribute("pageCount", searchResults.getPageCount());
				request.setAttribute("pageNum", searchResults.getPageNum());
				request.setAttribute("cateResults", searchResults.getPageResults());

//				cateResults
				
				request.getRequestDispatcher(URL_CATEGORY_PAGE).forward(request,response);

			} catch (Exception e) {
				try {
					request.setAttribute(
							"cateResults",
							searchService.getProductInCategory(
									Integer.parseInt(cid), 1));

					request.getRequestDispatcher(URL_CATEGORY_PAGE).forward(
							request, response);
				} catch (Exception ee) {
					throw ee;
				}
			}
		}

		// SearchProductsResult searchResults;
		// searchResults = searchService.searchProducts(
		// request.getParameter("keyword"),
		// Integer.parseInt(request.getParameter("pageSize")),
		// Integer.parseInt(request.getParameter("pageNum"))
		// );
		//
		// request.setAttribute("totalResultCount",
		// searchResults.getTotalResultCount());
		// request.setAttribute("pageSize", searchResults.getPageSize());
		// request.setAttribute("pageCount", searchResults.getPageCount());
		// request.setAttribute("pageNum", searchResults.getPageNum());
		// request.setAttribute("pageResults", searchResults.getPageResults());
		//
		// request.setAttribute("searchSuccess", true);

	}

	public void home(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("categoryList", categoryService.getAllCategory());
		request.setAttribute("productList", searchService.getHotProduct());
		request.setAttribute("advList", searchService.getAdvProduct());
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
	
	public void categoryByPrice (HttpServletRequest request, HttpServletResponse response){
		//String[] checkedId = (String[]) request.getAttribute("checkedId");
				String[] checkedDiscount =(String[]) request.getParameterValues("checkedId[]");
				int cat_id = Integer.parseInt(request.getParameter("catId"));
				int pageNum = Integer.parseInt(request.getParameter("pageNum"));
//				if (checkedDiscount == null) {
//					try {
//						response.getWriter().write("select all discount");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				} else {
//					try {
//						response.getWriter().write(Arrays.toString(checkedDiscount));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
				
				
				String test = "select * from (select t.*, rownum r from ("+
								 "select k.* from"+
								  "(select p.* from category_product c_p left join product p on c_p.pro_id = p.pro_id where c_p.cat_id = ?"+
								  ") k where k.PRO_DIS > ? and k.PRO_DIS=< ? )t where rownum <= ? ) p where p.r > ?";
				 response.setContentType("text/plain");  
				  response.setCharacterEncoding("UTF-8"); 
				  
				  SearchProductsResult resultReturn = new SearchProductsResult();
				  resultReturn.setPageResults(null);
				  List<SearchProductsResult> searchResultsList = new ArrayList<>();
				  List<Product> productList = new ArrayList<>();
				  if(checkedDiscount!=null){
					  for(int i=0;i<checkedDiscount.length;i++){
						  int discountLowerBound,discountUpperBound;
						  switch(checkedDiscount[i]){
						  case"ds10%":
							  discountLowerBound=0;
							  discountUpperBound=10;
							  break;
						  case"ds20%":
							  discountLowerBound=10;
							  discountUpperBound=20;
							  break;
						  case"ds30%":
							  discountLowerBound=20;
							  discountUpperBound=30;
							  break;
						  case"ds40%":
							  discountLowerBound=30;
							  discountUpperBound=40;
							  break;
						  case"ds50%":
							  discountLowerBound=40;
							  discountUpperBound=100;
							  break;
						  default:
							  discountLowerBound=0;
							  discountUpperBound=100;
							  break;
						  }
						 // Object[] param = {cat_id,discountGt,discountLt,last_pro_num,first_pro_num};s
						  //SearchProductsResult searchResult = searchService.getProductByCatIdAndPrice(cat_id, discountLowerBound, discountUpperBound, pageNum);
						  SearchProductsResult searchResult = searchService.getProductByCatIdAndPrice(cat_id, discountLowerBound, discountUpperBound, pageNum);
						  searchResultsList.add( searchResult );
						  if(resultReturn.getPageResults()==null){
							  resultReturn.setPageResults(searchResult.getPageResults());
						  }else{
							  for(Product product:searchResult.getPageResults()){
								  if(!resultReturn.getPageResults().contains(product)){
									  resultReturn.getPageResults().add(product);
								  }
								  
							  }
							  //resultReturn.getPageResults().addAll(searchResult.getPageResults());
						  }
						  for(Product product:searchResult.getPageResults()){
							  if(!productList.contains(product)){
								  productList.add(product);
							  }
							  
						  }
						  resultReturn.setTotalResultCount(resultReturn.getTotalResultCount()+searchResult.getPageResults().size());
						  if(searchResult.getPageCount()>resultReturn.getPageCount()){
							  resultReturn.setPageCount(searchResult.getPageCount());
						  }
						 
 
				  }
				  					  
				}else{
//					 SearchProductsResult searchResult = searchService.getProductByCatIdAndPrice(cat_id, 0, 100, pageNum);
//					  searchResultsList.add( searchResult );
//					  for(Product product:searchResult.getPageResults()){
//						  if(!productList.contains(product)){
//							  productList.add(product);
//						  }
//						  
//					  }
					SearchProductsResult searchResult=searchService.getProductInCategory(cat_id, pageNum);
					searchResultsList.add( searchResult );
					 for(Product product:searchResult.getPageResults()){
						  if(!productList.contains(product)){
							  productList.add(product);
						  }
						  
					  }
					  
				}
				 // test+=")t where rownum <="+last_pro_num+") p where p.r > "+first_pro_num+";";
				  
				  
				  try {
					  ObjectMapper mapper = new ObjectMapper();
					//response.getWriter().write(mapper.writeValueAsString(productList));
					response.getWriter().write(mapper.writeValueAsString(resultReturn));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException();
				} 
			
	}
	public void categoryByVolumn (HttpServletRequest request, HttpServletResponse response){
		int cat_id = Integer.parseInt(request.getParameter("catId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		//SearchProductsResult resultReturn = new SearchProductsResult();
		//resultReturn.setPageResults(null);
		SearchProductsResult searchResult = searchService.getProductByCatIdAndVolumn(cat_id, pageNum);
		//resultReturn.setPageResults(searchResult.getPageResults());
		//resultReturn.setPageCount(searchResult.getPageCount());
		try {
			ObjectMapper mapper = new ObjectMapper();
			//response.getWriter().write(mapper.writeValueAsString(productList));
			response.getWriter().write(mapper.writeValueAsString(searchResult));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	public void categoryByVisit (HttpServletRequest request, HttpServletResponse response){
		int cat_id = Integer.parseInt(request.getParameter("catId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		//SearchProductsResult resultReturn = new SearchProductsResult();
		//resultReturn.setPageResults(null);
		SearchProductsResult searchResult = searchService.getProductByCatIdAndVisit(cat_id, pageNum);
		//resultReturn.setPageResults(searchResult.getPageResults());
		//resultReturn.setPageCount(searchResult.getPageCount());
		try {
			ObjectMapper mapper = new ObjectMapper();
			//response.getWriter().write(mapper.writeValueAsString(productList));
			response.getWriter().write(mapper.writeValueAsString(searchResult));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
