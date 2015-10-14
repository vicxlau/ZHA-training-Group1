package com.oocl.shopwebdemo.model;

import java.io.Serializable;


public class Product implements Serializable{
	private static final long serialVersionUID = -7966258622181207700L;
	private Integer id;
	private String name;
	private Double price;
	private String pic;
	private String remark;
	private Double adv;
	private Integer visitTime;
	private Integer categoryID;


	public Product() {}

	public Product(Integer id) {
		setId(id);
	}
	
	public Product(String name, Double price, String pic, String remark,Double adv,Integer cid) {
		setName(name);
		setPrice(price);
		setPic(pic);
		setRemark(remark);
		setAdv(adv);
		setVisitTime(0);
		setCategoryID(cid);
	}
	
	public Product(Integer id, String name, Double price, String pic, String remark,Integer cid) {
		setId(id);
		setName(name);
		setPrice(price);
		setPic(pic);
		setRemark(remark);
		setVisitTime(visitTime);
		setCategoryID(cid);
	}


	public Integer getId() { return id; }
	public String getName() { return name; }
	public Double getPrice() { return price; }
	public String getPic() { return pic; }
	public String getRemark() {	return remark; }


	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getAdv() {
		return adv;
	}

	public void setAdv(Double adv) {
		this.adv = adv;
	}

	public Integer getVisitTime() {
		return visitTime;
	}

	private void setVisitTime(Integer visitTime) {
		this.visitTime = visitTime;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
}