package channel;
import java.net.*;
import java.nio.channels.*;
import java.nio.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class EchoClient{
  private DatagramChannel datagramChannel = null;
  private ByteBuffer sendBuffer=ByteBuffer.allocate(1024);
  private ByteBuffer receiveBuffer=ByteBuffer.allocate(1024);
  private Charset charset=Charset.forName("GBK");
  private Selector selector;
  
  public EchoClient()throws IOException{
    this(7000);
  }
  public EchoClient(int port)throws IOException{
    datagramChannel = DatagramChannel.open();
    InetAddress ia = InetAddress.getLocalHost();
    InetSocketAddress isa = new InetSocketAddress(ia,port);
    datagramChannel.configureBlocking(false); //设置为非阻塞模式
    datagramChannel.socket().bind(isa); //与本地地址绑定
    isa = new InetSocketAddress(ia,8000);
    datagramChannel.connect(isa); //与远程地址连接
    selector=Selector.open();
  }

  public static void main(String args[])throws IOException{
    int port=7000;
    if(args.length>0)port=Integer.parseInt(args[0]);    
    final EchoClient client=new EchoClient(port);
    Thread receiver=new Thread(){
      public void run(){
        client.receiveFromUser();
      }
    };

    receiver.start();
    client.talk();
  }

  public void receiveFromUser(){
    try{
      BufferedReader localReader=new BufferedReader(new InputStreamReader(System.in));
      String msg=null;
      while((msg=localReader.readLine())!=null){
        synchronized(sendBuffer){
            sendBuffer.put(encode(msg + "\r\n"));
         }
        if(msg.equals("bye"))
          break;
      }
    }catch(IOException e){
       e.printStackTrace();
    }
  }

  public void talk()throws IOException {
     datagramChannel.register(selector,
                          SelectionKey.OP_READ |
                          SelectionKey.OP_WRITE);
     while (selector.select() > 0 ){
       Set readyKeys = selector.selectedKeys();
       Iterator it = readyKeys.iterator();
       while (it.hasNext()){
         SelectionKey key=null;
         try{
             key = (SelectionKey) it.next();
             it.remove();

             if (key.isReadable()) {
                 receive(key);
             }
             if (key.isWritable()) {
                 send(key);
             }
         }catch(IOException e){
            e.printStackTrace();
            try{
                if(key!=null){
                    key.cancel();
                    key.channel().close();
                }
            }catch(Exception ex){e.printStackTrace();}
         }
      }//#while
    }//#while
  }

  public void send(SelectionKey key)throws IOException{
    DatagramChannel datagramChannel=(DatagramChannel)key.channel();
    synchronized(sendBuffer){
        sendBuffer.flip(); //把极限设为位置
        datagramChannel.write(sendBuffer);
        sendBuffer.compact();
     }
  }
  public void receive(SelectionKey key)throws IOException{
    DatagramChannel datagramChannel=(DatagramChannel)key.channel();
    datagramChannel.read(receiveBuffer);
    receiveBuffer.flip();
    String receiveData=decode(receiveBuffer);

    if(receiveData.indexOf("\n")==-1)return;

    String outputData=receiveData.substring(0,receiveData.indexOf("\n")+1);
    System.out.print(outputData);
    if(outputData.equals("echo:bye\r\n")){
        key.cancel();
        datagramChannel.close();
        System.out.println("关闭与服务器的连接");
        selector.close();
        System.exit(0);
    }

    ByteBuffer temp=encode(outputData);
    receiveBuffer.position(temp.limit());
    receiveBuffer.compact();
  }

  public String decode(ByteBuffer buffer){  //解码
    CharBuffer charBuffer= charset.decode(buffer);
    return charBuffer.toString();
  }
  public ByteBuffer encode(String str){  //编码
    return charset.encode(str);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
