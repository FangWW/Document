package sync;

import java.rmi.*;
import javax.naming.*;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       Stack stack = new StackImpl("a stack");

       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:MyStack", stack );
        
       System.out.println( "服务器注册了一个Stack对象" );
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
