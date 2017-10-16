package sync;
import java.rmi.*;

public interface Stack extends Remote{
  public String getName()throws RemoteException;
  public int getPoint()throws RemoteException;
  public String pop()throws RemoteException ;
  public void push(String goods) throws RemoteException;
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
