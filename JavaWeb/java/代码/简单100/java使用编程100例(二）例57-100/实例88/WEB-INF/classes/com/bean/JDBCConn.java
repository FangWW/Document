package  com.bean; 

import java.sql.*;
import java.util.*;
/**
 * <p>Title: JSP+Bean连接数据库</p>
 * <p>Description: 本实例演示JSP+Bean框架实现数据库查询操作。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCConn.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDBCConn{
  public static String url="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
  public static String username="test";
  public static String password="test";
/**
 *<br>方法说明：获得数据连接
 *<br>输入参数：
 *<br>返回类型：Connection 连接对象
 */  
  public Connection conn(){
     try {
     	//加载JDBC驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //创建数据库连接
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }catch(SQLException sqle){
    	System.out.println("can't connection db:"+sqle);
    	return null;
    } catch (Exception e) {
        System.out.println("Failed to load JDBC/ODBC driver.");
        return null;
     }
  }
/**
 *<br>方法说明：执行查询SQL语句
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String sql 要执行的SQL语句
 *<br>返回类型：
 */
  public Vector query( String sql){
  	Connection con = conn();
    Vector vResult = new Vector();
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     
     //声明语句
     Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //执行查询
     ResultSet rs = stmt.executeQuery(sql); 
     //获取记录总数  
     rs.last();  
     int intRowCount = rs.getRow(); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //获得数据字段个数
     int numColumns = rmeta.getColumnCount();

     while(rs.next())
	 {
	   Vector vTemp = new Vector();
	   for(int i = 0;i< numColumns;i++)
	   {
		String sTemp = rs.getString(i+1);
		vTemp.addElement(sTemp);
	   }
	   vResult.addElement(vTemp);
	 }
	 rs.close();
    stmt.close();
    }catch(Exception e){
      System.out.println("query error:"+e);
    }finally{
     try{
     con.close();
    }catch(SQLException e){}
    }
    return vResult;
  }
/**
 *<br>方法说明：执行查询SQL语句
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String sql 要执行的SQL语句
 *<br>输入参数：pageNo  页码数
 *<br>输入参数：pageSize  记录条数
 *<br>返回类型：
 */
  public Vector query(String sql,int pageNo,int pageSize){
    Vector vResult = new Vector();
    Connection con = conn();
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     int intRowCount;//记录总数
     int intPageCount;//总页数
     //声明语句
     Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //执行查询
     ResultSet rs = stmt.executeQuery(sql); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //获得数据字段个数
     int numColumns = rmeta.getColumnCount();
     //获取记录总数  
     rs.last();  
     intRowCount = rs.getRow(); 
     vResult.addElement(String.valueOf(intRowCount));
     //记算总页数  
     intPageCount = (intRowCount+pageSize-1) / pageSize;
	 //调整待显示的页码  
     if(pageNo>intPageCount) pageNo = intPageCount; 

	 if(intPageCount>0){
     //将记录指针定位到待显示页的第一条记录上  
     rs.absolute((pageNo-1) * pageSize + 1); 
     int i = 0;
     while(i<pageSize && !rs.isAfterLast())
	 {
	   Vector vTemp = new Vector();
	   for(int j = 0;j< numColumns;j++)
	   {
	   	
		String sTemp = rs.getString(j+1);
		vTemp.addElement(sTemp);
	   }
	   i++;
	   rs.next();
	  vResult.addElement(vTemp);	
	 }
	}
	rs.close();
    stmt.close();
    }catch(Exception e){
      System.out.println("query error:"+e);
    }finally{
    try{
      con.close();
    }catch(SQLException e){}
   }
    return vResult;
  }
/**
 *<br>方法说明：执行插入、更新、删除等没有返回结果集的SQL语句
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String sql 要执行的SQL语句
 *<br>返回类型：
 */
   public int execute( String sql){
    Connection con = conn();
    try{
     if(con==null) return -2;
     Statement stmt = con.createStatement();
     return stmt.executeUpdate(sql); 
    }catch(Exception e){
      System.out.println("query error:"+e);
      return -1;
    }finally{
     try{
      con.close();
    }catch(SQLException e){}
    }
  }

} 