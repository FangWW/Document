package flight2;

import java.rmi.*;
import javax.naming.*;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       FlightFactory factory  = new FlightFactoryImpl();
 
       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:FlightFactory", factory );
        
       System.out.println( "服务器注册了一个FlightFactory对象" );
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
