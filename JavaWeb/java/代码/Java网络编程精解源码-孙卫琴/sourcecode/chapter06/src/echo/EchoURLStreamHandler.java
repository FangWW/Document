package echo;
import java.net.*;
import java.io.*;
public class EchoURLStreamHandler extends URLStreamHandler{
  public int getDefaultPort(){
    return 8000;
  }
  protected URLConnection openConnection(URL url)throws IOException{
    return new EchoURLConnection(url);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
