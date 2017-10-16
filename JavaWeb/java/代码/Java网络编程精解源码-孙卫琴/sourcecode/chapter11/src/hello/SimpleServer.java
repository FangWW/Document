package hello;

import java.rmi.*;
import javax.naming.*;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       HelloService service1 = new HelloServiceImpl("service1");
       HelloService service2 = new HelloServiceImpl("service2");

       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:HelloService1", service1 );
       namingContext.rebind( "rmi:HelloService2", service2 );
/*     
       namingContext.rebind( "rmi://localhost:8000/HelloService1", service1 );
       namingContext.rebind( "rmi://localhost:8000/HelloService1", service2 );
*/   
    
       System.out.println( "服务器注册了两个HelloService对象" );
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
