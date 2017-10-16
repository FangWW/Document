import java.sql.*;
public class SavepointTester{
  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    try{
      con.setAutoCommit(false);
      Statement stmt=con.createStatement();
      stmt.executeUpdate("delete from ACCOUNTS");
      stmt.executeUpdate("insert into ACCOUNTS(ID,NAME,BALANCE)"
        +"values(1,'Tom',1000)");
      Savepoint sp=con.setSavepoint();
      stmt.executeUpdate("update ACCOUNTS set BALANCE=900 where ID=1");
      con.rollback(sp);
      stmt.executeUpdate("insert into ACCOUNTS(ID,NAME,BALANCE)"
        +"values(2,'Jack',1000)");
      con.commit();
    }catch(SQLException e){
      con.rollback(); //撤销整个事务
    }finally{
      con.close();
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
