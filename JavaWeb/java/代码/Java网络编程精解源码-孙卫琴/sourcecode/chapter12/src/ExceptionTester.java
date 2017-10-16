import java.sql.*;
import java.io.*;

public class ExceptionTester{
  public static void main(String args[]){
    try{
      Connection con=new ConnectionProvider().getConnection();
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("select FIRSTNAME from CUSTOMERS");
    }catch(SQLException e){
      System.out.println("ErrorCode:"+e.getErrorCode());
      System.out.println("SQLState:"+e.getSQLState());
      System.out.println("Reason:"+e.getMessage());
    }
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
