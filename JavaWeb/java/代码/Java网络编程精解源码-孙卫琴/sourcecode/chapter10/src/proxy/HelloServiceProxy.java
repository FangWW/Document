package proxy;
import java.util.Date;
public class HelloServiceProxy implements HelloService{
  private HelloService helloService;

  public HelloServiceProxy(HelloService helloService){
    this.helloService=helloService;
  }

  public void setHelloServiceProxy(HelloService helloService){
    this.helloService=helloService;
  }

  public String echo(String msg){
    System.out.println("before calling echo()");
    String result=helloService.echo(msg);
    System.out.println("after calling echo()");
    return result;
  }
  public Date getTime(){
    System.out.println("before calling getTime()");
    Date date=helloService.getTime();
    System.out.println("after calling getTime()");
    return date;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
