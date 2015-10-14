package com.oocl.shopwebdemo.model;

import java.io.*;

public class Category implements Serializable{

	private static final long serialVersionUID = -591157739598989637L;
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
