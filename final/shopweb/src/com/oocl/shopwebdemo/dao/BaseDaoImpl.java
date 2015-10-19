package com.oocl.shopwebdemo.dao;

import java.sql.*;
import java.util.*;

import oracle.jdbc.*;

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

			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
			}

			rs = st.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new RuntimeException(
						"No row returned. Fail to get count.");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
		}
	}

	// protected List<T> executeQuery(String sql, Object... params) {
	protected List<T> executeQuery(String sql, RowMapper rm, Object... params) {

		Connection conn = null;
		OracleCallableStatement st = null;
		OracleResultSet rs = null;

		try {
			conn = ConnUtil.jdbcUtil.getConnection();
			st = (OracleCallableStatement) conn.prepareCall(sql);

			int i = 0;
			if (params != null)
				for (; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}
			++i;
			st.registerOutParameter(i, OracleTypes.CURSOR);

			st.execute();

			rs = (OracleResultSet) st.getCursor(i);

//			return mapRowsToObjects(rs);
			return rm.getRowMapper(rs);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
		}
	}

	
	protected List<T> executeQuery_preparedStatement(String preparedStatement, RowMapper rm, Object... params) {

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
			
			return rm.getRowMapper(rs);
			
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
		}
	}
	
	
	protected int executeUpdate(String sql, Object... params) {

		Connection conn = null;
		OracleCallableStatement st = null;

		try {
			conn = ConnUtil.jdbcUtil.getConnection();
//			conn.setAutoCommit(false);
			st = (OracleCallableStatement) conn.prepareCall(sql);

			if (params != null)
				for (int i = 0; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}

			return st.executeUpdate();

		} catch (Exception e) {
//			throw new RuntimeException(e);
			try {
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		} finally {
			ConnUtil.jdbcUtil.closeJdbcObjects(null, st, conn);
		}
	}
	//
	// protected List<T> executeQuery(String preparedStatement, Object...
	// params) {
	//
	// Connection conn = null;
	// PreparedStatement st = null;
	// ResultSet rs = null;
	//
	// try {
	// conn = ConnUtil.jdbcUtil.getConnection();
	// st = conn.prepareStatement(preparedStatement);
	//
	// for (int i=0; i<params.length; i++) {
	// st.setObject(i+1, params[i]);
	// }
	//
	// rs = st.executeQuery();
	//
	// return mapRowsToObjects(rs);
	//
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	//
	// } finally {
	// ConnUtil.jdbcUtil.closeJdbcObjects(rs, st, conn);
	// }
	// }
	//
	//
	// protected int executeUpdate(String preparedStatement, Object... params) {
	//
	// Connection conn = null;
	// PreparedStatement st = null;
	//
	// try {
	// conn = ConnUtil.jdbcUtil.getConnection();
	// st = conn.prepareStatement(preparedStatement);
	//
	// for (int i=0; i<params.length; i++) {
	// st.setObject(i+1, params[i]);
	// }
	//
	// return st.executeUpdate();
	//
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	//
	// } finally {
	// ConnUtil.jdbcUtil.closeJdbcObjects(null, st, conn);
	// }
	// }
}