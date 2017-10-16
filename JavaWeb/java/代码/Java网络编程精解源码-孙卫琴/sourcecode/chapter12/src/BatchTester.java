import java.sql.*;
public class BatchTester{
  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    try{
      con.setAutoCommit(false);
      Statement stmt=con.createStatement();
      stmt.addBatch("delete from ACCOUNTS");
      stmt.addBatch("insert into ACCOUNTS(ID,NAME,BALANCE)"
        +"values(1,'Tom',1000)");
      stmt.addBatch("insert into ACCOUNTS(ID,NAME,BALANCE)"
        +"values(2,'Jack',1000)");
      stmt.addBatch("update ACCOUNTS set BALANCE=900 where ID=1");
      stmt.addBatch("update ACCOUNTS set BALANCE=1100 where ID=2");
      int[] updateCounts=stmt.executeBatch();
      for (int i = 0; i < updateCounts.length; i++) {
        System.out.print(updateCounts[i] + "   ");
      }
      con.commit(); 
    }catch(BatchUpdateException ex) {
      System.err.println(
        "----BatchUpdateException----");
      System.err.println("SQLState:  " + ex.getSQLState());
      System.err.println("Message:  " + ex.getMessage());
      System.err.println(
        "Vendor:  " + ex.getErrorCode());
      System.err.print("Update counts:  ");
      int [] updateCounts = ex.getUpdateCounts();
      for (int i = 0; i < updateCounts.length; i++) {
        System.err.print(updateCounts[i] + "   ");
      }
      System.err.println("");  
      con.rollback();
    }catch(SQLException ex) {
      con.rollback();
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
