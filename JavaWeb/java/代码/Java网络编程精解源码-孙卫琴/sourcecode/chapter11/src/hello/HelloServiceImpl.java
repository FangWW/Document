package hello;
import java.util.Date;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
  private String name;
  public HelloServiceImpl(String name)throws RemoteException{
    this.name=name;
  }
  public String echo(String msg)throws RemoteException{
    System.out.println(name+":调用echo()方法");
    return "echo:"+msg +" from "+name;
  }
  public Date getTime()throws RemoteException{
    System.out.println(name+":调用getTime()方法");
    return new Date();
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
