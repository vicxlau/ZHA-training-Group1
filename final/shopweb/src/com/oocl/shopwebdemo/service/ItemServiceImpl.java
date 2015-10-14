package com.oocl.shopwebdemo.service;

import java.util.List;

import com.oocl.shopwebdemo.model.*;

public class ItemServiceImpl {

	private ProductServiceImpl productService=new ProductServiceImpl();
	
	public Cart addItemToCart(Cart cart, Item item){
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
	
	public Item newItem(int id, int num) {
		Product product = productService.getProductByIndex(id);
		// product存储到item中
		Item item=new Item();
		item.setId(product.getId());
		item.setName(product.getName());
		item.setPrice(product.getPrice());
		item.setPic(product.getPic());
		item.setNumber(num);
		item.setProduct(product);
		return item;
	}
}
