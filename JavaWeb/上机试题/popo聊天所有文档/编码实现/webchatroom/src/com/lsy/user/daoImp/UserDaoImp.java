package com.lsy.user.daoImp;

import com.lsy.dbc.DataBaseConnection;
import com.lsy.user.dao.UserDao;
import com.lsy.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class UserDaoImp implements UserDao  {
	
	//��¼��֤
	public User userLogin(Connection conn,String qq, String password)throws Exception {
		
		User user =null ;
		PreparedStatement pstmt	= null ;
		// ����һ�����������
		ResultSet rs			= null ;
		// ����һ��SQL���������ڱ���SQL���
		String sql				= null ;
		sql = "SELECT qq,nickname,sign,photo FROM user_info WHERE qq=? and password=?" ;
		try
		{			
			// ʵ�������ݿ��������
			pstmt = conn.prepareStatement(sql) ;
			// ����pstmt�����ݣ��ǰ�ID��������֤
			pstmt.setString(1,qq) ;
			pstmt.setString(2,password) ;
			// ��ѯ��¼
			rs = pstmt.executeQuery() ;
			
			if(rs.next())
			{
				// ����м�¼���û��ǺϷ��ģ����Ե�½
				user=new User();
				user.setQq(rs.getString("qq"));
				user.setNickname(rs.getString("nickname"));
				user.setSign(rs.getString("sign"));
				user.setPhoto(rs.getString("photo"));
				
				
			}
			// ���ιر�
			
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


	// �û�ע��
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

	
	// ͨ��QQ�˺Ų����û���Ϣ
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
	
	// ���� �û���Ϣ
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



