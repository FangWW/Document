package store;
import java.rmi.*;

public interface StoreView extends Remote{
  /** 注册处理用户动作的监听器，即StoreController */
  public void addUserGestureListener(StoreController ctrl)throws StoreException,RemoteException;

  /** 在图形界面上显示数据， 参数display表示待显示的数据 */
  public void showDisplay(Object display)throws StoreException,RemoteException;

  /** 当其他程序修改了数据库中某个客户的信息，同步刷新所有客户程序中的图形界面 */
  public void handleCustomerChange(Customer cust)throws StoreException,RemoteException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
