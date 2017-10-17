package dgc;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService,Unreferenced{
  private boolean isAccessed=false;
  public HelloServiceImpl()throws RemoteException{}
  public void access()throws RemoteException{
    System.out.println("HelloServiceImpl:�ұ�һ���ͻ�Զ������");
    isAccessed=true;
  }
  public void bye()throws RemoteException{
    System.out.println("HelloServiceImpl:һ���ͻ�������������");
  }
  public boolean isAccessed()throws RemoteException{
    return isAccessed;
  }
  public void unreferenced(){
    System.out.println("HelloServiceImpl:�Ҳ��ٱ�Զ������");
  }
} 


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
