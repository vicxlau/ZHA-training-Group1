package com.oocl.shopwebdemo.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oocl.shopwebdemo.model.*;

public class CartDaoImplTest {
	
	private static CartDaoImpl cartDaoImpl=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cartDaoImpl=new CartDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		cartDaoImpl=null;
	}

	@Test
	public void testSave() {
		// 模拟购物流程,在购物车中买了两件商品
		Cart cart=new Cart();
		cart.setName("小明");
		cart.setPhone("18312345678");
		cart.setPost("123");
		cart.setAddress("广东珠海");
		cart.setRemark("周六周日不要配送");
		cart.setTotal(100.00);
		// 当前购买的两个商品
		Item item=new Item();
		item.setName("西服");
		item.setNumber(1);
		item.setPrice(40.00);
		// 购物项添加到购物车中
		cart.getItemList().add(item);
		
		Item item2=new Item();
		item2.setName("衬衣");
		item2.setNumber(1);
		item2.setPrice(60.00);
		// 购物项添加到购物车中
		cart.getItemList().add(item2);
		
		cartDaoImpl.save(cart);
	}
	
	
	
	

}
