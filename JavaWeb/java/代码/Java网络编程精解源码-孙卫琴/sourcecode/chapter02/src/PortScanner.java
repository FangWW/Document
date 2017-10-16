import java.net.*;
import java.io.*;
public class PortScanner {
  public static void main(String args[]){
    String host="localhost";
    if(args.length>0)host=args[0];
    new PortScanner().scan(host);
  }
  public void scan(String host){
    Socket socket=null;
    for(int port=1;port<1024;port++){
      try {
          socket = new Socket(host, port);
          System.out.println("There is a server on port "+port);
      } catch (IOException e) {
          System.out.println("Can't connect to port "+port);
      } finally {
        try {
            if(socket!=null)socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
