package proxy;
public class Client1{
  public static void main(String args[]){
    HelloService helloService=new HelloServiceImpl();
    HelloService helloServiceProxy=new HelloServiceProxy(helloService);
    System.out.println(helloServiceProxy.echo("hello"));
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
