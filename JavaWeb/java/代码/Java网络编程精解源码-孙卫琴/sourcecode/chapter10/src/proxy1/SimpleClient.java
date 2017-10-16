package proxy1;
import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient {
  public static void main(String args[])throws Exception {
     //创建静态代理类实例
    HelloService helloService1=new HelloServiceProxy("localhost",8000);
    System.out.println(helloService1.echo("hello"));
    System.out.println(helloService1.getTime());

    //创建动态代理类实例
    HelloService helloService2=
         (HelloService)ProxyFactory.getProxy(HelloService.class,"localhost",8000);
    System.out.println(helloService2.echo("hello"));
    System.out.println(helloService2.getTime());
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
