import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class AcceptHandler implements Handler {
  public void handle(SelectionKey key) throws IOException {
    ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
    SocketChannel socketChannel = serverSocketChannel.accept();
    if (socketChannel== null)return;
    System.out.println("接收到客户连接，来自:" +
                   socketChannel.socket().getInetAddress() +
                   ":" + socketChannel.socket().getPort());

    ChannelIO cio =new ChannelIO(socketChannel, false/*非阻塞模式*/);
    RequestHandler rh = new RequestHandler(cio);
    socketChannel.register(key.selector(), SelectionKey.OP_READ, rh);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
