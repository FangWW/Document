package hello;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
  private String name;
  public HelloServiceImpl(String name)throws RemoteException{
    this.name=name;
  }
  public String echo(String msg)throws RemoteException{
    System.out.println(name+":����echo()����");
    return "echo:"+msg +" from "+name;
  }
  public Date getTime()throws RemoteException{
    System.out.println(name+":����getTime()����");
    return new Date();
  }
} 


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
