package cn.oocl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.*;

/*
 * 1: ui設計 extjs jquery
 * 2: java: oracle, DBFactory mysqlDao + oracleDao properties files 
 * 3: RegExp
 * 
 * 操作数据库的工具类：与数据库连接之前需要具备:
 *    1: url username password port   jdbc:mysql://127.0.0.1:3306/mysql
 *    2: driver 驱动程序
 *    3: ConnUtil should be singleton, also load once only.
 * */
public class JDBCUtil {

	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	private static JDBCUtil util;
	//singleton default constructor should be private
	private JDBCUtil(){
		
	}
	
	// 1: 根据单一职责原则,连接数据库抽取一个单独的工具类
	// 2: 创建一个方法,返回相关的连接字符串
	/*private String getURLString() {
		return "jdbc:oracle:thin:@localhost:1521:XE";
	}*/

	// 在连接mysql数据库时,应该需要加载 mysql driver驱动程序, 此行为仅仅需要执行一次
	// 结论: 所有的XML,Properties,txt... 外部资源,都只需要添加一次
	static {
		// 通过Java 给定的API加载相应的数据库驱动
		// 通过相应的名称,把指定驱动类添加到JVM中
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties props = new Properties();
			props.load(JDBCUtil.class.getResourceAsStream("/conn.properties"));
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static JDBCUtil getConnUtil(){
		if (util == null){ //提高性能, 可以被免thread在synchronized 白等
			synchronized (JDBCUtil.class){
				if(util == null){
					util = new JDBCUtil(); //保證一個對象
				}
			}
		}
		return util;
	}

	// 创建一个方法,用来完成Connection对象的创建操作
	public Connection getConnection() {
		// 1: 设置连接数据库的url,username password
		try {
			return DriverManager.getConnection(url, username,password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void closeConn(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (st != null && !st.isClosed()) {
					st.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void main(String[] args) {
		JDBCUtil util = new JDBCUtil();
	}

}
