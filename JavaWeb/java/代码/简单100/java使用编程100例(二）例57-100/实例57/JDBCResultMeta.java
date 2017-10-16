import java.sql.*;
/**
 * <p>Title: 结果集元数据</p>
 * <p>Description: 使用结果集元数据对象获取数据库信息。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCResultMeta.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDBCResultMeta {
 private String url="";
 private String username="";
 private String password="";
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
 public static void main(java.lang.String[] args) {
    if(args.length!=4){
      System.out.println("use: java JDBCResultMeta url username password tablename");
      return;
    }
    JDBCResultMeta JRM = new JDBCResultMeta();
    JRM.url = args[0];
    JRM.username=args[1];
    JRM.password=args[2];
    JRM.getMeta(JRM.conn(),args[3]);
}

/**
 *<br>方法说明：获得数据连接
 *<br>输入参数：
 *<br>返回类型：Connection 连接对象
 */  
  public Connection conn(){
     try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }catch(ClassNotFoundException cf){
    	System.out.println("can't find class"+cf);
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
 *<br>方法说明：获取结果集元数据信息
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String table 表名称
 *<br>返回类型：
 */
  public void getMeta(Connection con, String table){
   try {
     Statement Stm = con.createStatement();
     String sql="select * from "+table;
     ResultSet rs = Stm.executeQuery(sql);
     ResultSetMetaData lineInfo = rs.getMetaData();
     System.out.println("*********************RESULT META Comment************************");
     //获取数据列数
     int columnCount = lineInfo.getColumnCount();
     System.out.println("Column Count :"+columnCount);
     //获取数据列类型
     for(int i=1;i<columnCount+1;i++){
       String columeName = lineInfo.getColumnName(i);
       String columeType = lineInfo.getColumnTypeName(i);
       boolean autocol = lineInfo.isAutoIncrement(i);
       System.out.println(columeName+" = "+columeType +"  :::  "+autocol);
     }

     }catch (SQLException se) {
       // 输出数据库连接错误信息
       System.out.println("SQL Exception: " + se.getMessage());
       se.printStackTrace(System.out);
    }catch(Exception e){
       System.out.println(e);
    }finally{
       try{
       con.close();
      }catch(SQLException se){}
    } 
  }

}
