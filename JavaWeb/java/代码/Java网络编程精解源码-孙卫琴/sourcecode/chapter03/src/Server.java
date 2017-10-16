import java.io.*;
import java.net.*;
public class Server {
  private int port=8000;
  private ServerSocket serverSocket;

  public Server() throws IOException {
    serverSocket = new ServerSocket(port,3);  //连接请求队列的长度为3
    System.out.println("服务器启动");
  }

  public void service() {
    while (true) {
      Socket socket=null;
      try {
        socket = serverSocket.accept();  //从连接请求队列中取出一个连接
        System.out.println("New connection accepted " +
        socket.getInetAddress() + ":" +socket.getPort());
      }catch (IOException e) {
         e.printStackTrace();
      }finally {
         try{
           if(socket!=null)socket.close();
         }catch (IOException e) {e.printStackTrace();}
      }
    }
  }

  public static void main(String args[])throws Exception {
    Server server=new Server();
    Thread.sleep(60000*10);  //睡眠十分钟
    //server.service();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
