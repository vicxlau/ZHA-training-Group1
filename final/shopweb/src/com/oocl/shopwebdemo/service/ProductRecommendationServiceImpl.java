package com.oocl.shopwebdemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.model.Product;

public class ProductRecommendationServiceImpl implements IProductRecommendationService {

	private static final Map<Integer, Map<Integer, Integer>> COMMON_RECENT_ITEMS = new HashMap<Integer, Map<Integer, Integer>>();
	private IProductDao productDao = new ProductDaoImpl();
	
	@Override
	public List<Product> recommendProducts(int productId) {
		
		if (COMMON_RECENT_ITEMS.containsKey(productId)) {
			
			Map<Integer, Integer> currentBrowsedProductsCntMap = COMMON_RECENT_ITEMS.get(productId);
			
			List<Integer> prod_id_list = new ArrayList<Integer>();
			prod_id_list.addAll(currentBrowsedProductsCntMap.keySet());
			
			return productDao.getProductsByIds(prod_id_list);
			
		} else {
			return new ArrayList<Product>();
		}
	}


	@Override
	public void addProductBrowsePairs(Product lastBrowsedProduct, Product currentBrowsedProduct) {
		
		Map<Integer, Integer> currentBrowsedProductsCntMap;
		Integer lastBrowsedProductId = lastBrowsedProduct.getId();
		Integer currentBrowsedProductId = currentBrowsedProduct.getId();
		
		if (lastBrowsedProductId.equals(currentBrowsedProductId))
			return;

		if (COMMON_RECENT_ITEMS.containsKey(lastBrowsedProductId)) {
			currentBrowsedProductsCntMap = COMMON_RECENT_ITEMS.get(lastBrowsedProductId);

		} else {
			currentBrowsedProductsCntMap = new HashMap<Integer, Integer>();
			COMMON_RECENT_ITEMS.put(lastBrowsedProductId, currentBrowsedProductsCntMap);
		}
		
		
		Integer cnt;
		if (currentBrowsedProductsCntMap.containsKey(currentBrowsedProductId)) {
			cnt = currentBrowsedProductsCntMap.get(currentBrowsedProductId);
			
		} else {
			cnt = 0;
		}
		
		currentBrowsedProductsCntMap.put(currentBrowsedProductId, cnt+1);
		
		
		// filter away the weakest relationship
		Integer min_cnt = Integer.MAX_VALUE;
		Integer min_key = -1;
		if (currentBrowsedProductsCntMap.keySet().size() > 6) {
			
			for (Entry<Integer, Integer> entry : currentBrowsedProductsCntMap.entrySet()) {
				if (entry.getValue() < min_cnt) {
					min_key = entry.getKey();
				}
			}
			
			if (min_key != -1) {
				currentBrowsedProductsCntMap.remove(min_key);
			}
		}
	}

}
