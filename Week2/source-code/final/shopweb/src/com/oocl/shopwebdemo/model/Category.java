package com.oocl.shopwebdemo.model;


public class Category {

	private Integer id;
	private String type;
	private Boolean hot;

	public Category() {}

	public Category(Integer id, String type, Boolean hot) {
		setId(id);
		setType(type);
		setHot(hot);
	}
	
	public Category(String type, Boolean hot) {
		setType(type);
		setHot(hot);
	}


	public Integer getId() { return id; }
	public String getType() { return type; }	
	public Boolean getHot() { return hot; }


	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}	
}
