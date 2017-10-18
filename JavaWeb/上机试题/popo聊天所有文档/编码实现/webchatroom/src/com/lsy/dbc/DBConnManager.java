package com.lsy.dbc;

import java.sql.*;
import java.util.*;


/*���ӳع�����,���Թ�������ݿ����ӳ�*/
public class DBConnManager {
	//���ӳ����б�
	
	private Vector<String> poolnames = new Vector<String>();
	//��������б�
	private Vector<String> drivernames = new Vector<String>();
	//��ݿ��ʶ�б�
	private Vector<String> dbids = new Vector<String>();
	//�û����б�
	private Vector<String> usernames = new Vector<String>();
	//�����б�
	private Vector<String> passwds = new Vector<String>();
	//����������б�
	private Vector<String> maxconns = new Vector<String>();
	//���ӳض���
	private Hashtable<String, DBConnPool> connPools = new Hashtable<String, DBConnPool>();
	
	public DBConnManager() {
		//���mysql��ݿ��������Ϣ
		poolnames.addElement("mysql");
		drivernames.addElement("org.gjt.mm.mysql.Driver");
		dbids.addElement("jdbc:mysql://localhost/test");
		usernames.addElement("root");
		passwds.addElement("shangdii");
		maxconns.addElement("5");
		
		//���access��ݿ��������Ϣ
		poolnames.addElement("access");
		drivernames.addElement("sun.jdbc.odbc.JdbcOdbcDriver");
		dbids.addElement("jdbc:odbc:sun");
		usernames.addElement("");
		passwds.addElement("");
		maxconns.addElement("5");
		
		//���sql��ݿ��������Ϣ
		poolnames.addElement("sql");
		drivernames.addElement("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		dbids.addElement("jdbc:microsoft:sqlserver://localhost:1433:student");
		usernames.addElement("sa");
		passwds.addElement("");
		maxconns.addElement("10");

		//���oracle��ݿ��������Ϣ
		poolnames.addElement("oracle");
		drivernames.addElement("oracle.jdbc.driver.OracleDriver");
		dbids.addElement("jdbc:oracle:thin:@localhost:1521:mldn");
		usernames.addElement("scott");
		passwds.addElement("tiger");
		maxconns.addElement("10");
		
		//�������ӳ�
		createPools();
	}
	
	/*�����ӷ��ظ���ָ�������ӳ�*/
	public void releaseConnection(String name, Connection con) {
		DBConnPool pool = connPools.get(name);
		if (pool != null)
			pool.releaseConnection(con);
	}
	
	/*�õ�һ��ָ�����ӳ��е�����*/
	public Connection getConnection(String name) {
		DBConnPool pool = connPools.get(name);
		if (pool != null)
			return pool.getConnection();
		return null;
	}
	
	/*�ر���������*/
	public synchronized void closeConns() {
		Enumeration<DBConnPool> allPools = connPools.elements();
		while (allPools.hasMoreElements()) {
			DBConnPool pool = allPools.nextElement();
			pool.closeConn();
		}
	}
	
	/*�������ӳ�*/
	private void createPools() {
		for(int i = 0; i<poolnames.size();i++){
			String poolname = poolnames.elementAt(i).toString();
			String drivername = drivernames.elementAt(i).toString();
			String dbid = dbids.elementAt(i).toString();
			String username = usernames.elementAt(i).toString();
			String passwd = passwds.elementAt(i).toString();
			int maxconn=0;
			try {
				maxconn = Integer.parseInt(maxconns.elementAt(i).toString());
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
			DBConnPool pool = new DBConnPool(poolname, drivername, dbid, username, passwd, maxconn);
			connPools.put(poolname, pool);
			//System.out.println("**********pool********"+pool);
		}
	}
}

