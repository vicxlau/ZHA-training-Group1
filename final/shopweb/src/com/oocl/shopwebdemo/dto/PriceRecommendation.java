package com.oocl.shopwebdemo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oocl.shopwebdemo.model.Product;

public class PriceRecommendation {

	private List<Map<String,String>> productRefList = new ArrayList<Map<String,String>>();
	private Double medianPrice;
	private Double averagePrice;
	private Double minPrice;
	private Double maxPrice;

	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public List<Map<String,String>> getProductRefList() {
		return productRefList;
	}
	public void setProductRefList(List<Map<String,String>> productRefList) {
		this.productRefList = productRefList;
	}
	public Double getMedianPrice() {
		return medianPrice;
	}
	public void setMedianPrice(Double medianPrice) {
		this.medianPrice = medianPrice;
	}
}
