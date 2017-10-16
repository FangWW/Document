package store;

import java.util.*;
import java.sql.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class StoreModelImpl extends UnicastRemoteObject implements StoreModel{
  private ArrayList<StoreView> changeListeners=new ArrayList<StoreView>(10);
  private DBService dbService;

  public StoreModelImpl()throws StoreException,RemoteException{
    try{
      dbService=new DBServiceImpl();
    }catch(Exception e){
      throw new StoreException("数据库异常");
    }
  }
  
  /** 判断数据库中是否存在参数指定的客户ID */
  protected boolean idExists(long id){
    Statement stmt=null;
    try{
      stmt=dbService.getStatement();
      ResultSet result=stmt.executeQuery("select ID from CUSTOMERS where ID="+id);
      return result.next();
    }catch(Exception e){
      return false;
    }finally{
      dbService.closeStatement(stmt);
    }
  }
  
  /** 注册视图，以便当模型修改了数据库中的客户信息时，可以回调视图的刷新界面的方法 */
  public void addChangeListener(StoreView sv) throws StoreException,RemoteException{
    changeListeners.add(sv);
  }
  
  /** 当数据库中客户信息发生变化时，同步刷新所有的视图 */
  private void fireModelChangeEvent(Customer cust){
    StoreView v;
    for(int i=0;i<changeListeners.size();i++){
      try{
       v=changeListeners.get(i);
       v.handleCustomerChange(cust);
      }catch(Exception e){
        System.out.println(e.toString());
      }
    }
  }
  
  /** 向数据库中添加一个新的客户 */
  public void addCustomer(Customer cust) throws StoreException,RemoteException{
    try{
      if(idExists(cust.getId())){
        throw new StoreException("Customer "+cust.getId()+" already exists");
      }
      long id=cust.getId(); 
      String name=cust.getName();
      String addr=cust.getAddr();
      int age=cust.getAge();
      String sql="insert into CUSTOMERS(ID,NAME,ADDRESS,AGE) values("
      + id+","
      + "'"+name+"'"+","
      + "'"+addr+"'"+","
      + age+")";

      dbService.modifyTable(sql);
      fireModelChangeEvent(cust);
    }catch(Exception e){
       throw new StoreException("StoreDbImpl.addCustomer\n"+e);
    }
  }
  
  /** 从数据库中删除一个客户 */  
  public void deleteCustomer(Customer cust) throws StoreException,RemoteException{
    try{
      if(!idExists(cust.getId())){
        throw new StoreException("Customer "+cust.getId()+" not found");
      }
      String sql="delete from CUSTOMERS where ID="+cust.getId();
      dbService.modifyTable(sql);
      fireModelChangeEvent(cust);
    }catch(Exception e){
        throw new StoreException("StoreDbImpl.deleteCustomer\n"+e);
    }
  }
  
  /** 更新数据库中的客户 */
  public void updateCustomer(Customer cust)throws StoreException,RemoteException{
    try{
      if(!idExists(cust.getId())){
        throw new StoreException("Customer "+cust.getId()+" not found");
      }
      String sql="update CUSTOMERS set "+
      "NAME='"+cust.getName()+"',"+
      "AGE="+cust.getAge()+","+
      "ADDRESS='"+cust.getAddr()+"' "+
      "where ID="+cust.getId()+"";

      dbService.modifyTable(sql);
      fireModelChangeEvent(cust);
    }catch(Exception e){
        throw new StoreException("StoreDbImpl.updateCustomer\n"+e);
    }
  }
  
  /** 根据参数id检索客户 */
  public Customer getCustomer(long id)throws StoreException,RemoteException{
    Statement stmt=null;
    try{
      if(!idExists(id)){
        throw new StoreException("Customer "+id+" not found");
      }

      stmt=dbService.getStatement();
      ResultSet rs=stmt.executeQuery("select ID,NAME,ADDRESS,AGE from CUSTOMERS where ID="+id);
      rs.next();

      return new Customer(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4));
    }catch(Exception e){
      throw new StoreException("StoreDbImpl.getCustomer\n"+e);
    }finally{
      dbService.closeStatement(stmt);
    }
  }
  
  /** 返回数据库中所有的客户清单 */
  public Set<Customer> getAllCustomers() throws StoreException,RemoteException{
    Set<Customer> custs=new HashSet<Customer>();
    Statement stmt=null;
    try{
      stmt=dbService.getStatement();
      ResultSet rs=stmt.executeQuery("select ID,NAME,ADDRESS,AGE from CUSTOMERS ");
      while(rs.next()){
        custs.add(new Customer(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
      }
      return custs;
    }catch(Exception e){
      throw new StoreException("StoreDbImpl.getAllCustomers\n"+e);
    }finally{
      dbService.closeStatement(stmt);
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
