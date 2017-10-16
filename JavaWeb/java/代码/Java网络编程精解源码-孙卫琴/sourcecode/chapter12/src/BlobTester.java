import java.sql.*;
import java.io.*;

public class BlobTester{
  Connection con;
  public BlobTester(Connection con){this.con=con;}

  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    BlobTester tester=new BlobTester(con);  
    tester.createTableWithBlob();
    tester.saveBlobToDatabase();
    tester.getBlobFromDatabase();
    con.close();
  }
  public void createTableWithBlob()throws Exception{
    Statement stmt=con.createStatement();
    stmt.execute("drop table if exists ABLOB");
    stmt.execute("create table ABLOB(ID bigint auto_increment primary key,FILE mediumblob)");
    stmt.close();
  }
  /**向数据库中保存Blob数据 */ 
  public void saveBlobToDatabase()throws Exception{
    PreparedStatement stmt=con.prepareStatement("insert into ABLOB(ID,FILE) values(?,?) ");  
    stmt.setLong(1,1);
    FileInputStream fin=new FileInputStream("test.gif");
    stmt.setBinaryStream(2,fin,fin.available());
    stmt.executeUpdate();
    fin.close();
    stmt.close();
  }
  /** 从数据库中读取Blob数据 */
  public void getBlobFromDatabase()throws Exception{
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select FILE from ABLOB where ID=1");
    rs.next();
    Blob blob=rs.getBlob(1);
    
    //把数据库中的Blob数据拷贝到test_bak.gif文件中
    InputStream in=blob.getBinaryStream();
    FileOutputStream fout = new FileOutputStream("test_bak.gif");  
    int b=-1;
    while((b=in.read())!=-1)           
      fout.write(b);
    fout.close();
    in.close();
    rs.close();
    stmt.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
