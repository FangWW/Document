package hello;
import java.util.Date;
public interface HelloService extends Remote{
  public String echo(String msg) throws RemoteException;
  public Date getTime() throws RemoteException;
} 


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
