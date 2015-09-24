package cn.oocl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 操作数据库的工具类：与数据库连接之前需要具备:
 *    1: url username password port   jdbc:mysql://127.0.0.1:3306/mysql
 *    2: driver 驱动程序
 * */
public final class ConnUtil {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "system";
	private static String password = "Hk713521Jun15";
	
	public static Connection conn = null;		// stuck if many request 
	public static ConnUtil util = null;
	private ConnUtil(){}
	// 在连接mysql数据库时,应该需要加载 mysql driver驱动程序, 此行为仅仅需要执行一次
	// 结论: 所有的XML,Properties,txt... 外部资源,都只需要添加一次
	static{
		// 通过Java 给定的API加载相应的数据库驱动
		// 通过相应的名称,把指定驱动类添加到JVM中
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// 创建一个方法,用来完成Connection对象的创建操作
	public static Connection getConnection(){
		// 1: 设置连接数据库的url,username password
		try {
			return DriverManager.getConnection(url,username, password);
		} catch (Exception e) {
		   throw new RuntimeException(e);
		}
	}
	
	public static void closeConn(ResultSet rs, Statement st, Connection conn){
		try {
			if(rs!=null && !rs.isClosed()){ //conn!=null && !conn.isClosed()){
				rs.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(st!=null && !st.isClosed()){ //conn!=null && !conn.isClosed()){
					st.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
				try {
					if(conn!=null && !conn.isClosed()){ //conn!=null && !conn.isClosed()){
						conn.close();
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static ConnUtil getUtil(){
		if(util == null)
			synchronized (ConnUtil.class) {
				if(util == null)
					util = new ConnUtil();
			}
		return util;
	}
//	public static PreparedStatement prepareStatement(String sql) throws Exception {
//		return conn.prepareStatement(sql);
//	}
	
//	public static void main(String[] args) {
//		ConnUtil util=new ConnUtil();
//		System.out.println(util.getConnection());
//		System.out.println(util.getConnection());
//	}	
	
}
