import java.io.*;
import java.net.*;
public class EchoServer {
  private int port=8000;
  private DatagramSocket socket;

  public EchoServer() throws IOException {
    socket=new DatagramSocket(port); //与本地的一个固定端口绑定
    System.out.println("服务器启动");
  }

  public String echo(String msg) {
    return "echo:" + msg;
  }

  public void service() {
    while (true) {
      try {
        DatagramPacket packet=new DatagramPacket(new byte[512],512);
        socket.receive(packet);  //接收来自任意一个EchoClient的数据报
        String msg=new String(packet.getData(),0,packet.getLength());         
        System.out.println(packet.getAddress() + ":" +packet.getPort()
                            +">"+msg);
        
        packet.setData(echo(msg).getBytes());
        socket.send(packet);  //给EchoClient回复一个数据报
      }catch (IOException e) {
         e.printStackTrace();
      }
    }
  }

  public static void main(String args[])throws IOException {
    new EchoServer().service();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
