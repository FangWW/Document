import java.sql.*;
public class Pool2Tester implements Runnable{
  ConnectionPool pool=new ConnectionPoolImpl2();
  public static void main(String args[])throws Exception{
    Pool2Tester tester=new Pool2Tester();
    Thread[] threads=new Thread[30];
    for(int i=0;i<threads.length;i++){
      threads[i]=new Thread(tester);
      threads[i].start();
      Thread.sleep(300);
    }
    
    for(int i=0;i<threads.length;i++){
      threads[i].join();
    }
    tester.close(); //关闭连接池
  }
  public void close(){
    pool.close();
  }
  public void run(){
    try{
      Connection con=pool.getConnection();
      System.out.println(Thread.currentThread().getName()+": 从连接池取出一个连接"+con);
      Statement stmt=con.createStatement();
      stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) " 
        +"VALUES ('小王',20,'上海')");
 
      //释放相关资源
      stmt.close();
      con.close();
      System.out.println(Thread.currentThread().getName()+": 释放连接"+con);
    }catch(Exception e){e.printStackTrace();}
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
