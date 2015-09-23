package com.oocl.shopwebdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.oocl.shopwebdemo.util.ConnUtil;

public abstract class BaseDaoImpl<T> {


	protected abstract List<T> mapRowsToObjects(ResultSet rs) throws Exception;


	protected int executeCountQuery(String preparedStatement, Object... params) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = ConnUtil.jdbcUtil.getConnection();
			st = conn.prepareStatement(preparedStatement);
			
			for (int i=0; i<params.length; i++) {
				st.setObject(i+1, params[i]);
			}

			rs = st.executeQuery();
		
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new RuntimeException("No row returned. Fail to get count.");
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
		}
	}
	
	
	protected List<T> executeQuery(String preparedStatement, Object... params) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = ConnUtil.jdbcUtil.getConnection();
			st = conn.prepareStatement(preparedStatement);
			
			for (int i=0; i<params.length; i++) {
				st.setObject(i+1, params[i]);
			}

			rs = st.executeQuery();
			
			return mapRowsToObjects(rs);
			
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
		}
	}


	protected int executeUpdate(String preparedStatement, Object... params) {
		
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = ConnUtil.jdbcUtil.getConnection();
			st = conn.prepareStatement(preparedStatement);

			for (int i=0; i<params.length; i++) {
				st.setObject(i+1, params[i]);
			}

			return st.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(null, st, conn);
		}
	}
}