import java.io.*;
import java.net.*;

public class RandomPort{
  public static void main(String args[])throws IOException{
    ServerSocket serverSocket=new ServerSocket(0);
    System.out.println("监听的端口为:"+serverSocket.getLocalPort());
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
