package echo;
import java.net.*;
import java.io.*;
public class EchoURLStreamHandlerFactory implements URLStreamHandlerFactory{
  public URLStreamHandler createURLStreamHandler(String protocol){
    if(protocol.equals("echo"))
      return new EchoURLStreamHandler();
    else
      return null;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
