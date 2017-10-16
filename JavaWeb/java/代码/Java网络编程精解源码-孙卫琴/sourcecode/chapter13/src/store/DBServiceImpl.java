package store;

import java.sql.*;
import java.util.*;
import java.io.*;

public class DBServiceImpl implements DBService{

  private ConnectionPool pool; //连接池

  public DBServiceImpl() throws Exception{
    //ConnectionPoolImpl2连接池实现提供Connection对象的动态代理
    pool=new ConnectionPoolImpl2(); 
  }
  /** 创建并返回一个Statement对象 */
  public Statement getStatement() throws Exception{
    return pool.getConnection().createStatement();
  }
  
  /** 关闭Statement对象，以及与之关联的Connection对象*/
  public void closeStatement(Statement stmt){
    try{
    }finally{
      try{
        if(stmt!=null){
          Connection con=stmt.getConnection(); 
          stmt.close();
          //con引用Connection对象的动态代理对象，它的close()方法把自身放回连接池
          con.close(); 
        }  
      }catch(Exception e){e.printStackTrace();}
    }
  }
  /** 执行SQL update、delete和insert语句 */ 
  public void modifyTable(String sql) throws Exception{
    Statement stmt=getStatement();
    try {
       stmt.executeUpdate(sql);
    }finally{closeStatement(stmt);}
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
