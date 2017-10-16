import java.sql.*;
import java.io.*;

public class ClobTester{
  Connection con;
  public ClobTester(Connection con){this.con=con;}

  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    ClobTester tester=new ClobTester(con);  
    tester.createTableWithClob();
    tester.saveClobToDatabase();
    tester.getClobFromDatabase();
    con.close();
  }
  public void createTableWithClob()throws Exception{
    Statement stmt=con.createStatement();
    stmt.execute("drop table if exists ACLOB");
    stmt.execute("create table ACLOB(ID bigint auto_increment primary key,FILE clob)");
    stmt.close();
  }
  /**向数据库中保存Clob数据 */ 
  public void saveClobToDatabase()throws Exception{
    PreparedStatement stmt=con.prepareStatement("insert into ACLOB(ID,FILE) values(?,?) ");  
    stmt.setLong(1,1);
    FileInputStream fin=new FileInputStream("test.txt");
    InputStreamReader reader=new InputStreamReader(fin);
    stmt.setCharacterStream(2,reader,fin.available());
    stmt.executeUpdate();
    reader.close();
    stmt.close();
  }
  /** 从数据库中读取Clob数据 */
  public void getClobFromDatabase()throws Exception{
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select FILE from ACLOB where ID=1");
    rs.next();
    Clob clob=rs.getClob(1);
    
    //把数据库中的Clob数据拷贝到test_bak.txt文件中
    Reader reader=clob.getCharacterStream();
    FileWriter writer = new FileWriter("test_bak.txt");  
    int c=-1;
    while((c=reader.read())!=-1)           
      writer.write(c);
    writer.close();
    reader.close();
    rs.close();
    stmt.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
