package store;
import java.rmi.*;
import java.util.*;
public interface StoreModel extends Remote {
  /** 注册视图，以便当模型修改了数据库中的客户信息时，可以回调视图的刷新界面的方法 */
  public void addChangeListener(StoreView sv) throws StoreException,RemoteException;
  /** 向数据库中添加一个新的客户 */
  public void addCustomer(Customer cust) throws StoreException,RemoteException;
  /** 从数据库中删除一个客户 */ 
  public void deleteCustomer(Customer cust) throws StoreException,RemoteException;
  /** 更新数据库中的客户 */ 
  public void updateCustomer(Customer cust) throws StoreException,RemoteException;
  /** 根据参数id检索客户 */
  public Customer getCustomer(long id) throws StoreException,RemoteException;
  /** 返回数据库中所有的客户清单 */
  public Set<Customer> getAllCustomers() throws StoreException,RemoteException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
