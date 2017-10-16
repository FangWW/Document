package channel;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
public class EchoServer {
  private int port=8000;
  private DatagramChannel channel;
  private final int MAX_SIZE=1024;

  public EchoServer() throws IOException {
    channel= DatagramChannel.open();
    DatagramSocket socket=channel.socket();
    SocketAddress localAddr=new InetSocketAddress(8000);
    socket.bind(localAddr);
    System.out.println("服务器启动");
  }

  public String echo(String msg) {
    return "echo:" + msg;
  }

  public void service() {
    ByteBuffer receiveBuffer=ByteBuffer.allocate(MAX_SIZE);
    while (true) {
      try {
        receiveBuffer.clear(); 
        InetSocketAddress client=(InetSocketAddress)channel.receive(receiveBuffer);  //接收来自任意一个EchoClient的数据报
        receiveBuffer.flip();  
        String msg=Charset.forName("GBK").decode(receiveBuffer).toString();     
        System.out.println(client.getAddress() + ":" +client.getPort()
                            +">"+msg);
        
        channel.send(ByteBuffer.wrap(echo(msg).getBytes()),client);  //给EchoClient回复一个数据报
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
