package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.service.IPriceRecommendationService;
import com.oocl.shopwebdemo.service.PriceRecommendationServiceImpl;

public class PriceRecommendController extends HttpServlet {

	private static final long serialVersionUID = 6407598122142254924L;
	private ObjectMapper mapper = new ObjectMapper();
	private IPriceRecommendationService recommService = new PriceRecommendationServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		double lowerBound = Double.parseDouble(request.getParameter("price"));

		if (keyword == null || keyword.equals("")) {
			response.getWriter().write("");
			return;
		}
		
		response.getWriter().write(
				mapper.writeValueAsString(
						recommService.getBuyerPriceRecommendation(
								URLDecoder.decode(keyword, "UTF-8"), lowerBound)));
	}
}
