package com.oocl.shopwebdemo.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.shopwebdemo.dto.PriceRecommendation;


public class PriceRecommendationServiceImpl implements IPriceRecommendationService {

	private ObjectMapper mapper = new ObjectMapper();
	
	
	private PriceRecommendation getPriceRecommendationFilteredByPrice(String queryString, double priceLowerBound) {
		
		PriceRecommendation recom = new PriceRecommendation();
		SummaryStatistics stats = new SummaryStatistics();
		
		try {
			HttpClient client = HttpClientBuilder.create().build();
			
			URIBuilder builder = new URIBuilder();
			builder.setScheme("http").setHost("world.taobao.com").setPath("/search/json.htm")
				.setCharset(StandardCharsets.UTF_8)
				.setParameter("_input_charset", "utf-8")
			    .setParameter("json", "on")
			    .setParameter("q", queryString);
			
			URI uri = builder.build();
			
			HttpGet httpget = new HttpGet(uri);
			System.out.println(httpget.getURI());
			
			
			HttpResponse response = client.execute(httpget);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			Map<String,Object> queryResultMap = mapper.readValue(responseString, Map.class);
			
			List itemList = (List) queryResultMap.get("itemList");
			List<Map<String,String>> productRefList = recom.getProductRefList();
			List<Double> priceList = new ArrayList<Double>();
			
			for (Object item : itemList) {
				
				Map<String,Object> itemMap = (Map<String,Object>)item;

				String price = (String)itemMap.get("currentPrice");
				Double price_double = Double.parseDouble(price);
				
				if (price_double <= priceLowerBound)
					continue;
				
				stats.addValue(price_double);
				priceList.add(price_double);
				
				Map<String,String> prod = new HashMap<String,String>();
				
				prod.put("name", (String)itemMap.get("tip"));
				prod.put("price", price);
				prod.put("href", (String)itemMap.get("href"));
				prod.put("image", (String)itemMap.get("image"));
				prod.put("shop", (String)itemMap.get("nick"));
				prod.put("location", (String)itemMap.get("loc"));
				
				productRefList.add(prod);
			}

			if (productRefList.isEmpty()) {
				recom.setMaxPrice(0.0);
				recom.setMinPrice(0.0);
				recom.setAveragePrice(0.0);
				recom.setMedianPrice(0.0);
			} else {
				recom.setMaxPrice(stats.getMax());
				recom.setMinPrice(stats.getMin());
				recom.setAveragePrice(1.0*Math.round(stats.getMean()));
			
				double[] priceArr = new double[priceList.size()];
				for (int i=0; i<priceArr.length; i++) {
					priceArr[i] = priceList.get(i);
				}
				recom.setMedianPrice(StatUtils.percentile(priceArr, 50));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return recom;	
	}

	
	@Override
	public PriceRecommendation getSellerPriceRecommendation(String queryString) {
		return this.getPriceRecommendationFilteredByPrice(queryString, 0);
	}



	@Override
	public PriceRecommendation getBuyerPriceRecommendation(String queryString, double price) {
		return this.getPriceRecommendationFilteredByPrice(queryString, price);
	}
}
