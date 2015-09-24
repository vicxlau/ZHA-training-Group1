package cn.oocl.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.*;
import cn.oocl.util.ConnUtil;

/*
 * 对db共性操作进行代码的抽取
 * */
public abstract class BaseDaoImpl<T> {

	protected abstract T getRowMapper(ResultSet rs) throws Exception;


	public List<T> executeSPSelect(String sql, Object[] param) {
		Connection connection = null;
		OracleCallableStatement statement = null;
		// 1: 通过ConnUtil:获取connection对象
		OracleResultSet rs = null;
		ConnUtil util = ConnUtil.getUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = (OracleCallableStatement) connection.prepareCall(sql);
			
			// 根据传入的数据,对sql语句进行赋值
			int i=0;
			if(param != null)
				for (; i < param.length; i++) {
					statement.setObject(i + 1, param[i]);
				}
			statement.registerOutParameter(++i, OracleTypes.CURSOR);
			
			statement.execute();
//			rs = statement.getResultSet();
//			rs = statement.executeQuery();
			
			rs = (OracleResultSet) statement.getCursor(i);
//			rs = statement.executeQuery();

			List<T> listObj = new ArrayList<T>();
			while(rs.next()) {
				listObj.add(getRowMapper(rs));
			}
			return listObj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			util.closeConn(null, statement, connection);
		}
	}
	
	public int executeSPUpdate(String sql, Object[] param) {
		Connection connection = null;
		OracleCallableStatement statement = null;
		// 1: 通过ConnUtil:获取connection对象
		ConnUtil util = ConnUtil.getUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = (OracleCallableStatement) connection.prepareCall(sql);
			
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
	
	public List<T> executeSelect(String sql, Object[] param) {
		Connection connection = null;
		PreparedStatement statement = null;
		// 1: 通过ConnUtil:获取connection对象
		ResultSet rs = null;
		ConnUtil util = ConnUtil.getUtil();
		connection = util.getConnection();
		// 2: 获取的是操作mysql prepareStatement对象
		try {
			statement = connection.prepareStatement(sql);
			// 根据传入的数据,对sql语句进行赋值
			if(param != null)
				for (int i = 0; i < param.length; i++) {
					statement.setObject(i + 1, param[i]);
				}

			rs = statement.executeQuery();
			List<T> listObj = new ArrayList<T>();
			while(rs.next()) {
				listObj.add(getRowMapper(rs));
			}
			return listObj;
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
		ConnUtil util = ConnUtil.getUtil();
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
