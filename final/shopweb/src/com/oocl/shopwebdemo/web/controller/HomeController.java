package com.oocl.shopwebdemo.web.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;
import com.oocl.shopwebdemo.model.*;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME_PAGE = Locale.getSystemValue("URL_HOME_PAGE");
	private static final String URL_ERROR = Locale.getSystemValue("URL_ERROR");
	private ICategoryService categoryService = new CategoryServiceImpl();
	private ISearchService searchService = new SearchServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			DataStorage appStore = (DataStorage) getServletContext().getAttribute("home_data");
			if(appStore != null){
				request.setAttribute("categoryList", appStore.getCategoryList());
				request.setAttribute("productList", appStore.getHotProductList());
				request.setAttribute("advList", appStore.getAdvProductList());
			}else{
				// get data from DB
				request.setAttribute("categoryList", categoryService.getAllCategory());
				request.setAttribute("productList", searchService.getHotProduct());
				request.setAttribute("advList", searchService.getAdvProduct());
			}
			request.getRequestDispatcher(URL_HOME_PAGE).forward(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e);
			request.getRequestDispatcher(URL_ERROR).forward(request,response);
		}

	}

}
