package com.oocl.shopwebdemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * Conntil singleton encapsulates PoolDataSource
 */
public class ConnUtil {

	private static final String connUrl;
	private static final String username;
	private static final String password;
	
	public static ConnUtil jdbcUtil = null;
	
	
	static{
		try {
			// load jdbc driver
			Class.forName("oracle.jdbc.OracleDriver");
			
			// load conn params from config file
			Properties props = new Properties();
			props.load(ConnUtil.class.getResourceAsStream("/conn.properties"));
			connUrl = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
			
			// initialize singleton object
			if (jdbcUtil == null) {
				synchronized(ConnUtil.class) {
					if (jdbcUtil == null) {
						jdbcUtil = new ConnUtil();
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private ConnUtil() {}

	
	// return new Connection object
	public Connection getConnection(){

		try {
			return DriverManager.getConnection(connUrl, username, password);
		} catch (Exception e) {
		   throw new RuntimeException(e);
		}
	}


	// close jdbc objects
	public void closeJdbcObjects(ResultSet rs, Statement st, Connection conn){

		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(st!=null){
					st.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally{
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
