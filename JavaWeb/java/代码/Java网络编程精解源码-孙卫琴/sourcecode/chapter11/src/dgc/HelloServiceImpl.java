package dgc;
import java.util.Date;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService,Unreferenced{
  private boolean isAccessed=false;
  public HelloServiceImpl()throws RemoteException{}
  public void access()throws RemoteException{
    System.out.println("HelloServiceImpl:我被一个客户远程引用");
    isAccessed=true;
  }
  public void bye()throws RemoteException{
    System.out.println("HelloServiceImpl:一个客户不再引用我了");
  }
  public boolean isAccessed()throws RemoteException{
    return isAccessed;
  }
  public void unreferenced(){
    System.out.println("HelloServiceImpl:我不再被远程引用");
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
