package com.oocl.shopwebdemo.web.controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

public class RetrievalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_ERROR = Locale.getSystemValue("URL_ERROR");
	private static final String URL_PRODUCT_PAGE = Locale.getSystemValue("URL_PRODUCT_PAGE");
	private static final String URL_CATEGORY_PAGE = Locale.getSystemValue("URL_CATEGORY_PAGE");
	
	private ICategoryService categoryService = new CategoryServiceImpl();
	private IProductService productService = new ProductServiceImpl();
	private ISearchService searchService = new SearchServiceImpl();

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
				productService.addProductVisitTime(id);
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
}
