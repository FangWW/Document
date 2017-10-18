package com.lsy.user.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lsy.dbc.DataBaseConnection;
import com.lsy.user.dao.UserDao;
import com.lsy.vo.User;

public  class UserDaoImp implements UserDao  {
	
	//登录验证
	public User userLogin(Connection conn,String qq, String password)throws Exception {
		
		User user =null ;
		PreparedStatement pstmt	= null ;
		// 声明一个结果集对象
		ResultSet rs			= null ;
		// 声明一个SQL变量，用于保存SQL语句
		String sql				= null ;
		sql = "SELECT qq,nickname,sign,photo FROM user_info WHERE qq=? and password=?" ;
		try
		{			
			// 实例化数据库操作对象
			pstmt = conn.prepareStatement(sql) ;
			// 设置pstmt的内容，是按ID和密码验证
			pstmt.setString(1,qq) ;
			pstmt.setString(2,password) ;
			// 查询记录
			rs = pstmt.executeQuery() ;
			
			if(rs.next())
			{
				// 如果有记录，用户是合法的，可以登陆
				user=new User();
				user.setQq(rs.getString("qq"));
				user.setNickname(rs.getString("nickname"));
				user.setSign(rs.getString("sign"));
				user.setPhoto(rs.getString("photo"));
				
				
			}
			// 依次关闭
			
		}
		catch(Exception e)
		{
			System.out.println(e) ;
		}
		finally
		{
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return user ;
	}


	// 用户注册
	public boolean userRge(Connection conn,User user)throws Exception {
        boolean flag=false;
        int count=0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO user_info(qq,nickname,password,age,sex,birthday,sign,photo) VALUES (?,?,?,?,?,?,?,?)";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getQq());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user.getAge());
			pstmt.setString(5, user.getSex());
			pstmt.setString(6, user.getBirthday());
			pstmt.setString(7, user.getSign());
			pstmt.setString(8, user.getPhoto());
		   count=pstmt.executeUpdate();
		   if(count>0){
			   flag=true;
		   }else{
			   flag=false;
		   }
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DataBaseConnection.closeStmt(pstmt);
		}
        return flag;
	}

	
	// 通过QQ账号查找用户信息
	public User findById(Connection conn, String qq) throws Exception{

		String sql = "select qq,nickname,age,sex,birthday,sign,photo  from user_info  where qq=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setQq(rs.getString("qq"));
				user.setNickname(rs.getString("nickname"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex"));
				user.setSign(rs.getString("sign"));
				user.setBirthday(rs.getString("birthday"));
				user.setPhoto(rs.getString("photo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DataBaseConnection.closeRs(rs);
			DataBaseConnection.closeStmt(pstmt);
			DataBaseConnection.closeConn(conn);
		}
		
		return user;

	}
	
	// 更新 用户信息
	public boolean updataUser(Connection conn, User user)throws Exception {
		boolean flag=false;
		int count=0;
		String sql = "UPDATE user_info SET nickname=?,sex=?,age=?,birthday=?,photo=?,sign=? WHERE qq=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getSex());
			pstmt.setInt(3, user.getAge());
			pstmt.setString(4, user.getBirthday());
			pstmt.setString(5, user.getPhoto());
			pstmt.setString(6, user.getSign());
			pstmt.setString(7, user.getQq());
		    count=pstmt.executeUpdate();
		    if(count>0){
		    	flag=true;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DataBaseConnection.closeStmt(pstmt);
			DataBaseConnection.closeConn(conn);

		}
		return flag;

	}
	
	
	
	
	
	}



