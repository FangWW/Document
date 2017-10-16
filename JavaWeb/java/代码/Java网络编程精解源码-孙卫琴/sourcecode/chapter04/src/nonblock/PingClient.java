package nonblock;
import java.net.*;
import java.nio.channels.*;
import java.nio.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

class Target {  //表示一项任务
  InetSocketAddress address;
  SocketChannel channel;
  Exception failure;
  long connectStart;  //开始连接时的时间
  long connectFinish = 0;  //连接成功时的时间
  boolean shown = false;  //该任务是否已经打印

  Target(String host) {
      try {
          address = new InetSocketAddress(InetAddress.getByName(host),80);
      } catch (IOException x) {
          failure = x;
      }
  }

  void show() {  //打印任务执行的结果
      String result;
      if (connectFinish != 0)
          result = Long.toString(connectFinish - connectStart) + "ms";
      else if (failure != null)
          result = failure.toString();
      else
          result = "Timed out";
      System.out.println(address + " : " + result);
      shown = true;
  }
}

public class PingClient{
  private Selector selector;
  //存放用户新提交的任务
  private LinkedList targets=new LinkedList();
  //存放已经完成的需要打印的任务
  private LinkedList finishedTargets=new LinkedList();

  public PingClient()throws IOException{
    selector=Selector.open();
    Connector connector=new Connector();
    Printer printer=new Printer();
    connector.start();
    printer.start();
    receiveTarget();
  }

  public static void main(String args[])throws IOException{
    new PingClient();
  }
  public void addTarget(Target target) {
    //向targets队列中加入一个任务
     SocketChannel socketChannel = null;
      try {
          socketChannel = SocketChannel.open();
          socketChannel.configureBlocking(false);
          socketChannel.connect(target.address);

          target.channel = socketChannel;
          target.connectStart = System.currentTimeMillis();

           synchronized (targets) {
             targets.add(target);
           }
           selector.wakeup();
       } catch (Exception x) {
          if (socketChannel != null) {
              try {
                  socketChannel.close();
              } catch (IOException xx) { }
          }
          target.failure = x;
          addFinishedTarget(target);
      }
  }

  public void addFinishedTarget(Target target) {
      //向finishedTargets队列中加入一个任务
      synchronized (finishedTargets) {
      finishedTargets.notify();
      finishedTargets.add(target);
     }
  }

  public void printFinishedTargets() {
    //打印finisedTargets队列中的任务
     try {
        for (;;) {
            Target target = null;
            synchronized (finishedTargets) {
                while (finishedTargets.size() == 0)
                    finishedTargets.wait();
                target = (Target)finishedTargets.removeFirst();
            }
            target.show();
        }
    } catch (InterruptedException x) {
        return;
    }
  }

  public void registerTargets(){
    //取出targets队列中的任务，向Selector注册连接就绪事件
    synchronized (targets) {
      while (targets.size() > 0) {
        Target target = (Target)targets.removeFirst();

        try {
          target.channel.register(selector, SelectionKey.OP_CONNECT, target);
        } catch (IOException x) {
              try{target.channel.close();}catch(IOException e){e.printStackTrace();}
              target.failure = x;
              addFinishedTarget(target);
        }
      }
    }
  }

  public void processSelectedKeys() throws IOException {
    //处理连接就绪事件
    for (Iterator it = selector.selectedKeys().iterator(); it.hasNext();) {
      SelectionKey selectionKey = (SelectionKey)it.next();
      it.remove();

      Target target = (Target)selectionKey.attachment();
      SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

      try {
          if (socketChannel.finishConnect()) {
              selectionKey.cancel();
              target.connectFinish = System.currentTimeMillis();
              socketChannel.close();
              addFinishedTarget(target);
          }
      } catch (IOException x) {
          socketChannel.close();
          target.failure = x;
          addFinishedTarget(target);
      }
    }
  }

  public void receiveTarget(){
    //接收用户输入的地址，向targets队列中加入任务
    try{
      BufferedReader localReader=new BufferedReader(new InputStreamReader(System.in));
      String msg=null;
      while((msg=localReader.readLine())!=null){
        if(!msg.equals("bye")){
          Target target=new Target(msg);
          addTarget(target);
        }else{
          shutdown=true;
          selector.wakeup();
          break;
        }
      }
    }catch(IOException e){
       e.printStackTrace();
    }
  }

  boolean shutdown=false;

  public class Printer extends Thread{
    public Printer(){
        setDaemon(true);
    }
    public void run(){
        printFinishedTargets();
    }
  }

  public class Connector extends Thread{
    public void run(){
        while (!shutdown) {
            try {
                registerTargets();
                if (selector.select() > 0) {
                    processSelectedKeys();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
       try{
           selector.close();
       }catch(IOException e){e.printStackTrace();}
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
