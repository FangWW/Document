import java.net.*;
public class Client {
  public static void main(String args[])throws Exception{
    final int length=100;
    String host="localhost";
    int port=8000;

    Socket[] sockets=new Socket[length];
    for(int i=0;i<length;i++){  //试图建立100次连接
      sockets[i]=new Socket(host, port);
      System.out.println("第"+(i+1)+"次连接成功");
    }
    Thread.sleep(3000);
    for(int i=0;i<length;i++){
      sockets[i].close();  //断开连接
    } 
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
