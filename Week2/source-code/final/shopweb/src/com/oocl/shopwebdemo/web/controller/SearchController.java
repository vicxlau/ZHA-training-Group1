package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.dto.*;
import com.oocl.shopwebdemo.service.*;

public class SearchController extends HttpServlet {

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
										Integer.parseInt(request.getParameter("pageSize")),
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
			
		} catch(Exception e) {
			
			request.setAttribute("showErrorMsg", true);
			request.setAttribute("searchSuccess", false);
			request.setAttribute("errorMsg", e.getMessage());
		}
		

		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);


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
