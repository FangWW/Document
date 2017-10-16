import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
public class ReceiveChannel {
  public static void main(String args[])throws Exception {
    final int ENOUGH_SIZE=1024;
    final int SMALL_SIZE=4;
    
    boolean isBlocked=true;
    int size=ENOUGH_SIZE;
    
    if(args.length>0){
      int opt=Integer.parseInt(args[0]);
      switch(opt){
        case 1: isBlocked=true;size=ENOUGH_SIZE;break;
        case 2: isBlocked=true;size=SMALL_SIZE;break;
        case 3: isBlocked=false;size=ENOUGH_SIZE;break;
        case 4: isBlocked=false;size=SMALL_SIZE;break;
      }
    }   

    DatagramChannel channel= DatagramChannel.open();
    channel.configureBlocking(isBlocked);
    ByteBuffer buffer=ByteBuffer.allocate(size);
    DatagramSocket socket=channel.socket();
    SocketAddress localAddr=new InetSocketAddress(8000);
    socket.bind(localAddr);
    
    while(true){
      System.out.println("开始接收数据报");
      SocketAddress remoteAddr=channel.receive(buffer);
      if(remoteAddr==null){
        System.out.println("没有接收到数据报");
      }else{
        buffer.flip();
        System.out.println("接收到的数据报的大小为"+buffer.remaining());
      } 
      Thread.sleep(500);
    }
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
