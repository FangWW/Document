package com.lsy.dbc ;

import java.sql.* ;
public class DataBaseConnection
{
	
	public static  Connection getConn() {
		Connection conn = null;
		// 创建DBConnManager DM对象......与数据连接池连接
		DBConnManager connManager = new DBConnManager();
		//System.out.println("^^^^^创建DBConnManager DM对象......与数据连接池连接^^^^^^^^");
		conn = connManager.getConnection("mysql");
		return conn;
	}
	
	
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStmt(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeRs(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
};