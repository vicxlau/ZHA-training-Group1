package com.oocl.shopwebdemo.model;

import java.io.*;
import java.util.*;

/*
 * Cart:用来表示购物车,如果添加到DB则就是订单表
 * */
public class Cart implements Serializable{
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
	
	public Integer getId() {
		return id;
	}
	public void updateTotal(){
		double total = 0.0;
		for (Item item : this.getItemList()) {
			total += (item.getNumber() * item.getPrice());
		}
		setTotal(total);
	}
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
	}
	
	
}
