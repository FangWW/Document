import java.net.*;
import java.io.*;

public class MulticastReceiver {
  public static void main(String[] args) throws Exception{
    InetAddress group=InetAddress.getByName("224.0.0.1");
    int port=4000;
    MulticastSocket ms = null;
  
    try {
      ms = new MulticastSocket(port);
      ms.joinGroup(group);
      
      byte[] buffer = new byte[8192];
      while (true) {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ms.receive(dp);  
        String s = new String(dp.getData(),0,dp.getLength());
        System.out.println(s);
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
