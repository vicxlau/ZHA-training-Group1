package com.oocl.shopwebdemo.web.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.dto.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.ConfigReader;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_SEARCH_RESULT = ConfigReader.getSystemValue("URL_SEARCH_RESULT");
	private static final String URL_ERROR = ConfigReader.getSystemValue("URL_ERROR");
	private ISearchService searchService = new SearchServiceImpl();
	private ICategoryService cService = new CategoryServiceImpl();

	private ObjectMapper mapper = new ObjectMapper();

	
	// handle search box form submission
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		if (keyword == null)
			keyword = ""; //search all products
		
		try {
			SearchProductsResult searchResults;
			searchResults = searchService.searchProducts(
										keyword,
										Integer.parseInt(ConfigReader.getSystemValue("pageSize")),
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
	}
	
	
	//for ajax search box suggestion
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String keyword = request.getParameter("keyword");
		
		if (keyword == null || keyword.length() < 2) {
			response.getWriter().write("[]");
			return;
		}

		
		Map<String, List<String>> productNamesSuggestionMap = (Map<String, List<String>>) request.getServletContext().getAttribute("productNamesSuggestionMap");

		String jsonOutput = "[]";

		try {
			
			keyword = keyword.toLowerCase();
			
			String prefix = keyword.substring(0,2);
			
			if (productNamesSuggestionMap.containsKey(prefix)) {

				List<String> bucket = productNamesSuggestionMap.get(prefix);
				List<String> suggestions = new ArrayList<String>();
				
				for (String name : bucket) {
					if (name.toLowerCase().startsWith(keyword)) {
						suggestions.add(name);
					}
				}
				
				jsonOutput = mapper.writeValueAsString(suggestions);
			}

			response.getWriter().write(jsonOutput);

		} catch(Exception e) {
			response.getWriter().write(e.getMessage());	
		}
	}
}
