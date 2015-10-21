package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.service.IPriceRecommendationService;
import com.oocl.shopwebdemo.service.PriceRecommendationServiceImpl;

@Controller
@RequestMapping("/priceRecommend")
public class PriceRecommendController {

	private ObjectMapper mapper = new ObjectMapper();
	private IPriceRecommendationService recommService = new PriceRecommendationServiceImpl();
	
	@RequestMapping(value = "/buyer", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getBuyerPriceRecommend(
							@RequestParam String keyword,
							@RequestParam String price
						) {

		double lowerBound = Double.parseDouble(price);

		try {
			return mapper.writeValueAsString(
							recommService.getBuyerPriceRecommendation(
									URLDecoder.decode(keyword, "UTF-8"), lowerBound));
		} catch (Exception e) {
			return "";
		}
	}
}
