package com.oocl.shopwebdemo.model;

public class Address {

	private int addr_id;
	private String recipient_name;
	private String recipient_phone;
	private String recipient_post;
	private String recipient_address;
	private boolean is_default;
	
	public Address(int id, String name, String phone, String post, String addr, boolean is_default){
		this.addr_id = id;
		this.recipient_name = name;
		this.recipient_phone = phone;
		this.recipient_post = post;
		this.recipient_address = addr;
		this.is_default = is_default;
	}
	
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_phone() {
		return recipient_phone;
	}
	public void setRecipient_phone(String recipient_phone) {
		this.recipient_phone = recipient_phone;
	}
	public String getRecipient_post() {
		return recipient_post;
	}
	public void setRecipient_post(String recipient_post) {
		this.recipient_post = recipient_post;
	}
	public String getRecipient_address() {
		return recipient_address;
	}
	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
	}
	public boolean isIs_default() {
		return is_default;
	}
	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}
}
