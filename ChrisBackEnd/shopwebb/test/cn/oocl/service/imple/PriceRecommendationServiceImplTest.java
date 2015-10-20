package cn.oocl.service.imple;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.oocl.dto.PriceRecommendation;
import cn.oocl.model.Product;

public class PriceRecommendationServiceImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetSellerPriceRecommendation() {
		PriceRecommendation recom = new PriceRecommendationServiceImpl().getSellerPriceRecommendation("iphone6手机壳");
		
		System.out.println(recom.getMaxPrice());
		System.out.println(recom.getMinPrice());
		System.out.println(recom.getAveragePrice());
		System.out.println(recom.getMedianPrice());


		for (Map<String, String> item : recom.getProductRefList()) {
			System.out.println(item.get("name"));
			System.out.println(item.get("price"));
			System.out.println(item.get("href"));
			System.out.println(item.get("image"));
			System.out.println(item.get("shop"));
			System.out.println(item.get("location"));
		}
		
	}

	@Test
	public void testGetBuyerPriceRecommendation() {
		fail("Not yet implemented");
	}

}
