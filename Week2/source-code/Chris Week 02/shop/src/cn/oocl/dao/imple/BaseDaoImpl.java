package cn.oocl.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.oocl.model.Category;
import cn.oocl.util.JDBCUtil;

/*
 * 对db共性操作进行代码的抽取
 * */
public abstract class BaseDaoImpl<T> { //T as a class
	// define an abstract method, modify by subclass
	protected abstract T getRowMapper(ResultSet rs) throws Exception;

	/*
	 * Statement: doesnt support '?', use String only
	 * 		PreparedStatement: solved SQL Injection
	 * 			CallableStatement: SQL Storage
	 * */
	
	
	//sql: 假分頁的implement, 而且支持SQL injection (References)
	public List<T> query(String sql, String keyword, int page, int size){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		// 1: 通过ConnUtil:获取connection对象
		JDBCUtil util = JDBCUtil.getConnUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			//PreparedStatement pre-edited, via ? to get param
			//Statement use + connect String together
			statement = connection.prepareStatement(sql);
			// 根据传入的数据,对sql语句进行赋值
			statement.setObject(1,"%"+keyword+"%");
			// resultset
			rs = statement.executeQuery();
			// nextRow()
			List <T> list = new ArrayList<T>();
			while(rs.next()){
				//getRowMapper(rs);
				list.add(getRowMapper(rs));
			}
			// from sql can retrieve all result according to the condition 
			System.out.println("no.of records " + list.size());
			return list.subList((page-1)*size, size*page<list.size()?(page*size):list.size() ); //from '>=' || to '<'
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			util.closeConn(null, statement, connection);
		}
	}
	
	public List<T> query(String sql, Object[] param){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		// 1: 通过ConnUtil:获取connection对象
		JDBCUtil util = JDBCUtil.getConnUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = connection.prepareStatement(sql);
			// 根据传入的数据,对sql语句进行赋值
			for(int i=0; i<param.length; i++){
				statement.setObject(i+1, param[i]);
			}
			// resultset
			rs = statement.executeQuery();
			// nextRow()
			List <T> list = new ArrayList<T>();
			while(rs.next()){
				//getRowMapper(rs);
				list.add(getRowMapper(rs));
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			util.closeConn(null, statement, connection);
		}
	}
	
	// 
	public T get(String sql, Object param){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		// 1: 通过ConnUtil:获取connection对象
		JDBCUtil util = JDBCUtil.getConnUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = connection.prepareStatement(sql);
			// 根据传入的数据,对sql语句进行赋值
			statement.setObject(1, param);
			// resultset
			rs = statement.executeQuery();
			// nextRow()
			T model = null;
			if(rs.next()){
				model=getRowMapper(rs);
			}
			return model;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			util.closeConn(null, statement, connection);
		}
	}
	

	public int executeUpdate(String sql, Object[] param) {
		Connection connection = null;
		PreparedStatement statement = null;
		// 1: 通过ConnUtil:获取connection对象
		JDBCUtil util = JDBCUtil.getConnUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = connection.prepareStatement(sql);
			// 根据传入的数据,对sql语句进行赋值
			for (int i = 0; i < param.length; i++) {
				statement.setObject(i + 1, param[i]);
			}
			return statement.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			util.closeConn(null, statement, connection);
		}

	}
}
