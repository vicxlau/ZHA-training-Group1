package com.oocl.shopwebdemo.model;

import java.io.*;
import java.util.*;

/*
 * Cart:用来表示购物车,如果添加到DB则就是订单表
 * */
public class CartBuffer implements Serializable {
	private static final long serialVersionUID = 1129711287892388149L;
	private Stack<Cart> buffer;

	public CartBuffer() {
		buffer = new Stack<Cart>();
	}

	public Cart pop(){
		return buffer.pop();
	}
	public CartBuffer push(Cart cart) {
		buffer.push(cart);
		return this;
	}

	public boolean isEmpty() {
		return (buffer==null || buffer.size()==0);
	}
}
