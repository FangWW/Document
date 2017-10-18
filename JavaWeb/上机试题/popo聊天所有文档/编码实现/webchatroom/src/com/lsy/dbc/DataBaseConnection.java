package com.lsy.dbc ;

public class DataBaseConnection
{
	
	public static  Connection getConn() {
		Connection conn = null;
		// ����DBConnManager DM����......���������ӳ�����
		DBConnManager connManager = new DBConnManager();
		//System.out.println("^^^^^����DBConnManager DM����......���������ӳ�����^^^^^^^^");
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