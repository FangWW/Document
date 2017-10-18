/**
 * 
 */
package com.sly.user.business;

import java.sql.Connection;
import com.lsy.dbc.DataBaseConnection;
import com.lsy.user.dao.UserDao;
import com.lsy.user.factory.UserDaoFactory;
import com.lsy.vo.User;

/**
 * @author 赖声渊
 * 
 */

public class UserManager {

	// 通过单例模式取得类的实现 UserDaoImp
	private static UserManager instance = new UserManager();
	private UserDao userDao = null;

	private UserManager() {
		userDao = UserDaoFactory.getInstance();
	}

	public static UserManager getInstance() {
		return instance;
	}

	/**
	 * 登录验证
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
	 * 用户注册
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
	 * QQ账号查找
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
