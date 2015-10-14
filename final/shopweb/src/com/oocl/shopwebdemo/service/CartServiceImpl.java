package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.dao.AddressDaoImpl;
import com.oocl.shopwebdemo.dao.CartDaoImpl;
import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.util.Locale;

public class CartServiceImpl {

	private ProductServiceImpl productService=new ProductServiceImpl();
	private CartDaoImpl cartDao=new CartDaoImpl();
	private AddressDaoImpl aDao = new AddressDaoImpl();
	
	// worked in Cart
//	public double calculateTotalPrice(Cart cart){
//		double total = 0.0;
//		for (Item item : cart.getItemList()) {
//			total += (item.getNumber() * item.getPrice());
//		}
//		return total;
//	}
	
	public Cart save(Cart cart, int user_id,int addr_id, String remark){
		Address a;
		try{
			a = aDao.getAddressByAddrID(addr_id);
		}catch(Exception e){
			a = aDao.getDefaultAddressByUserID(user_id);				
		}
//		if(request.getParameter("addr_id")!=null){
//			int addr_id = Integer.parseInt(request.getParameter("addr_id"));
//			a = aDao.getAddressByAddrID(addr_id);
//		}else{
//			a = aDao.getDefaultAddressByUserID(c.getUser().getUser().getId());				
//		}
		cart.setName(a.getRecipient_name());
		cart.setPhone(a.getRecipient_phone());
		cart.setPost(a.getRecipient_post());
		cart.setAddress(a.getRecipient_address());
		cart.setRemark(remark);				
		// 2: 把购物车(cart)添加到数据库中 
		// 3: 把购物项(item)添加到数据库中 (2~3应该是相同的事务)
		cart.setId(cartDao.save(cart));
		return cart;
		
	}
	
	public Cart setCart(Cart cart, Item item){
		boolean isExist = false;
		if(cart==null){
			cart=new Cart();
		}else{
			List<Item> cartItem = cart.getItemList();
			for(Item m : cartItem){
				if(m.getId()==item.getId()){
					isExist = true;
					m.addNumber(item.getNumber());
					break;
				}
			}
		}
		if(!isExist){
			cart.getItemList().add(item);
		}
		return cart;
	}
	
	public Item addItem(int id, int num) {
		Product product = productService.getProductByIndex(id);
		// product存储到item中
		Item item=new Item();
		item.setName(product.getName());
		item.setPrice(product.getPrice());
		item.setPic(product.getPic());
		item.setNumber(num);
		item.setProduct(product);
		return item;
	}
}
