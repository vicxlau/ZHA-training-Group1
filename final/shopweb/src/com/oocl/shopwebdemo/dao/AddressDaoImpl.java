package com.oocl.shopwebdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oocl.shopwebdemo.model.*;

public class AddressDaoImpl extends BaseDaoImpl<Address> {

	@Override
	protected List<Address> mapRowsToObjects(ResultSet rs) throws Exception {
		List<Address> results = new ArrayList<Address>();
		while(rs.next()){
			results.add(new Address(rs.getInt("addr_id"), 
					rs.getString("recipient_name"), 
					rs.getString("recipient_phone"), 
					rs.getString("recipient_post"), 
					rs.getString("recipient_address"),
					rs.getBoolean("is_default")));
		}
		return results;
	}
	
	public List<Address> getAddressByUserID(int uid) {
		String sql = "call UI_USER_ADDRESS_GET_BY_USER_ID(?,?)";
		return super.executeQuery(sql, new RowMapper<Address>(){

			@Override
			public List<Address> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}
			
		}, uid);
	}
	
	public Address getDefaultAddressByUserID(int uid) {
		String sql = "call UI_USER_ADDRESS_GET_DEFAULT(?,?)";
		return (Address) super.executeQuery(sql, new RowMapper<Address>(){			
			@Override
			public List<Address> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}			
		}, uid).get(0);
	}

	public Address getAddressByAddrID(int addr_id) {
		String sql = "call UI_USER_ADDRESS_GET_BY_ADDR_ID(?,?)";
		return (Address) super.executeQuery(sql, new RowMapper<Address>(){			
			@Override
			public List<Address> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}			
		}, addr_id).get(0);
	}

//	public int save(Item item, Connection conn) {
//		// 1: 先添加Cart,然后for循环添加每个Item
//		String sql = "insert into item (item_name,item_price,item_number,cart_id) values (?,?,?,?)";
//		Object[] param = new Object[] { item.getName(), item.getPrice(),
//				item.getNumber(), item.getCart().getId() };
//		Connection connection = null;
//		PreparedStatement statement = null;
//
//		connection = conn;
//		// 2: 获取的是操作mysql prepareStatement对象
//		try {
//			// 设置为手动模式,只有出现commit才能提交,rollback则回滚
//			// connection.setAutoCommit(false);
//			statement = connection.prepareStatement(sql);
//			// 根据传入的数据,对sql语句进行赋值
//			for (int i = 0; i < param.length; i++) {
//				statement.setObject(i + 1, param[i]);
//			}
//			// 把cart添加到cart表,如果成功则将要添加购物买item到item表
//			return statement.executeUpdate();
//		} catch (Exception e) {
//			// 回滚当前事务的所有操作
//			try {
//				connection.rollback();
//				throw new RuntimeException(e);
//			} catch (SQLException e1) {
//				throw new RuntimeException(e1);
//			}
//
//		}
//	}
}
