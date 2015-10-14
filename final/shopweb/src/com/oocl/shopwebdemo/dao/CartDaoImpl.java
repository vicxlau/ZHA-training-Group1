package com.oocl.shopwebdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import com.oocl.shopwebdemo.model.Cart;
import com.oocl.shopwebdemo.model.Item;
import com.oocl.shopwebdemo.util.ConnUtil;

public class CartDaoImpl extends BaseDaoImpl<Cart> {
	
	private ItemDaoImpl itemDao=new ItemDaoImpl();
	
//	@Override
//	protected Cart getRowMapper(ResultSet rs) throws Exception {
//		return null;
//	}
	
	// 根据statement获取当前插入数据的序列号
	private int getSeqId(Statement statement){
		// 获取当前入库的cart_id
		ResultSet rs;
		try {
			rs = statement.executeQuery("select ord_seq.currval as id from dual");
			// 默认当前指针指向的表头,Next() 指向第一行
			rs.next();
			return rs.getInt("id");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 添加Cart与多个Item,并且这些操作都应该在相同的transaction中
	public int save(Cart cart) {
		// 1: 先添加Cart,然后for循环添加每个Item
		String sql = "call UI_ORDERS_SAVE(?,?,?,?,?,?)";
		Object[] param = new Object[] { cart.getName(), cart.getPhone(),cart.getRemark(),cart.getTotal(),cart.getPost(),cart.getAddress() };
		Connection connection = null;
		OracleCallableStatement statement = null;
		// 1: 通过ConnUtil:获取connection对象
		connection = ConnUtil.jdbcUtil.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			// 设置为手动模式,只有出现commit才能提交,rollback则回滚
			connection.setAutoCommit(false);
			statement = (OracleCallableStatement)connection.prepareCall(sql);
			// 根据传入的数据,对sql语句进行赋值
			for(int i=0;i<param.length;i++){
				statement.setObject(i+1, param[i]);
			}
			// 把cart添加到cart表,如果成功则将要添加购物买item到item表
			int result=statement.executeUpdate();
			int seqID=getSeqId(statement);
			cart.setId(seqID);
			// 调用添加item的方法
			for(Item temp:cart.getItemList()){
				temp.setCart(cart);
				itemDao.save(temp,connection);
				// Integer.parseInt("xxxxx");
			}
			connection.commit();
			// 统一调用父类的操作数据的方法
			return seqID;
		} catch (Exception e) {
			// 回滚当前事务的所有操作
			try {
				connection.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			
		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(null, statement, connection);
		}
	}

	@Override
	protected List<Cart> mapRowsToObjects(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
