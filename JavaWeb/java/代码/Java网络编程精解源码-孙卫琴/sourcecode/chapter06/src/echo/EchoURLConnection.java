package echo;
import java.net.*;
import java.io.*;
public class EchoURLConnection extends URLConnection{
  private Socket connection=null;
  public final static int DEFAULT_PORT=8000;

  public EchoURLConnection(URL url){
    super(url);
  }

  public synchronized InputStream getInputStream() throws IOException{
    if(!connected)connect();
    return connection.getInputStream();
  }

  public synchronized OutputStream getOutputStream() throws IOException{
    if(!connected)connect();
    return connection.getOutputStream();
  }
  public String getContentType(){
    return "text/plain";
  }

  public synchronized void connect()throws IOException{
    if(!connected){
      int port=url.getPort();
      if(port<0 || port>65535)port=DEFAULT_PORT;
      this.connection=new Socket(url.getHost(),port);
      this.connected=true;
    }
  }

  public synchronized void disconnect() throws IOException{
      if(connected){
          this.connection.close();
          this.connected=false;
      }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
