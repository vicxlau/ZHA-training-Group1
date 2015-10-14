package com.oocl.shopwebdemo.web.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oocl.shopwebdemo.dto.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_SEARCH_RESULT = Locale.getSystemValue("URL_SEARCH_RESULT");
	private static final String URL_ERROR = Locale.getSystemValue("URL_ERROR");
	private ISearchService searchService = new SearchServiceImpl();
	private ICategoryService cService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		if (keyword == null)
			keyword = ""; //search all products
		
		//keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
		
		try {
			SearchProductsResult searchResults;
			searchResults = searchService.searchProducts(
										keyword,
										Integer.parseInt(Locale.getSystemValue("pageSize")),
										Integer.parseInt(request.getParameter("pageNum"))
									);

			request.setAttribute("categoryList", cService.getAllCategory());
			request.setAttribute("totalResultCount", searchResults.getTotalResultCount());
			request.setAttribute("pageSize", searchResults.getPageSize());
			request.setAttribute("pageCount", searchResults.getPageCount());
			request.setAttribute("pageNum", searchResults.getPageNum());
			request.setAttribute("pageResults", searchResults.getPageResults());

			request.setAttribute("keyword", keyword);
			request.setAttribute("searchSuccess", true);
			request.getRequestDispatcher(URL_SEARCH_RESULT).forward(request, response);
			
		} catch(Exception e) {
			
			request.setAttribute("showErrorMsg", true);
			request.setAttribute("searchSuccess", false);
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher(URL_ERROR).forward(request, response);
		}
		



		/* for ajax search
		try {
			response.getOutputStream().print(
					String.format(
						"{\"success\":true,\"dataObj\":%s }",
						mapper.writeValueAsString(
							searchService.searchProducts(
								request.getParameter("keyword"),
								Integer.parseInt(request.getParameter("pageSize")),
								Integer.parseInt(request.getParameter("pageNum"))
				))));

		} catch(Exception e) {
			response.getOutputStream().print(
					String.format(
						"{\"success\":false,\"error_msg\":\"%s\"}",
						e.getMessage()
				));	
		}
		*/
	}
}
