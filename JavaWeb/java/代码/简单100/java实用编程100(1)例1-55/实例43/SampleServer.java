import java.net.*;
import java.io.*;
/**
 * <p>Title: 简单服务器服务端</p>
 * <p>Description: 这是一个简单的服务器端程序</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SampleServer.java</p>
 * @author 杜江
 * @version 1.0
 */
public class SampleServer{
  public static void main(String[] arges){
    try{
      int port = 8888;
      //使用8888端口创建一个ServerSocket
      ServerSocket mySocket = new ServerSocket(port);
      //等待监听是否有客户端连接
      Socket sk = mySocket.accept();
      //输入缓存
      BufferedReader in = new BufferedReader (
                         new InputStreamReader (sk.getInputStream ()));
      //输出缓存
      PrintWriter out = new PrintWriter (
                         new BufferedWriter(
                          new OutputStreamWriter(
                           sk.getOutputStream ())), true);
      //打印接收到的客户端发送过来的信息
      System.out.println("客户端信息:"+in.readLine ());
      //向客户端回信息
      out.println("你好，我是服务器。我使用的端口号： "+port); 
    }catch(Exception e){
      System.out.println(e);
    }
  }
}