package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dto.PriceRecommendation;


public interface IPriceRecommendationService {
	PriceRecommendation getSellerPriceRecommendation(String queryString);
	PriceRecommendation getBuyerPriceRecommendation(String queryString, double price);
}
