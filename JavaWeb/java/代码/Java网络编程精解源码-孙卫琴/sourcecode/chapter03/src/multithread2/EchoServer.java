package multithread2;
import java.io.*;
import java.net.*;
public class EchoServer {
  private int port=8000;
  private ServerSocket serverSocket;
  private ThreadPool threadPool;  //线程池
  private final int POOL_SIZE=4;  //单个CPU时线程池中工作线程的数目

  public EchoServer() throws IOException {
    serverSocket = new ServerSocket(port);
    //创建线程池
    //Runtime的availableProcessors()方法返回当前系统的CPU的数目
    //系统的CPU越多，线程池中工作线程的数目也越多 
    threadPool= new ThreadPool( 
            Runtime.getRuntime().availableProcessors() * POOL_SIZE);

    System.out.println("服务器启动");
  }

  public void service() {
    while (true) {
      Socket socket=null;
      try {
        socket = serverSocket.accept();
        threadPool.execute(new Handler(socket)); //把与客户通信的任务交给线程池
      }catch (IOException e) {
         e.printStackTrace();
      }
    }
  }

  public static void main(String args[])throws IOException {
    new EchoServer().service();
  }
}

class Handler implements Runnable{
  private Socket socket;
  public Handler(Socket socket){
    this.socket=socket;
  }
  private PrintWriter getWriter(Socket socket)throws IOException{
    OutputStream socketOut = socket.getOutputStream();
    return new PrintWriter(socketOut,true);
  }
  private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    return new BufferedReader(new InputStreamReader(socketIn));
  }
  public String echo(String msg) {
    return "echo:" + msg;
  }
  public void run(){
    try {
      System.out.println("New connection accepted " +
      socket.getInetAddress() + ":" +socket.getPort());
      BufferedReader br =getReader(socket);
      PrintWriter pw = getWriter(socket);

      String msg = null;
      while ((msg = br.readLine()) != null) {
        System.out.println(msg);
        pw.println(echo(msg));
        if (msg.equals("bye"))
          break;
      }
    }catch (IOException e) {
       e.printStackTrace();
    }finally {
       try{
         if(socket!=null)socket.close();
       }catch (IOException e) {e.printStackTrace();}
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
