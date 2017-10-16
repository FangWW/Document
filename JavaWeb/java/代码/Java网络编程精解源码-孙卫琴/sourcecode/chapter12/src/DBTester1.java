import java.sql.*;
public class DBTester1{
  public static void main(String args[])throws Exception{
    Connection con;
    Statement stmt;
    ResultSet rs;
    //加载驱动器，下面的代码为加载MySQL驱动器
    Class.forName("com.mysql.jdbc.Driver");
    //注册MySQL驱动器
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    //连接到数据库的URL
    String dbUrl =
"jdbc:mysql://localhost:3306/STOREDB?useUnicode=true&characterEncoding=GB2312";
    String dbUser="dbuser";
    String dbPwd="1234";
    //建立数据库连接
    con = java.sql.DriverManager.getConnection(dbUrl,dbUser,dbPwd);
    //创建一个Statement对象
    stmt = con.createStatement();
    
    //增加新记录
stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) " 
+"VALUES ('小王',20,'上海')");

    //查询记录
    rs= stmt.executeQuery("SELECT ID,NAME,AGE,ADDRESS from CUSTOMERS");
    //输出查询结果
    while (rs.next()){
      long id = rs.getLong(1);
      String name = rs.getString(2);
      int age = rs.getInt(3);
      String address = rs.getString(4);
        
      //打印所显示的数据
      System.out.println("id="+id+",name="+name+",age="+age+",address="+address);
    }
 
    //删除新增加的记录
    stmt.executeUpdate("delete from CUSTOMERS where name='小王'");

    //释放相关资源
    rs.close();
    stmt.close();
    con.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
