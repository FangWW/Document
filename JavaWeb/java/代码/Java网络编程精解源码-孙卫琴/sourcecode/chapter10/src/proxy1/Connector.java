package proxy1;
import java.io.*;
import java.net.*;
import java.util.*;
public class Connector {
  private String host;
  private int port;
  private Socket skt;
  private InputStream is;
  private ObjectInputStream ois;
  private OutputStream os;
  private ObjectOutputStream oos;

  public Connector(String host,int port)throws Exception{
     this.host=host;
     this.port=port;
     connect(host,port);
  }

  public void send(Object obj)throws Exception{
    oos.writeObject(obj);
  }
  public Object receive() throws Exception{
    return ois.readObject();
  }
  public void connect()throws Exception{
    connect(host,port);
  }
  public void connect(String host,int port)throws Exception{
    skt=new Socket(host,port);
    os=skt.getOutputStream();
    oos=new ObjectOutputStream(os);
    is=skt.getInputStream();
    ois=new ObjectInputStream(is);
  }
  public void close(){
    try{
    }finally{
      try{
        ois.close();
        oos.close();
        skt.close();
      }catch(Exception e){
        System.out.println("Connector.close: "+e);
      }
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
