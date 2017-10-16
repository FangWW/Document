package stock;

import java.rmi.*;
import javax.naming.*;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       StockQuoteRegistryImpl registry=new StockQuoteRegistryImpl();
 
       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:StockQuoteRegistry", registry);
       System.out.println( "服务器注册了一个StockQuoteRegistry对象" );

       new Thread(registry).start();
    }catch(Exception e){
       e.printStackTrace();
    } 
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
