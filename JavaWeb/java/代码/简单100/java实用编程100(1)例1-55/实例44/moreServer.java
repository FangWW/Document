// 文件名：moreServer.java
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * <p>Title: 多线程服务器</p>
 * <p>Description: 本实例使用多线程实现多服务功能。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
class moreServer
{
 public static void main (String [] args) throws IOException
 {
   System.out.println ("Server starting...\n"); 
   //使用8000端口提供服务
   ServerSocket server = new ServerSocket (8000);
   while (true)
   {
    //阻塞，直到有客户连接
     Socket sk = server.accept ();
     System.out.println ("Accepting Connection...\n");
     //启动服务线程
     new ServerThread (sk).start ();
   }
 }
}
//使用线程，为多个客户端服务
class ServerThread extends Thread
{
 private Socket sk;
 
 ServerThread (Socket sk)
 {
  this.sk = sk;
 }
//线程运行实体
 public void run ()
 {
  BufferedReader in = null;
  PrintWriter out = null;
  try{
    InputStreamReader isr;
    isr = new InputStreamReader (sk.getInputStream ());
    in = new BufferedReader (isr);
    out = new PrintWriter (
           new BufferedWriter(
            new OutputStreamWriter(
              sk.getOutputStream ())), true);

    while(true){
      //接收来自客户端的请求，根据不同的命令返回不同的信息。
      String cmd = in.readLine ();
      System.out.println(cmd);
      if (cmd == null)
          break;
      cmd = cmd.toUpperCase ();
      if (cmd.startsWith ("BYE")){
      	 out.println ("BYE");
         break;
      }else{
        out.println ("你好，我是服务器！");
      }
    }
    }catch (IOException e)
    {
       System.out.println (e.toString ());
    }
    finally
    {
      System.out.println ("Closing Connection...\n");
      //最后释放资源
      try{
       if (in != null)
         in.close ();
       if (out != null)
         out.close ();
        if (sk != null)
          sk.close ();
      }
      catch (IOException e)
      {
      	System.out.println("close err"+e);
      }
    }
 }
}