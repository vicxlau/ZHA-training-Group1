package com.oocl.shopwebdemo.dao;

import com.oocl.shopwebdemo.model.User;

public interface IAccountDao {
	User getValidUser(String login_id,String pw);
}
