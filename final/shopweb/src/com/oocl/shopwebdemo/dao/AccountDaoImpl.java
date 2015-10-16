package com.oocl.shopwebdemo.dao;
import java.sql.*;
import java.util.*;

import com.oocl.shopwebdemo.model.*;

public class AccountDaoImpl extends BaseDaoImpl<User> implements IAccountDao {
	@Override
	protected List<User> mapRowsToObjects(ResultSet rs) throws Exception {
		List<User> results = new ArrayList<User>();
		
		while (rs.next()) {
			User u= new User();
			u.setId(rs.getInt("user_id"));
			u.setName(rs.getString("user_name"));
			u.setRoleId(rs.getInt("role_id"));			
			results.add(u);
		}
		return results;
	}
	
	@Override
	public User getValidUser(String login_id,String pw){
		User result;
		try{
			String sql = "call UI_USER_GET_BY_LOGINID_PW(?,?,?)";
//			result = super.executeQuery(sql,login_id,pw).get(0);
			result = super.executeQuery(sql,new RowMapper<User>() {
				@Override
				public List<User> getRowMapper(ResultSet rs)throws Exception {
					return mapRowsToObjects(rs);
				}
			},login_id,pw).get(0);
		}catch(Exception e){
			// add comment/ log
			result = null;
		}
		return result;
	}

	public User getValidUser(int id){
		User u;
		try{
			String sql = "call UI_USER_GET_BY_ID(?,?)";
//			u = super.executeQuery(sql,id).get(0);
			u = super.executeQuery(sql,new RowMapper<User>() {
				@Override
				public List<User> getRowMapper(ResultSet rs)throws Exception {
					return mapRowsToObjects(rs);
				}
			},id).get(0);
		}catch(Exception e){
			// add comment/ log
			u = null;
		}
		return u;
	}
}
