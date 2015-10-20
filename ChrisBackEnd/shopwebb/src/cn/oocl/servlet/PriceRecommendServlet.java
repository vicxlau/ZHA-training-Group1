package cn.oocl.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.oocl.service.PriceRecommendationService;
import cn.oocl.service.imple.PriceRecommendationServiceImpl;


public class PriceRecommendServlet extends HttpServlet {
	
	private ObjectMapper mapper = new ObjectMapper();
	private PriceRecommendationService recommService = new PriceRecommendationServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword.equals("")) {
			response.getWriter().write("");
			return;
		}
		
		response.getWriter().write(
				mapper.writeValueAsString(
						recommService.getSellerPriceRecommendation(
								URLDecoder.decode(keyword, "UTF-8"))));
	}
}
