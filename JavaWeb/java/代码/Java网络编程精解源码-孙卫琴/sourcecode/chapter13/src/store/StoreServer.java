package store;
import java.rmi.*;
import javax.naming.*;
public class StoreServer {
  public static void main( String args[] ){
    try{
      System.setProperty("java.security.policy",StoreClient.class.getResource("secure.policy").toString());
      System.setSecurityManager(new RMISecurityManager());

      StoreModel storeModel=new StoreModelImpl();
      Context namingContext=new InitialContext();
      namingContext.rebind( "rmi:storeModel", storeModel );
      System.out.println( "服务器注册了StoreModel对象" );
    }catch( Exception e ){
      e.printStackTrace();
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
