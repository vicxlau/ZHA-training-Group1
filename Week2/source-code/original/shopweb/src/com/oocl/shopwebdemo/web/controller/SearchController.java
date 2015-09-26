package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.dto.SearchProductsResult;
import com.oocl.shopwebdemo.service.ISearchService;
import com.oocl.shopwebdemo.service.SearchServiceImpl;

public class SearchController extends HttpServlet {

	private ISearchService searchService = new SearchServiceImpl();
	//private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		if (keyword == null)
			keyword = ""; //search all products
		
		try {
			SearchProductsResult searchResults;
			searchResults = searchService.searchProducts(
										request.getParameter("keyword"),
										Integer.parseInt(request.getParameter("pageSize")),
										Integer.parseInt(request.getParameter("pageNum"))
									);


			request.setAttribute("totalResultCount", searchResults.getTotalResultCount());
			request.setAttribute("pageSize", searchResults.getPageSize());
			request.setAttribute("pageCount", searchResults.getPageCount());
			request.setAttribute("pageNum", searchResults.getPageNum());
			request.setAttribute("pageResults", searchResults.getPageResults());

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
