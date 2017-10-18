/**
 * 
 */
package com.sly.user.business;

import com.lsy.dbc.DataBaseConnection;
import com.lsy.user.dao.UserDao;
import com.lsy.user.factory.UserDaoFactory;
import com.lsy.vo.User;

import java.sql.Connection;

/**
 * @author ����Ԩ
 * 
 */

public class UserManager {

	// ͨ������ģʽȡ�����ʵ�� UserDaoImp
	private static UserManager instance = new UserManager();
	private UserDao userDao = null;

	private UserManager() {
		userDao = UserDaoFactory.getInstance();
	}

	public static UserManager getInstance() {
		return instance;
	}

	/**
	 * ��¼��֤
	 * 
	 * @param
	 * @return
	 * 
	 */
	public User userLogin(String qq, String password) {
		User user = null;
		Connection conn = null;
		try {
			conn = DataBaseConnection.getConn();
			user = userDao.userLogin(conn, qq, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	/**
	 * �û�ע��
	 * 
	 * @param User
	 *            user
	 * @return boolean flag
	 */
	public boolean userReg(User user) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DataBaseConnection.getConn();
			flag = userDao.userRge(conn, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConn(conn);
		}
		return flag;
	}

	/**
	 * QQ�˺Ų���
	 * 
	 * @param
	 * @return
	 * 
	 */
	public User findById(String qq) {
		Connection conn = null;
		User user = null;
		try {
			conn = DataBaseConnection.getConn();
			user = userDao.findById(conn, qq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConn(conn);
		}

		return user;
	}

	public boolean updataUser(User user) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DataBaseConnection.getConn();
			flag = userDao.updataUser(conn, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConn(conn);
		}
		return flag;
	}

	

}
