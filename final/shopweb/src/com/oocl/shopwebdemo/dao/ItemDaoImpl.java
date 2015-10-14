package com.oocl.shopwebdemo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import com.oocl.shopwebdemo.model.Item;

public class ItemDaoImpl extends BaseDaoImpl<Item> {
	
//	@Override
//	protected Item getRowMapper(ResultSet rs) throws Exception {
//		return null;
//	}
	
	// connection必须从外面传入,这样可以保证所有的操作都是同一个事务
	public int save(Item item,Connection conn) {
		// 1: 先添加Cart,然后for循环添加每个Item
		String sql = "call UI_ITEM_SAVE(?,?,?,?)";
		Object[] param = new Object[] { item.getName(),item.getPrice(),item.getNumber(),item.getCart().getId()};
		Connection connection = null;
		OracleCallableStatement statement = null;
		
		connection = conn;
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			// 设置为手动模式,只有出现commit才能提交,rollback则回滚
			// connection.setAutoCommit(false);
			statement = (OracleCallableStatement)connection.prepareCall(sql);
			// 根据传入的数据,对sql语句进行赋值
			for(int i=0;i<param.length;i++){
				statement.setObject(i+1, param[i]);
			}
			// 把cart添加到cart表,如果成功则将要添加购物买item到item表
			return statement.executeUpdate();
		}catch (Exception e) {
			// 回滚当前事务的所有操作
			try {
				connection.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		} 
	}

	@Override
	protected List<Item> mapRowsToObjects(ResultSet rs) throws Exception {
		return null;
	}
	
}
