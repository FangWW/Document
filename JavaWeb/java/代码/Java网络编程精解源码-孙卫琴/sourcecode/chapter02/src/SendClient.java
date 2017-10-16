import java.io.*;
import java.net.*;
public class SendClient {
  public static void main(String args[])throws Exception {
    Socket s = new Socket("localhost",8000);
    OutputStream out=s.getOutputStream();
    out.write("hello ".getBytes());
    out.write("everyone".getBytes());
    Thread.sleep(60000);  //睡眠1分钟
    s.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
