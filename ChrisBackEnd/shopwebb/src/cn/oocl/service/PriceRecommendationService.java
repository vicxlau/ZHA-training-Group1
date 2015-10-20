package cn.oocl.service;

import cn.oocl.dto.PriceRecommendation;

public interface PriceRecommendationService {

	PriceRecommendation getSellerPriceRecommendation(String queryString);
	PriceRecommendation getBuyerPriceRecommendation(String queryString, double price);
}
