import java.sql.*;
public class ProcedureTester{
  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    CallableStatement cStmt = con.prepareCall("{call demoSp(?, ?)}");
    //设置第一个参数的值
    cStmt.setString(1, "Tom");
    //cStmt.setString("inputParam", "Tom");
    
    //注册第二个参数的类型
    cStmt.registerOutParameter(2, Types.INTEGER);
    //cStmt.registerOutParameter("inOutParam", Types.INTEGER);
    
    //设置第二个参数的值
    cStmt.setInt(2, 1);
    //cStmt.setInt("inOutParam", 1);
   
    //执行存储过程
    boolean hadResults = cStmt.execute();
    //访问结果集
    if(hadResults) {
      ResultSet rs = cStmt.getResultSet();
      //SQLExecutor类参见12.1节的例程12-1
      SQLExecutor.showResultSet(rs);
    }
    
    //获得第二个参数的输出值
    int outputValue = cStmt.getInt(2); // index-based
    //int outputValue = cStmt.getInt("inOutParam"); // name-based
   
    con.close();
  }
}


/*************************
以下是MySQL数据库中存储过程的定义

CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255), INOUT inOutParam INT)
BEGIN
    DECLARE z INT;
    SET z = inOutParam + 1;
    SET inOutParam = z;

    SELECT CONCAT('hello ', inputParam);
END
**************************/


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
