package com.oocl.shopwebdemo.model;


public class Product {

	private Integer id;
	private String name;
	private Double price;
	private String pic;
	private String remark;


	public Product() {}

	public Product(Integer id) {
		setId(id);
	}
	
	public Product(String name, Double price, String pic, String remark) {
		setName(name);
		setPrice(price);
		setPic(pic);
		setRemark(remark);
	}
	
	public Product(Integer id, String name, Double price, String pic, String remark) {
		setId(id);
		setName(name);
		setPrice(price);
		setPic(pic);
		setRemark(remark);
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
}