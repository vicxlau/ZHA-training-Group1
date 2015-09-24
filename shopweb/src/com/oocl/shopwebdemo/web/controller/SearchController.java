package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.service.ISearchService;
import com.oocl.shopwebdemo.service.SearchServiceImpl;

public class SearchController extends HttpServlet {

	private ISearchService searchService = new SearchServiceImpl();
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	}
}
