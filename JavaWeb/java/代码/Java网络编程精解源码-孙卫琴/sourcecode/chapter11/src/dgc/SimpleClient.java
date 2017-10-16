package dgc;
import java.rmi.*;
import javax.naming.*;

public class SimpleClient{
  public static void main( String args[] ){
    String url="rmi://localhost/";
    try{
      Context namingContext=new InitialContext();
      HelloService service=(HelloService)namingContext.lookup(url+"HelloService");
      service.access();
      Thread.sleep(10000);
      service.bye();
    }catch( Exception e){
       e.printStackTrace();
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
