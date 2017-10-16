import java.sql.*;
/**
 * <p>Title: JDBC连接数据库</p>
 * <p>Description: 本实例演示如何使用JDBC连接Oracle数据库，并演示添加数据和查询数据。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCConnCommit.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDBCConnCommit{
  private static String url="";
  private static String username="";
  private static String password="";
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
    }catch(ClassNotFoundException cnf){
    	System.out.println("driver not find:"+cnf);
    	return null;
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
  public void query(Connection con, String sql) throws SQLException,Exception {
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     //声明语句
     Statement stmt = con.createStatement();
     //执行查询
     ResultSet rs = stmt.executeQuery(sql); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //获得数据字段个数
     int numColumns = rmeta.getColumnCount();
     while(rs.next())
	 {
	   for(int i = 0;i< numColumns;i++)
	   {
		String sTemp = rs.getString(i+1);
		System.out.print(sTemp+"  ");
	   }
	  System.out.println("");	
	 }
	 rs.close();
	 stmt.close();
    }catch(Exception e){
      System.out.println("query error: sql = "+sql);
      System.out.println("query error:"+e);
      throw new SQLException("query error");
    }
  }
/**
 *<br>方法说明：执行插入、更新、删除等没有返回结果集的SQL语句
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String sql 要执行的SQL语句
 *<br>返回类型：
 */
   public void execute(Connection con, String sql) throws SQLException  {
    try{
     if(con==null) return;
     Statement stmt = con.createStatement();
     int i = stmt.executeUpdate(sql); 
     System.out.println("update row:"+i);
     stmt.close();
   }catch(Exception e){
      System.out.println("execute error: sql = "+sql);
      System.out.println(e);
      throw new SQLException("execute error");
    }
  }

/**
 *<br>方法说明：实例演示
 *<br>输入参数：
 *<br>返回类型：
 */
  public void demo(){
      JDBCConnCommit oc = new JDBCConnCommit();
      Connection conn = oc.conn();
    try{
      conn.setAutoCommit( false );
      String sql = "";
      for(int i=0;i<4;i++){
      sql = "insert into TBL_USER(id,name,password)values(seq_user.nextval,'tom','haorenpingan')";
      oc.execute(conn,sql);
      }
      sql = "select * from TBL_USER where name='tom' order by id";
      oc.query(conn,sql);
      sql = "delete from TBL_USER where name='tom'";
      oc.execute(conn,sql);
      conn.commit();
    }catch(SQLException se){
      try{
        conn.rollback(); 
      }catch(Exception e){
      }
      System.out.println(se);
    }catch(Exception e){ 
      System.out.println(e);
    }finally
    { 
      try{
       conn.close();
     }catch(SQLException e){}
    }
    
  }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arg){
    if(arg.length!=3){
      System.out.println("use: java JDBCConnCommit url username password");
      return;
    }
    JDBCConnCommit oc = new JDBCConnCommit();
    oc.url = arg[0];
    oc.username=arg[1];
    oc.password=arg[2];
    oc.demo();
  }
} 
  
