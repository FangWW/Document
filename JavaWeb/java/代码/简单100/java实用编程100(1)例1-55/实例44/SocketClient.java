//文件名：SocketClient.java
import java.io.*;
import java.net.*; 
class SocketThreadClient extends Thread
{
 public static int count = 0;
//构造器，实现服务
 public SocketThreadClient (InetAddress addr)
 {
  count++;
  BufferedReader in = null;
  PrintWriter out = null;
  Socket sk = null;
  try{
  //使用8000端口
   sk = new Socket (addr, 8000);
   InputStreamReader isr;
   isr = new InputStreamReader (sk.getInputStream ());
   in = new BufferedReader (isr);
   //建立输出
   out = new PrintWriter (
          new BufferedWriter(
           new OutputStreamWriter(
             sk.getOutputStream ())), true);
   //向服务器发送请求
   System.out.println("count:"+count);
   out.println ("Hello");
   System.out.println (in.readLine ());
   out.println ("BYE");
   System.out.println (in.readLine ());

  }
  catch (IOException e)
  {
   System.out.println (e.toString ());
  }
  finally
  {
   out.println("END");
   //释放资源
   try
   {
    if (in != null)
     in.close ();
    if (out != null)
     out.close ();
    if (sk != null)
     sk.close ();
   }
   catch (IOException e)
   {
   }
  }
 }
}
//客户端
public class SocketClient{
  public static void main(String[] args) throws IOException,InterruptedException
  {
    InetAddress addr = InetAddress.getByName(null);
      for(int i=0;i<10;i++)
         new SocketThreadClient(addr);
      Thread.currentThread().sleep(1000);
  } 
}
