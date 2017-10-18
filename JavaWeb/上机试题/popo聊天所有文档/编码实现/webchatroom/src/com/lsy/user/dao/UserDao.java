/**
 * 
 */
package com.lsy.user.dao;

import java.sql.Connection;


import com.lsy.vo.User;




/**
 * @author 赖声渊
 *
 */
public interface UserDao {


	//用户注册
	public boolean userRge(Connection conn, User user)throws Exception;
	//登录验证
	public  User userLogin(Connection conn,String qq, String password)throws Exception; 
	//通过QQ账号查询
	public User  findById(Connection conn, String qq)throws Exception;
	//更新用户信息
	public boolean updataUser(Connection conn,User stu)throws Exception;
	
}
