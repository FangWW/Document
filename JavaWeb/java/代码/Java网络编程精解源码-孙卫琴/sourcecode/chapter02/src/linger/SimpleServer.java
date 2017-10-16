package linger;
import java.io.*;
import java.net.*;
public class SimpleServer {
  public static void main(String args[])throws Exception {
    ServerSocket serverSocket = new ServerSocket(8000);
    Socket s=serverSocket.accept();
    Thread.sleep(5000);  //睡秒5秒后再读输入流
    InputStream in=s.getInputStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];
    int len=-1;
    do{
        len=in.read(buff);
        if(len!=-1)buffer.write(buff,0,len);
     }while(len!=-1);
    System.out.println(new String(buffer.toByteArray()));  //把字节数组转换为字符串
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
