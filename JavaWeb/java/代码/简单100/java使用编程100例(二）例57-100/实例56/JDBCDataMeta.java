import java.sql.*;
/**
 * <p>Title: 数据库元数据</p>
 * <p>Description: 使用数据库元数据对象获取数据库信息。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCDataMeta.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDBCDataMeta {
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
      System.out.println("use: java JDBCDataMeta url username password tablename");
      return;
    }
    JDBCDataMeta JDM = new JDBCDataMeta();
    JDM.url = args[0];
    JDM.username=args[1];
    JDM.password=args[2];
    JDM.getMeta(JDM.conn(),args[3]);
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
 *<br>方法说明：获取数据元数据信息
 *<br>输入参数：Connection con 数据库连接
 *<br>输入参数：String table 表名称
 *<br>返回类型：
 */
  public void getMeta(Connection con, String table){
   try {
     DatabaseMetaData dbmd = con.getMetaData();
     //获取驱动名
     String dataName = dbmd.getDriverName();
     String dataURL = dbmd.getURL();
     System.out.println("**************** DATA META Comment  *********************");
     System.out.println("dataName="+dataName);
     System.out.println("dataURL="+dataURL);
     //获取数据库最大支持字节数
     int dataMaxSize = dbmd.getMaxRowSize();
     System.out.println("dataMaxSize="+dataMaxSize);
     //获取数据库表信息
     String[] types = new String[1];
     types[0] = "TABLE"; 
     ResultSet results = dbmd.getTables(null, null, "%", types);
     System.out.println("********************tables information********************");
     while (results.next()) 
     {
        System.err.println("----------------------------");
        System.err.println("TABLE_CAT   : "+results.getObject(1));
        System.err.println("TABLE_SCHEM : "+results.getObject(2));
        System.err.println("TABLE_NAME  : "+results.getObject(3));
        System.err.println("TABLE_TYPE  : "+results.getObject(4));
        System.err.println("REMARKS     : "+results.getObject(5));
    }
     //获取表主键信息
     ResultSet pkRSet = dbmd.getPrimaryKeys(null, null, table);
     System.out.println("********************PK information********************");
      while(pkRSet.next()){
        System.err.println("TABLE_CAT : "+pkRSet.getObject(1));
        System.err.println("TABLE_SCHEM: "+pkRSet.getObject(2));
        System.err.println("TABLE_NAME : "+pkRSet.getObject(3));
        System.err.println("COLUMN_NAME: "+pkRSet.getObject(4));
        System.err.println("KEY_SEQ : "+pkRSet.getObject(5));
        System.err.println("PK_NAME : "+pkRSet.getObject(6));
     }

    System.out.println("*****************FK information***********************");
    //获取表外键信息
    results = dbmd.getImportedKeys(null, null, table);
     while (results.next()) 
     {
        System.err.println("PKTABLE_CAT   : "+results.getObject(1));
        System.err.println("PKTABLE_SCHEM : "+results.getObject(2));
        System.err.println("PKTABLE_NAME  : "+results.getObject(3));
        System.err.println("PKCOLUMN_NAME : "+results.getObject(4));
        System.err.println("FKTABLE_CAT   : "+results.getObject(5));
        System.err.println("FKTABLE_SCHEM : "+results.getObject(6));
        System.err.println("FKTABLE_NAME  : "+results.getObject(7));
        System.err.println("FKCOLUMN_NAME : "+results.getObject(8));
        System.err.println("KEY_SEQ       : "+results.getObject(9));
        System.err.println("UPDATE_RULE   : "+results.getObject(10));
        System.err.println("DELETE_RULE   : "+results.getObject(11));
        System.err.println("FK_NAME       : "+results.getObject(12));
        System.err.println("PK_NAME       : "+results.getObject(13));
        System.err.println("DEFERRABILITY : "+results.getObject(13));
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
