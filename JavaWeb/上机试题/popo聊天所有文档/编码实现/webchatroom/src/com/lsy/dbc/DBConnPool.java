package com.lsy.dbc;

import java.sql.*;
import java.util.*;

/*连接池类.能够根据要求创建新连接,直到最大连接数为止.*/
public class DBConnPool {
	//实际使用中的连接数
	private int inUse=0;
	//空闲连接
	private Vector<Connection> connections = new Vector<Connection>();
	//连接池名
	private String poolname;
	//数据库标识
	private String dbid;
	//驱动程序名
	private String drivername;
	//数据库账号
	private String username;
	//数据库密码
	private String passwd;
	//最大连接数
	private int maxconn;

	public DBConnPool(String poolname, String drivername, String dbid, String username, String passwd, int maxconn) {
		this.setPoolname(poolname);
		this.dbid = dbid;
		this.drivername = drivername;
		this.username = username;
		this.passwd = passwd;
		this.maxconn = maxconn;
	}

	/*将连接返回给连接池*/
	public synchronized void releaseConnection(Connection con) {
		// 将指定连接加入到向量末尾
		connections.addElement(con);
		//连接数减一
		inUse--;
	}

	/*从连接池得到一个连接*/
	public synchronized Connection getConnection() {
		Connection con = null;
		if (connections.size() > 0) {
			// 获取连接列表中获得第一个连接
			con = connections.elementAt(0);
			connections.removeElementAt(0);
			//如果此连接已关闭，则继续获取
         try {
            if (con.isClosed())
               con = getConnection();
         }
         catch (Exception ex) {
            ex.printStackTrace();
         }
		}
		//如果实际使用的连接小于最大连接数，就新创建一个连接
		else if (maxconn == 0 || inUse < maxconn) {
			con = newConnection();
		}
		if (con != null) {
			//连接数增一
			inUse++;
		}
		//返回一个连接
		return con;
	}

	/*创建新的连接*/
	private Connection newConnection() {
		Connection con = null;
		try {
			//加载驱动程序
			Class.forName(drivername);
			//建立连接
			con = DriverManager.getConnection(dbid, username, passwd);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		//返回该连接
		return con;
	}

	/*关闭所有连接*/
	public synchronized void closeConn() {
		Enumeration<Connection> allConnections = connections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = allConnections.nextElement();
			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connections.removeAllElements();
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public String getPoolname() {
		return poolname;
	}
}
