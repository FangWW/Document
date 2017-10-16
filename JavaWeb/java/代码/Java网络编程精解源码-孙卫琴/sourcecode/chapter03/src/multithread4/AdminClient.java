package multithread4;
import java.net.*;
import java.io.*;
public class AdminClient{
  public static void main(String args[]){
    Socket socket=null;
    try{
      socket=new Socket("localhost",8001);
      //发送关闭命令
      OutputStream socketOut=socket.getOutputStream();
      socketOut.write("shutdown\r\n".getBytes());
 
      //接收服务器的反馈
      BufferedReader br = new BufferedReader(
                                  new InputStreamReader(socket.getInputStream()));
      String msg=null;
      while((msg=br.readLine())!=null)
        System.out.println(msg);
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      try{
        if(socket!=null)socket.close();
      }catch(IOException e){e.printStackTrace();}
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
