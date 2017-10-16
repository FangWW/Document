package activate;
import java.util.Date;
import java.rmi.*;
import java.rmi.activation.*;
import java.io.*;

public class HelloServiceImpl extends Activatable implements HelloService{
  private String name;
  public HelloServiceImpl(ActivationID id,MarshalledObject data)throws RemoteException{
    super(id,0);
    try{
      this.name=(String)data.get();
    }catch(Exception e){e.printStackTrace();}
    System.out.println("创建"+name);
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
