import java.net.*;
import java.io.*;

public class MulticastSender {
  public static void main(String[] args) throws Exception{
    InetAddress group=InetAddress.getByName("224.0.0.1");
    int port=4000;
    MulticastSocket ms = null;
  
    try {
      ms = new MulticastSocket(port);
      ms.joinGroup(group);
      while (true) {
        String message = "Hello " + new java.util.Date(); 
        byte[] buffer=message.getBytes(); 
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length,group,port);
        ms.send(dp);  
        System.out.println("发送数据报给 "+group+":"+port);
        Thread.sleep(1000);
      }
    }catch (IOException e) {
      e.printStackTrace(); 
    }finally {
      if (ms != null) {
        try {
          ms.leaveGroup(group);
          ms.close();
        }
        catch (IOException e) {} 
      }
    } 
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
