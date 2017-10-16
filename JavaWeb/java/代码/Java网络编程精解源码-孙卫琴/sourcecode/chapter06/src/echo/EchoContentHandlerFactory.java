package echo;
import java.net.*;
public class EchoContentHandlerFactory implements ContentHandlerFactory{
  public ContentHandler createContentHandler(String mimetype){
    if(mimetype.equals("text/plain")){
      return new EchoContentHandler();
    }else{
      return null;
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
