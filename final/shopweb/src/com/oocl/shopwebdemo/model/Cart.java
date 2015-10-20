package com.oocl.shopwebdemo.model;

import java.io.*;
import java.util.*;

/*
 * Cart:用来表示购物车,如果添加到DB则就是订单表
 * */
public class Cart implements Serializable {
	private static final long serialVersionUID = 1129711287892388149L;
	private Integer id;
	private String name;
	private String phone;
	private String remark;
	private Date date;
	private Double total;
	private String post;
	private String address;
	private List<Item> itemList = new ArrayList<Item>();

	public Cart() {

	}

	public Cart(Cart cart) {
		id = cart.id;
		name = cart.name;
		phone = cart.phone;
		remark = cart.remark;
		date = cart.date;
		total = cart.total;
		post = cart.post;
		address = cart.address;
		itemList = new ArrayList<Item>();
		for(Item item :cart.itemList){
			itemList.add(item);
		}
	}

	public Integer getId() {
		return id;
	}

	public void updateTotal() {
		double total = 0.0;
		for (Item item : this.getItemList()) {
			total += (item.getNumber() * item.getPrice());
		}
		setTotal(total);
	}

	public void deleteItem(int item_id) {
		for (Item t : itemList) {
			if (t.getProduct().getId() == item_id) {
				itemList.remove(t);
				break;
			}
		}
		updateTotal();
		// Item t = getItemIndexByID(item_id);
		// if(t!=null)itemList.remove(t);
		// updateTotal();
	}

	public void updateItem(int item_id, int num) {
		for (Item t : itemList) {
			if (t.getProduct().getId() == item_id)
				t.setNumber(num);
		}
		updateTotal();
	}

	// private Item getItemIndexByID(int item_id){
	// for(Item t : itemList){
	// if(t.getId()==item_id)return t;
	// }
	// return null;
	// }
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
		updateTotal();
	}

	public Item getItemByProd_ID(int prod_id) {
		for(Item item : itemList){
			if(item.getProduct().getId()==prod_id)
				return item;
		}
		return null;
	}

	public void removeItem(int prod_id) {
		Item item = getItemByProd_ID(prod_id);
		itemList.remove(item);						
		updateTotal();
	}

}